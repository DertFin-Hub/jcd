package ru.dfhub;


import java.io.File;
import java.io.IOException;

public class Main {

    private static File target;
    private static long modifiedAt;
    private static ApplicationThread thread;

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help") || args[0].equals("-h")) {
            System.out.printf("Usage: java -jar %s target.jar%n", new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName()); return;
        }

        target = new File(args[0]);
        if (!target.exists()) { System.out.println("File not exists!"); return; }

        modifiedAt = target.lastModified();
        System.out.println("[JCD] Starting application...");
        thread = new ApplicationThread(target.getName());
        while (true) {
            try {
                Thread.sleep(5*1000);
            }
            catch (InterruptedException e) {
                System.out.println("[JCD] Stopping application...");
                System.exit(0);
            }
            if (!target.exists()) {
                System.out.println("[JCD] The target file has ceased to exist. Waiting for update...");
                continue;
            }
            if (target.lastModified() != modifiedAt) {
                System.out.println("[JCD] Target file is modified! Restarting application...");
                thread.stopProcess();
                System.out.println("[JCD] Stopped old application");
                System.out.println("[JCD] Trying to load update...");
                thread = new ApplicationThread(target.getName());
            }
        }
    }
}