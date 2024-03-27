package de.robertz.mooc;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

//@Slf4j
public class LeaderElection implements Watcher {
    private final static String SERVER = "localhost:2181";
    private ZooKeeper zk;

    public LeaderElection connect() throws IOException {
        Watcher watcher = this; // To make clear why we're implementing Watcher
        zk = new ZooKeeper(SERVER, 3000, watcher);
        return this;
    }

    public void run() throws InterruptedException {
        synchronized (zk) {
            zk.wait();
        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch(watchedEvent.getType()) {
            case None -> {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected.");
                } else {
                    synchronized (zk) {
                        System.out.println("Received disconnection event");
                        zk.notifyAll();
                    }
                }
            }
        }
    }
}
