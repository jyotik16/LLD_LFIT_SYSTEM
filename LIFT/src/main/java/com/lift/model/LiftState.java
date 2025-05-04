package com.lift.model;


public interface LiftState {
    void handleRequest(Lift lift, int floor);
}