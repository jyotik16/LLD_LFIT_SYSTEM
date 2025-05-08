package com.lift.model;

public class StoppedState implements LiftState {
    public void handleRequest(Lift lift, int floor) {
        System.out.println("lift "+lift.LiftId()+" is stopped at floor: " + floor);
        lift.setCurrentFloor(floor);
    }
}

