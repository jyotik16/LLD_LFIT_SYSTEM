package com.lift.strategy;

import com.lift.model.Lift;

public class EmergencyMovementStrategy implements MovementStrategy {
    public void move(Lift lift, int floor) {
        System.out.println("Emergency! Opening doors at floor: " + lift.getCurrentFloor());
        lift.openDoor();
    }
}

