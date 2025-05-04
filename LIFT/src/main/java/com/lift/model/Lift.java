package com.lift.model;

import com.lift.strategy.DefaultMovementStrategy;
import com.lift.strategy.MovementStrategy;

import java.util.LinkedList;
import java.util.Queue;


public class Lift {

    private int id;
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;
    private LiftState state;
    private MovementStrategy movementStrategy;
    private Door door = new Door();
    private Display display = new Display();
    private Queue<Integer> requests = new LinkedList<Integer>();

    public Lift(int id) { this.id = id; }

    public void handleRequest(Lift lift , int floor) {
        requests.offer(floor);
        move();
    }

    public void move() {
        if (!requests.isEmpty()) {
            int nextFloor = requests.poll();
            movementStrategy.move(this, nextFloor);
            display.update(currentFloor, direction);
        }
    }

    public Lift(int id, LiftState state, MovementStrategy movementStrategy) {
        this.id = id;
        this.state = state;
        this.movementStrategy = movementStrategy;
    }

    public void openDoor() { door.open(); }
    public void closeDoor() { door.close(); }

    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
        this.direction = (floor > currentFloor) ? Direction.UP :
                (floor < currentFloor) ? Direction.DOWN : Direction.IDLE;
    }

    public Direction getDirection() { return direction; }
    public void setState(LiftState state) { this.state = state; }
    public LiftState getState() { return state; }
    public void setMovementStrategy(MovementStrategy strategy) { this.movementStrategy = strategy; }
}
