package domain;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Race {
    public static final int FINISH_LINE_DISTANCE = 100;
    private final ConcurrentLinkedQueue<String> finishers = new ConcurrentLinkedQueue<>();

    public void setFinisher(String name) {
        finishers.add(name);
    }

    public synchronized String getResults() {
        StringBuilder finalResults = new StringBuilder();
        finalResults.append("\nFinishers in order:\n");
        int position = 1;
        for (String finisher : finishers) {
            finalResults.append(position++).append(". ").append(finisher).append("\n");
        }
        return finalResults.toString();
    }

}
