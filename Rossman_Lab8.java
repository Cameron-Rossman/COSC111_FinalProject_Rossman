
/**
 * Lab 8: Working with Two-Dimensional Arrays
 * ---------------------------------------
 * In this activity, you’ll create and work with a 2D array.
 * Your program will store and display a classroom seating chart.
 * @Author: Cameron Rossman
 * @Version: 11/10/2025
 * @class: cosc-111
 */
import java.util.Scanner;

public class Rossman_Lab8 {
    public static void printSeatingChart(String[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");

            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // TODO 1: Declare and initialize a 2D array of Strings called 'seats'.
        // Create a 3x3 grid of student names.
        // Example:
        // String[][] seats = {
        // {"Alice", "Ben", "Carla"},
        // {"David", "Ella", "Frank"},
        // {"Grace", "Henry", "Ivy"}
        // };

        String[][] seats = {
                { "Alice", "Ben", "Carla" },
                { "David", "Ella", "Frank" },
                { "Grace", "Henry", "Ivy" }

        };

        // TODO 2: Print the seating chart using nested for loops.
        // THIS SHOULD BE A METHOD CALL.
        // Each row should appear on its own line.
        // Example Output:
        // Alice Ben Carla
        // David Ella Frank
        // Grace Henry Ivy
        System.out.println("Seating chart before update:");
        printSeatingChart(seats);

        // TODO 3: Change one student’s name in the array (for example, replace "Ben"
        // with "Brian").
        // Then print the updated seating chart again.
        seats[0][1] = "Brian";
        System.out.println();
        System.out.println("Seating chart after update: ");
        printSeatingChart(seats);

        // TODO 4:
        // Use a Scanner to let the user enter a row and column number (0–2).
        // Print out the name of the student sitting in that position.
        Scanner kb = new Scanner(System.in);
        int row = 0;
        int column = 0;

        System.out.println("Enter a row number");
        row = kb.nextInt();
        System.out.println("Enter a column number");
        column = kb.nextInt();
        System.out.println("The student at that seat is: " + seats[row][column]);

        // count how many letters
        System.out.println("Enter a letter to count how many names start with it: ");
        char letter = kb.next().toUpperCase().charAt(0);
        int count = 0;
        // loop to count
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].toUpperCase().charAt(0) == letter) {
                    count++;
                }

            }
            System.out.println("There are " + count + " names that start with " + letter + ".");
        }

        // Prompt user to create their own array
        System.out.println("Enter number of rows: ");
        int createRows = kb.nextInt();
        System.out.println("Enter number of columns: ");
        int createColumns = kb.nextInt();
        String[][] customSeats = new String[createRows][createColumns];

        kb.nextLine();

        // Loop to create custom seating chart
        for (int joe = 0; joe < customSeats.length; joe++) {
            for (int sam = 0; sam < customSeats[joe].length; sam++) {
                System.out.print("Enter name for seat [" + joe + "][" + sam + "]: ");
                customSeats[joe][sam] = kb.nextLine();
            }
        }
        System.out.println("\n Custom Seating Chart:");
        printSeatingChart(customSeats);

        kb.close();

    }
}

/*
 * Code output sample
 * 
 * Seating chart before update:
 * Alice Ben Carla
 * David Ella Frank
 * Grace Henry Ivy
 * 
 * Seating chart after update:
 * Alice Brian Carla
 * David Ella Frank
 * Grace Henry Ivy
 * Enter a row number
 * 2
 * Enter a column number
 * 2
 * The student at that seat is: Ivy
 * Enter a letter to count how many names start with it:
 * a
 * There are 1 names that start with A.
 * There are 1 names that start with A.
 * There are 1 names that start with A.
 * Enter number of rows:
 * 2
 * Enter number of columns:
 * 2
 * Enter name for seat [0][0]: kevin
 * Enter name for seat [0][1]: evan
 * Enter name for seat [1][0]: cam
 * Enter name for seat [1][1]: joe
 * 
 * Custom Seating Chart:
 * kevin evan
 * cam joe
 * 
 * 
 * 
 * 
 * 
 */
