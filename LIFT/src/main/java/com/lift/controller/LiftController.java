package com.lift.controller;


import com.lift.model.Lift;

import java.util.List;

public class LiftController {
    private List<Lift> lifts;
    public LiftController(List<Lift> lifts) { this.lifts = lifts; }

    public void requestLift(int floor) {
        Lift lift = assignLift(floor);
        if (lift != null) lift.handleRequest(lift,floor);
        else System.out.println("No lifts available");
    }

    public Lift assignLift(int floor) {
        // Simple assignment: pick the first idle lift or the one closest to the floor
        Lift best = null;
        int minDistance = Integer.MAX_VALUE;
        for (Lift lift : lifts) {
            int distance = Math.abs(lift.getCurrentFloor() - floor);
            if (distance < minDistance) {
                minDistance = distance;
                best = lift;
            }
        }
        return best;
    }
}


