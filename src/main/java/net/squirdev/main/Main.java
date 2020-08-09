package net.squirdev.main;

import net.squirdev.threads.Client;
import net.squirdev.threads.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        scanner.start();

        Client client = new Client(args[0], args[1], args[2], 25565);
        client.start();
    }
}
