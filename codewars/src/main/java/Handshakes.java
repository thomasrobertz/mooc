import java.util.ArrayList;
import java.util.List;

// https://www.codewars.com/kata/5574835e3e404a0bed00001b
public class Handshakes {

    public static void main(String[] args) {
        int shakes = 10; // 5
        //shakes = 6; // 4
        //shakes = 7; // 5
        shakes = 0; // 1

        Observer o = new Observer(shakes);

        do {
            o.addFarmer();
        } while (o.needsMore());

        System.out.println(o.farmers.size());
    }

    public static class Observer {
        List<Farmer> farmers = new ArrayList<Farmer>();
        private int shakeCounter = 0;
        private int shakesToMeet;
        public Observer(int shakesToMeet) {
            this.shakesToMeet = shakesToMeet;
        }
        public void addFarmer() {
            Farmer farmer = new Farmer(this);
            farmers.forEach(f -> f.shake(farmer));
            farmers.add(farmer);
        }
        public void reportShake() {
            shakeCounter++;
        }
        public boolean needsMore() {
            return shakeCounter < shakesToMeet;
        }
    }

    public static class Farmer {
        private Observer observer;
        private List<Farmer> handshakedWith = new ArrayList<Farmer>();
        public Farmer(Observer observer) {
            this.observer = observer;
        }
        public boolean shake(Farmer farmer) {
            if(handshakedWith.contains(farmer)) {
                return false;
            }
            handshakedWith.add(farmer);
            observer.reportShake();
            return true;
        }
    }
}
