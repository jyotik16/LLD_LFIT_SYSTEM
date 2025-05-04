package com.lift.model;

class Display {
    private int floor;
    private Direction direction;
    public void update(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
        show();
    }
    public void show() {
        System.out.println("Display: Floor " + floor + ", Direction: " + direction);
    }
}

