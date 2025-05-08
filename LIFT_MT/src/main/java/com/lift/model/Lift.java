package com.lift.model;

import com.lift.strategy.DefaultMovementStrategy;
import com.lift.strategy.MovementStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Lift implements Runnable{

    private int id;
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;
    private LiftState state;
    private MovementStrategy movementStrategy;
    private Door door = new Door();
    private Display display = new Display();
    private Queue<Integer> requests = new ConcurrentLinkedQueue<>();

    private volatile boolean running = true;

    private String threadName;

    public Lift(int id) { this.id = id; }

    public int LiftId(){
        return this.id;
    }

    public void stopLift() {
        running = false;
    }


    public synchronized  void handleRequest(Lift lift , int floor,String threadName) {
        requests.offer(floor);
        move(threadName);
    }

    public synchronized void move(String threadName) {
        //simple case
        /*if (!requests.isEmpty()) {
            int nextFloor = requests.poll();
            movementStrategy.move(this, nextFloor);
            display.update(currentFloor, direction,threadName);
        }*/

        //handled the case: Prioritize requests in the same direction
        if (!requests.isEmpty()) {

            int nextFloor = requests.poll();
            requests.remove(nextFloor);  // Remove from the queue once the request is picked

            movementStrategy.move(this, nextFloor);
            display.update(currentFloor, direction, threadName);
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
        if (floor > this.currentFloor) {
            this.direction = Direction.UP;
        } else if (floor < this.currentFloor) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.IDLE;
        }
        this.currentFloor = floor;
    }

    public Direction getDirection() { return direction; }
    public void setState(LiftState state) { this.state = state; }
    public LiftState getState() { return state; }
    public void setMovementStrategy(MovementStrategy strategy) { this.movementStrategy = strategy; }

    @Override
    public void run() {
        while (running) {
            try {
                handleRequest(this, this.currentFloor,this.threadName);
                Thread.sleep(1000);
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
