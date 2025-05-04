package com.lift.model;

public class MovingUpState implements LiftState {
    public void handleRequest(Lift lift, int floor) {
        if (floor > lift.getCurrentFloor()) {
            System.out.println("Moving up to floor: " + floor);
            lift.setCurrentFloor(floor);
            lift.setState(new StoppedState());
        }
    }
}