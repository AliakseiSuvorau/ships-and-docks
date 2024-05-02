package org.example;

public class Main {
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);
        generator.makeShip(Size.SMALL, Product.BREAD, 1);
        generator.poolShutDown();
    }
}