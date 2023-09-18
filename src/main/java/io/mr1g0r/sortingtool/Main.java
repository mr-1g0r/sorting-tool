package sorting;

import sorting.menu.Menu;
import sorting.menu.Mode;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {

        final var input = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        Mode mode = Menu.parseArgs(args);
        mode.printStats(input);
    }
}
