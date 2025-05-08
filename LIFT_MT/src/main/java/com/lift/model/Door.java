package com.lift.model;

class Door {
    private boolean isOpen = false;
    public void open() {
        isOpen = true;
        System.out.println("Door opened.");
    }
    public void close() {
        isOpen = false;
        System.out.println("Door closed.");
    }
    public boolean isOpen() { return isOpen; }
}
