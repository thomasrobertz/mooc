package de.robertz.mooc;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        LeaderElection le = new LeaderElection();
        le.connect();
        le.volunteer();
        le.elect();
        le.run();
        le.close(); // Main thread woke up by notifyAll
        System.out.println("Disconnected.");
    }
}