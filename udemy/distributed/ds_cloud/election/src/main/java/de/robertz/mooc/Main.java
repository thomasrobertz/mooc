package de.robertz.mooc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        LeaderElection le = new LeaderElection();
        le.connect().run();
        le.close(); // Main thread woke up by notifyAll
        System.out.println("Disconnected.");
    }
}