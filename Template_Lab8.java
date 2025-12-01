/**
 * Lab 8: Working with Two-Dimensional Arrays
 * ---------------------------------------
 * In this activity, you’ll create and work with a 2D array.
 * Your program will store and display a classroom seating chart.
 */
import java.util.Scanner;
public class Template_Lab8 {
    public static void main(String[] args) {
        // TODO 1: Declare and initialize a 2D array of Strings called 'seats'.
        // Create a 3x3 grid of student names.
        // Example:
        // String[][] seats = {
        //     {"Alice", "Ben", "Carla"},
        //     {"David", "Ella", "Frank"},
        //     {"Grace", "Henry", "Ivy"}
        // };

        // Scanner and TODO 4 
        Scanner kb=new Scanner(System.in);
        int row=0;
        int column=0;
        //TODO 4 let user select a position to receive the name
        System.out.println("Enter a row number");
        row=kb.nextInt();
        System.out.println("Enter a column number");
        column=kb.nextInt();
        System.out.println("The student at that seat is: " + seats[row][column]);

        String[][] seats = {
            {"Alice", "Ben", "Carla"},
            {"David", "Ella", "Frank"},
            {"Grace", "Henry", "Ivy"}

            
        };

        
        // TODO 2: Print the seating chart using nested for loops. 
        // THIS SHOULD BE A METHOD CALL.
        // Each row should appear on its own line.
        // Example Output:
        // Alice  Ben  Carla
        // David  Ella Frank
        // Grace  Henry Ivy


    printSeatingChart(seats);


        // TODO 3: Change one student’s name in the array (for example, replace "Ben" with "Brian").
        // Then print the updated seating chart again.
        seats[0][1] = "Brian";




        
        // TODO 4:
        // Use a Scanner to let the user enter a row and column number (0–2).
        // Print out the name of the student sitting in that position.
    }

            public static void printSeatingChart(String[][] seats){
        for (int i=0; i < seats.length; i++){
            for (int j=0; j< seats[i].length; j++)
            System.out.print(seats[i][j] + " ");
        }
        System.out.println();
    }
}
