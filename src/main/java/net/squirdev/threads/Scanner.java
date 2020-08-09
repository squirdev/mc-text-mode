package net.squirdev.threads;

public class Scanner extends Thread {
    public void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            String s = scanner.nextLine();
            Client.say(s, true);
        }
    }
}
