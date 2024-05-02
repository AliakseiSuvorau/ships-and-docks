package org.example;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommonTest {

    @Test
    @Order(1)
    void simpleTestOneTypeOneSize() {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);

        generator.makeShip(Size.SMALL, Product.BREAD, 10);
        generator.poolShutDown();

        try {
            generator.poolAwaitTermination(15);
        } catch (InterruptedException e) {
            fail("An interrupt exception occurred during test!");
        }

        ArrayList<Logger> loggerList = generator.getLoggerList();
        for (Logger logger : loggerList) {
            assertEquals(logger.getIsInterruptedFlag(), false);  // The thread was not interrupted
            assertEquals(logger.getTypeOfShip(), logger.getTypeOfDock());  // The ship was loaded on the appropriate dock
            Integer trueStep = 0;
            assertEquals(6, logger.getStates().size());  // The ship went through all stages
            for (Integer step : logger.getStates()) {
                assertEquals(step, trueStep);  // The ship did everything in the right order
                ++trueStep;
            }
        }
    }

    @Test
    @Order(2)
    void simpleTestOneTypeManySize() {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);

        generator.makeShip(Size.SMALL, Product.BREAD, 4);
        generator.makeShip(Size.MEDIUM, Product.BREAD, 3);
        generator.makeShip(Size.LARGE, Product.BREAD, 3);
        generator.poolShutDown();

        try {
            generator.poolAwaitTermination(55);
        } catch (InterruptedException e) {
            fail("An interrupt exception occurred during test!");
        }

        ArrayList<Logger> loggerList = generator.getLoggerList();
        for (Logger logger : loggerList) {
            assertEquals(logger.getIsInterruptedFlag(), false);
            assertEquals(logger.getTypeOfShip(), logger.getTypeOfDock());
            Integer trueStep = 0;
            assertEquals(6, logger.getStates().size());
            for (Integer step : logger.getStates()) {
                assertEquals(step, trueStep);
                ++trueStep;
            }
        }
    }

    @Test
    @Order(3)
    void simpleTestManyTypeOneSize() {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);

        generator.makeShip(Size.SMALL, Product.BREAD, 4);
        generator.makeShip(Size.SMALL, Product.BANANAS, 3);
        generator.makeShip(Size.SMALL, Product.CLOTHES, 3);
        generator.poolShutDown();

        try {
            generator.poolAwaitTermination(10);
        } catch (InterruptedException e) {
            fail("An interrupt exception occurred during test!");
        }

        ArrayList<Logger> loggerList = generator.getLoggerList();
        for (Logger logger : loggerList) {
            assertEquals(logger.getIsInterruptedFlag(), false);
            assertEquals(logger.getTypeOfShip(), logger.getTypeOfDock());
            Integer trueStep = 0;
            assertEquals(6, logger.getStates().size());
            for (Integer step : logger.getStates()) {
                assertEquals(step, trueStep);
                ++trueStep;
            }
        }
    }

    @Test
    @Order(4)
    void simpleTestManyTypeManySize() {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);

        generator.makeShip(Size.SMALL, Product.BREAD, 1);
        generator.makeShip(Size.SMALL, Product.BANANAS, 1);
        generator.makeShip(Size.SMALL, Product.CLOTHES, 1);
        generator.makeShip(Size.MEDIUM, Product.BREAD, 1);
        generator.makeShip(Size.MEDIUM, Product.BANANAS, 1);
        generator.makeShip(Size.MEDIUM, Product.CLOTHES, 1);
        generator.makeShip(Size.LARGE, Product.BREAD, 1);
        generator.makeShip(Size.LARGE, Product.BANANAS, 1);
        generator.makeShip(Size.LARGE, Product.CLOTHES, 1);
        generator.poolShutDown();

        try {
            generator.poolAwaitTermination(25);
        } catch (InterruptedException e) {
            fail("An interrupt exception occurred during test!");
        }

        ArrayList<Logger> loggerList = generator.getLoggerList();
        for (Logger logger : loggerList) {
            assertEquals(logger.getIsInterruptedFlag(), false);
            assertEquals(logger.getTypeOfShip(), logger.getTypeOfDock());
            Integer trueStep = 0;
            assertEquals(6, logger.getStates().size());
            for (Integer step : logger.getStates()) {
                assertEquals(step, trueStep);
                ++trueStep;
            }
        }
    }

    @Test
    @Order(5)
    void stressTest() {
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, breadDock, bananasDock, clothesDock);

        generator.makeShip(Size.SMALL, Product.BREAD, 10);
        generator.makeShip(Size.SMALL, Product.BANANAS, 10);
        generator.makeShip(Size.SMALL, Product.CLOTHES, 10);
        generator.makeShip(Size.MEDIUM, Product.BREAD, 10);
        generator.makeShip(Size.MEDIUM, Product.BANANAS, 10);
        generator.makeShip(Size.MEDIUM, Product.CLOTHES, 10);
        generator.makeShip(Size.LARGE, Product.BREAD, 10);
        generator.makeShip(Size.LARGE, Product.BANANAS, 10);
        generator.makeShip(Size.LARGE, Product.CLOTHES, 10);
        generator.poolShutDown();

        try {
            generator.poolAwaitTermination(180);
        } catch (InterruptedException e) {
            fail("An interrupt exception occurred during test!");
        }

        ArrayList<Logger> loggerList = generator.getLoggerList();
        for (Logger logger : loggerList) {
            assertEquals(logger.getIsInterruptedFlag(), false);
            assertEquals(logger.getTypeOfShip(), logger.getTypeOfDock());
            Integer trueStep = 0;
            assertEquals(6, logger.getStates().size());
            for (Integer step : logger.getStates()) {
                assertEquals(step, trueStep);
                ++trueStep;
            }
        }
    }
}