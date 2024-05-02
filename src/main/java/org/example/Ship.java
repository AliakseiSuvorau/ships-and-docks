package org.example;

import static java.lang.Thread.sleep;

public class Ship implements Runnable {
    private final Size size;
    private final Product product;
    private final Tunnel tunnel;
    private final Dock dock;
    private final Logger logger;

    public final Product getProduct() {
        return product;
    }

    public final Logger getLogger() {
        return logger;
    }

    public Ship(Size size, Product product, Tunnel tunnel, Dock dock, Integer shipNum) {
        this.size = size;
        this.product = product;
        this.tunnel = tunnel;
        this.dock = dock;
        this.logger = new Logger(shipNum);
        logger.creation(getProduct());
        logger.setTypeOfShip(product);
        logger.setTypeOfDock(product);
    }

    private void passTunnel() {
        try {
            tunnel.semaphore.acquire();
            logger.enterTunnel();
            sleep(1000);
            logger.exitTunnel();
        } catch (InterruptedException e) {
            logger.isInterrupted();
            Thread.currentThread().interrupt();
        } finally {
            tunnel.semaphore.release();
        }
    }

    private void loadShip() throws InterruptedException {
        logger.startLoading();
        switch (size) {
            case Size.SMALL:
                sleep(1000);
                break;
            case Size.MEDIUM:
                sleep(1000 * 5);
                break;
            case Size.LARGE:
                sleep(1000 * 10);
                break;
        }
        logger.finishLoading();
    }

    private void approachDock() {
        dock.dockLock.lock();
        logger.approachDock(dock.getProduct());
        try {
            loadShip();
        } catch (InterruptedException e) {
            logger.isInterrupted();
            Thread.currentThread().interrupt();
        } finally {
            dock.dockLock.unlock();
        }
    }

    @Override
    public final void run() {
        passTunnel();
        approachDock();
    }
}
