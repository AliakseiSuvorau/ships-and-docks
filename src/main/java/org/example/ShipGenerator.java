package org.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShipGenerator {
    private final Tunnel tunnel;
    private final Dock breadDock;
    private final Dock bananasDock;
    private final Dock clothesDock;
    private Integer shipCount = 0;
    private final ExecutorService pool;
    private final ArrayList<Logger> loggerArray = new ArrayList<>();

    public ShipGenerator(Tunnel tunnel, Dock breadDock, Dock bananasDock, Dock clothesDock) {
        this.tunnel = tunnel;
        this.breadDock = breadDock;
        this.bananasDock = bananasDock;
        this.clothesDock = clothesDock;
        this.pool = Executors.newCachedThreadPool();
    }

    public final void poolAwaitTermination(long timeout) throws InterruptedException {
        pool.awaitTermination(timeout, TimeUnit.SECONDS);
    }
    public final ArrayList<Logger> getLoggerList() {
        return loggerArray;
    }

    public void makeShip(Size size, Product product, Integer num) {
        for (int i = 0; i < num; ++i) {
            Ship newShip = switch (product) {
                case Product.BREAD -> new Ship(size, product, tunnel, breadDock, shipCount);
                case Product.BANANAS -> new Ship(size, product, tunnel, bananasDock, shipCount);
                default -> new Ship(size, product, tunnel, clothesDock, shipCount);
            };
            pool.execute(newShip);
            ++shipCount;
            loggerArray.add(newShip.getLogger());
        }
    }

    public void poolShutDown() {
        pool.shutdown();
    }

}
