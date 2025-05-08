package com.lift.strategy;

import com.lift.model.*;

public class DefaultMovementStrategy implements MovementStrategy {
    public void move(Lift lift, int floor) {
        System.out.println("now lift "+lift.LiftId()+" at Current floor: " + lift.getCurrentFloor() + ", Requested floor: " + floor);

        if (floor > lift.getCurrentFloor()) {
           // System.out.println("Setting state: MovingUpState");
            lift.setState(new MovingUpState());
        } else if (floor < lift.getCurrentFloor()) {
          //  System.out.println("Setting state: MovingDownState");
            lift.setState(new MovingDownState());
        } else {
          //  System.out.println("Setting state: StoppedState");
            lift.setState(new StoppedState());
        }
        LiftState liftState = lift.getState();
        liftState.handleRequest(lift, floor);
    }
}
