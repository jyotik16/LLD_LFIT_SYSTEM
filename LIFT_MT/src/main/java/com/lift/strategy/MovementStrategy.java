package com.lift.strategy;

import com.lift.model.Lift;

public interface MovementStrategy {
    void move(Lift lift, int floor);
}
