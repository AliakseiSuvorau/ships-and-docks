package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    private final Product product;
    public final ReentrantLock dockLock = new ReentrantLock();

    Dock(Product product) {
        this.product = product;
    }

    public final Product getProduct() {
        return product;
    }
}
