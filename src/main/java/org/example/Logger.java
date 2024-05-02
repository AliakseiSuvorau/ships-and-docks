package org.example;

import java.util.ArrayList;

public class Logger {
    private final Integer shipNum;
    private final ArrayList<Integer> states = new ArrayList<>();
    private Boolean isInterrupted = false;
    private Product typeOfDock = null;
    private Product typeOfShip = null;

    public Logger(Integer shipNum) {
        this.shipNum = shipNum;
    }

    public final ArrayList<Integer> getStates() {
        return states;
    }

    public final Product getTypeOfShip() {
        return typeOfShip;
    }

    public final Product getTypeOfDock() {
        return typeOfDock;
    }

    public final Boolean getIsInterruptedFlag() {
        return isInterrupted;
    }

    public final void setTypeOfShip(Product product) {
        typeOfShip = product;
    }

    public  final void setTypeOfDock(Product product) {
        typeOfDock = product;
    }

    public void creation(Product shipProduct) {
        states.add(0);
        typeOfShip = shipProduct;
        System.out.printf("The ship #%d was created!\n", shipNum);
    }

    public void enterTunnel() {
        states.add(1);
        System.out.printf("The ship #%d entered the tunnel!\n", shipNum);
    }

    public void exitTunnel() {
        states.add(2);
        System.out.printf("The ship #%d exited the tunnel!\n", shipNum);
    }

    public void approachDock(Product dockProduct) {
        states.add(3);
        typeOfDock = dockProduct;
        System.out.printf("The ship #%d approached the dock!\n", shipNum);
    }

    public void startLoading() {
        states.add(4);
        System.out.printf("The ship #%d started loading!\n", shipNum);
    }

    public void finishLoading() {
        states.add(5);
        System.out.printf("The ship #%d finished loading!\n", shipNum);
    }

    public void isInterrupted() {
        isInterrupted = true;
        System.out.printf("The ship #%d is interrupted!\n", shipNum);
    }
}
