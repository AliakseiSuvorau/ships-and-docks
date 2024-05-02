## Task
There are transport `Ships`, which are made by `ShipGenerator`. They sail from the generator into the `Tunnel`. At a time no more than 5 ships can be in the tunnel. Then, the ships sail to the `Dock` they need to be loaded.
It takes 1 second for the ships for sail through the tunnel. 

#### Types of ships
`SMALL`, `MEDIUM` and `LARGE` ships can carry 10, 50 and 100 tons of products at a time. To load them it takes `, 5 and 10 seconds correspondingly.

Ships are being loaded with 3 types of products: `BREAD`, `BANANA` and `CLOTHES` (only one type of product at a time). As well, there are 3 types of docks of the same types. The ship carrying particular type of product can approach only the appropriate dock.

Ships are threads.

## Architectural solutions
For the tunnel I've used a semaphore to keep no more than 5 threads using it.
For the docks - an ordinary lock. 

## Tests
There are 5 tests for ships of the same type of product, same size, than mixed tests and a stress test.
Each test checks:
* The thread was not interrupted during its work
* The ship was loaded on the appropriate dock
* The ship went through all stages
* The ship did everything in the right order

Approximate time of testing: 5 minutes.

#### Example of `main`
```
        Tunnel tunnel = new Tunnel();
        Dock breadDock = new Dock(Product.BREAD);
        Dock bananasDock = new Dock(Product.BANANAS);
        Dock clothesDock = new Dock(Product.CLOTHES);
        ShipGenerator generator = new ShipGenerator(tunnel, 
                                                    breadDock, bananasDock, clothesDock);
        generator.makeShip(Size.SMALL, Product.BREAD, 1);
        generator.poolShutDown();
```