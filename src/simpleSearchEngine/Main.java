package simpleSearchEngine;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static List<String> listOfPeople = new ArrayList<>();
    static List<String> foundPeople = new ArrayList<>();

    public static void findAPerson() {
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        switch (scanner.nextLine()) {
            case "ALL" -> findAll();
            case "ANY" -> findAny();
            case "NONE" -> findNone();
        }
    }

    public static void findAny() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;

        System.out.println();

        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (person.toLowerCase().contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.add(person);
                    break;
                }
            }
        }
        foundPeople(count);
    }

    public static void findAll() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;


        System.out.println();
        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (Arrays.asList(person.toLowerCase().split(" ")).contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.add(person);
                }
            }
        }
        foundPeople(count);
    }

    public static void findNone() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchArray = scanner.nextLine().split(" ");
        int count = 0;

        foundPeople = new ArrayList<>(listOfPeople);

        System.out.println();

        for (String person : listOfPeople) {
            for (String dat : searchArray) {
                if (Arrays.asList(person.toLowerCase().split(" ")).contains(dat.toLowerCase())) {
                    count++;
                    foundPeople.remove(person);
                }
            }
        }
        foundPeople(count);
    }

    public static void foundPeople(int count) {
        if (count > 0) {
            System.out.println(count + " persons found:");
            for (String foundPerson : foundPeople) {
                System.out.println(foundPerson);
            }
            foundPeople.clear();
        } else {
            System.out.println("No matching people found.");
        }
    }

    public static void printAllPeople() {
        System.out.println("\n=== List of people ===");
        for (String listOfPerson : listOfPeople) {
            System.out.println(listOfPerson);
        }
    }

    public static void printMenu() {
        System.out.println("""

                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");
    }

    public static void main(String[] args) {
        String fileSource = args[1];

        try (FileReader fileReader = new FileReader(fileSource)) {
            Scanner fileScanner = new Scanner(fileReader);
            while (fileScanner.hasNext()) {
                listOfPeople.add(fileScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "1" -> findAPerson();
                case "2" -> printAllPeople();
                case "0" -> System.exit(0);
                default -> System.out.println("Incorrect option! Try again.");
            }
        }
    }
}