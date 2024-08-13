package domain;

import java.security.SecureRandom;

import static domain.Race.FINISH_LINE_DISTANCE;

public class Swimmer implements Runnable {
    private final Race race;
    private final String name;

    public Swimmer(Race race, String name) {
        this.race = race;
        this.name = name;
    }

    @Override
    public void run() {
        SecureRandom random = new SecureRandom();

        int distanceCovered = 0;
        while (distanceCovered < FINISH_LINE_DISTANCE) {
            try {
                // Sleep in between metres covered
                // between 4.5 seconds + 1 second per 10 metres
                int sleepTime = 4500 + random.nextInt(1001);
                Thread.sleep(sleepTime); // ms

                // Increment distance covered
                distanceCovered += 10;

                // Print progress
                System.out.printf("%-20s has covered %d meters.%n", name, distanceCovered);

            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted");
                return;
                //Thread.currentThread().interrupt();
            }
        }

        race.setFinisher(name);

    }
}