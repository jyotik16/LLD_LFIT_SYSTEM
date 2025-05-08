package com.lift.model;

public class MovingDownState implements LiftState {
    public void handleRequest(Lift lift, int floor) {
        if (floor < lift.getCurrentFloor()) {
            System.out.println("lift "+lift.LiftId()+" is Moving down to floor: " + floor);
            lift.setCurrentFloor(floor);
            lift.setState(new StoppedState());
        }
    }
}

