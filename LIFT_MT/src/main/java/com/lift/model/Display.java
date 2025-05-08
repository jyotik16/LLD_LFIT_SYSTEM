package com.lift.model;

class Display {
    private int floor;
    private Direction direction;

    private String threadName;
    public void update(int floor, Direction direction,String threadName) {
        this.floor = floor;
        this.direction = direction;
        this.threadName = threadName;
        show();
    }
    public void show() {
        System.out.println("Display: Floor " + floor + ", Direction: " + direction+" ,"+threadName);
    }
}

