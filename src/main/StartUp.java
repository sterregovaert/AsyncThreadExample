package main;

import domain.Race;
import domain.Swimmer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class StartUp {
    public static void main(String[] args) {

        System.out.println("---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----");
        System.out.println("---- ---- Olympic games ---- Swimming 100m Freestyle ---- ----");
        System.out.println("---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----");

        String[] swimmers = {
                "Daiya Seto",
                "Adam Peaty",
                "Caeleb Dressel",
                "Michael Phelps",
                "Ryan Lochte",
//                "Nathan Adrian",
//                "Sun Yang",
//                "Kosuke Hagino",
//                "Chad le Clos",
//                "Joseph Schooling"
        };

        ExecutorService executorService = Executors.newFixedThreadPool(swimmers.length);
        try {
            Race race = new Race();
            for (String swimmer : swimmers) {
                executorService.execute(new Swimmer(race, swimmer));
            }

            executorService.shutdown();
            try {
                // Wait for all tasks to complete or timeout after given minutes
                if (!executorService.awaitTermination(2, TimeUnit.MINUTES)) {
                    // If timeout occurs, forcefully shutdown all running tasks
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                // If the current thread is interrupted while waiting, forcefully shutdown all running tasks
                executorService.shutdownNow();
                // Preserve the interrupt status of the current thread
                Thread.currentThread().interrupt();
            }

            System.out.println();
            System.out.println("---- ---- ---- ----");
            System.out.println("---- Race is over ----");
            System.out.println("---- ---- ---- ----");
            System.out.print(race.getResults());

        } finally {
            if (!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
        }
    }
}

