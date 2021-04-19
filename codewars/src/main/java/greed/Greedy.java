import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

// https://www.codewars.com/kata/5270d0d18625160ada0000e4
public class Greed {

    public static class LedgerEntry {
        public int amountOccurred = 0;
        public int setsOccurred = 0;
        public int total = 0;
        public boolean setsIncreased = false;
        public LedgerEntry(int amountOccurred, int setsOccurred, int total) {
            this.amountOccurred = amountOccurred;
            this.setsOccurred = setsOccurred;
            this.total = total;
        }
        public LedgerEntry(int amountOccurred, int setsOccurred, int total, boolean setsIncreased) {
            this.amountOccurred = amountOccurred;
            this.setsOccurred = setsOccurred;
            this.total = total;
            this.setsIncreased = setsIncreased;
        }
    }

    public static class Ledger {
        Stack<LedgerEntry> ledgerEntries = new Stack<LedgerEntry>();
        public void add(LedgerEntry ledgerEntry) {
            ledgerEntries.push(ledgerEntry);
        }
        public LedgerEntry current() {
            return ledgerEntries.lastElement();
        }
        public LedgerEntry undo() {
            return ledgerEntries.pop();
        }
        public void reset() {
            ledgerEntries.clear();
        }
    }

    public static class ScoringRule {

        private ScoringRule next;
        private int pip;
        private int amountRequired;
        private int setsAllowed;
        private int points;
        private Ledger ledger = new Ledger();
        private LedgerEntry nextState;

        public ScoringRule(int pip, int amountRequired, int setsAllowed, int points) {
            initialize(pip, amountRequired, setsAllowed, points, null);
            reset();
        }

        public ScoringRule(int pip, int amountRequired, int setsAllowed, int points, ScoringRule next) {
            initialize(pip, amountRequired, setsAllowed, points, next);
            reset();
        }

        private void initialize(int pip, int amountRequired, int setsAllowed, int points, ScoringRule next) {
            this.pip = pip;
            this.amountRequired = amountRequired;
            this.setsAllowed = setsAllowed;
            this.points = points;
            this.next = next;
        }

        public void reset() {
            ledger.reset();
            ledger.add(new LedgerEntry(0,0,0));
            nextState = ledger.current();
            if (next instanceof ScoringRule) {
                next.reset();
            }
        }

        public boolean acceptPip(int pip) {
            if(pip == this.pip) {
                boolean nextAccepted = false;
                if (next instanceof ScoringRule) {
                    nextAccepted = next.acceptPip(pip);
                }
                if (full()) {
                    return nextAccepted;
                }
                if(match()) {
                    if(next instanceof ScoringRule) {
                        next.previous();
                    }
                }
                return true;
            }
            return false;
        }

        private boolean match() {
            addState(new LedgerEntry(nextState.amountOccurred + 1, nextState.setsOccurred, nextState.total, nextState.setsIncreased));
            if (nextState.amountOccurred >= amountRequired) {
                addState(new LedgerEntry(nextState.amountOccurred, nextState.setsOccurred + 1, nextState.total + points, true));
                return true;
            }
            return false;
        }

        private void addState(LedgerEntry entry) {
            ledger.add(entry);
            nextState = ledger.current();
        }

        public boolean full() {
            return nextState.setsOccurred == setsAllowed;
        }

        public int getTotal() {
            int localTotal = nextState.total;
            if (next instanceof ScoringRule) {
                localTotal += next.getTotal();
            }
            return localTotal;
        }

        private void previous() {
            ledger.undo();
            nextState = ledger.current();
            while (nextState.setsIncreased) {
                ledger.undo();
                nextState = ledger.current();
            }
        }
    }

    public static class Scorer {

        List<ScoringRule> scoringRules;

        public Scorer(List<ScoringRule> scoringRules) {
            this.scoringRules = scoringRules;
        }

        public void pip(int pip) {
            for(ScoringRule rule : scoringRules) {
                if(rule.acceptPip(pip)) {
                    break;
                }
            }
        }

        public int tally() {
            return scoringRules.stream().mapToInt(ScoringRule::getTotal).reduce(0, Integer::sum);
        }

        public void reset() {
            scoringRules.forEach(ScoringRule::reset);
        }
    }

    public static int greedy(int[] dice) {

        Scorer scorer = new Scorer(List.of(
                new ScoringRule(1, 3, 1, 1000,
                        new ScoringRule(1, 1,2, 100)),
                new ScoringRule(2, 3, 1, 200),
                new ScoringRule(3, 3,1, 300),
                new ScoringRule(4, 3,1, 400),
                new ScoringRule(5, 3,1, 500,
                        new ScoringRule(5, 1,2, 50)),
                new ScoringRule(6, 3,1, 600)));

        IntStream.of(dice).forEach(p -> scorer.pip(p));
        return scorer.tally();
    }

    public static void main(String[] args) {

        System.out.println(greedy(new int[]{5, 1, 3, 4, 1}));

        /*
        Scorer scorer = new Scorer(List.of(
            new ScoringRule(1, 3, 1, 1000,
                    new ScoringRule(1, 1,2, 100)),
            new ScoringRule(2, 3, 1, 200),
            new ScoringRule(3, 3,1, 300),
            new ScoringRule(4, 3,1, 400),
            new ScoringRule(5, 3,1, 500,
                    new ScoringRule(5, 1,2, 50)),
            new ScoringRule(6, 3,1, 600)));

        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(3);
        scorer.pip(1);
        System.out.println(scorer.tally());
        scorer.reset();

        scorer.pip(1);
        scorer.pip(3);
        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(1);
        System.out.println(scorer.tally());
        scorer.reset();

        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(1);
        scorer.pip(1);
        System.out.println(scorer.tally());
        scorer.reset();

        scorer.pip(5);
        scorer.pip(1);
        scorer.pip(3);
        scorer.pip(4);
        scorer.pip(1);
        System.out.println(scorer.tally());
        scorer.reset();

        scorer.pip(2);
        scorer.pip(4);
        scorer.pip(4);
        scorer.pip(5);
        scorer.pip(4);
        System.out.println(scorer.tally());
        scorer.reset();
        */
    }
}
