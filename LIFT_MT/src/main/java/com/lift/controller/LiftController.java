package com.lift.controller;


import com.lift.model.Direction;
import com.lift.model.Lift;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LiftController {
    private List<Lift> lifts;

    private AtomicInteger counter = new AtomicInteger(0);
    public LiftController(List<Lift> lifts) { this.lifts = lifts; }

    public void requestLift(int floor) {
        String threadName = String.valueOf(Thread.currentThread());
        Lift lift = assignLift(floor);
        System.out.println(+lift.LiftId()+" lift move to the floor "+floor);
        if (lift != null) lift.handleRequest(lift,floor,threadName);
        else System.out.println("No lifts available");
    }

    public Lift assignLift(int floor) {

        /* //case: give any lift
        int index = counter.getAndIncrement() % lifts.size();
        return lifts.get(index);
        */

        /*
         * Optimize the route: When the lift receives multiple requests,
         * it should select the next closest request based on the current direction and
         * proximity to minimize the total travel distance.
         * */

        Lift bestLift = null;
        int minDistance = Integer.MAX_VALUE;

        // Find the closest available lift or idle lift
        for (Lift lift : lifts) {
            int distance = Math.abs(lift.getCurrentFloor() - floor);
            if (distance < minDistance && lift.getDirection() == Direction.IDLE) {
                minDistance = distance;
                bestLift = lift;
            }
        }

        if (bestLift == null) {// If no idle lift, pick the first available lift
            bestLift = lifts.get(counter.getAndIncrement() % lifts.size());
        }
        return bestLift;

    }
}


