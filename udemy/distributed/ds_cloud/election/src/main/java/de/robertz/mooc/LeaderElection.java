package de.robertz.mooc;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

//@Slf4j
public class LeaderElection implements Watcher {
    private final static String SERVER = "localhost:2181";
    private final static String ELECTION_PATH = "/election";
    private ZooKeeper zk;

    private String voteTicket; // The created ephemeral znode with this node's registration order

    public void volunteer() throws InterruptedException, KeeperException {
        // Create a znode to volunteer for election.
        String znodePath = zk.create(ELECTION_PATH + "/c_",
                new byte[] { }, // Content, empty in this case.
                ZooDefs.Ids.OPEN_ACL_UNSAFE, // We don't care about this here
                CreateMode.EPHEMERAL_SEQUENTIAL); // Ephemeral = will be deleted once ZK is stopped

        System.out.println(">>>>> Volunteered with vote ticket " + znodePath);
        voteTicket = znodePath.replace(ELECTION_PATH + "/", "");
    }

    public void elect() throws InterruptedException, KeeperException {
        List<String> children = zk.getChildren(ELECTION_PATH, false);
        Collections.sort(children);
        String leader = children.get(0);
        if(leader.equals(voteTicket)) {
            // The smallest registered vote is this node's ticket number
            // therefore, this node has become the leader
            System.out.println(">>>>> This node won the election process and is now leader.");
        }
        else {
            System.out.println(">>>>> " + leader + " is the leader.");
        }
    }

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
            case None:
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
