package org.example;

import java.util.concurrent.Semaphore;

public class Tunnel {
    public final Semaphore semaphore = new Semaphore(5);
}
