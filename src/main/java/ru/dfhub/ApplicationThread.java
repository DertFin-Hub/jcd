package ru.dfhub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationThread extends Thread {

    private final String targetJar;
    private Process process;

    public ApplicationThread(String target) {
        targetJar = target;
        this.start();
        System.out.println("[JCD] Application loaded");
    }

    @Override
    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", targetJar);
            process = pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {}
        }
    }

    public void stopProcess() {
        process.destroy();
    }
}
