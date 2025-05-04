package com.lift.strategy;

import com.lift.model.*;

public class DefaultMovementStrategy implements MovementStrategy {
    public void move(Lift lift, int floor) {
        if (floor > lift.getCurrentFloor()) {
            lift.setState(new MovingUpState());
        } else if (floor < lift.getCurrentFloor()) {
            lift.setState(new MovingDownState());
        } else {
            lift.setState(new StoppedState());
        }
        LiftState liftState = lift.getState();
        liftState.handleRequest(lift, floor);
    }
}
