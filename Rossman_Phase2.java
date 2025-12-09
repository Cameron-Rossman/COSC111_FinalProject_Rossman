import java.util.Scanner;

public class Rossman_Phase2 {

    public static void main(String[] args) {
        // Create a test player to verify the Player class works
        Player testPlayer = new Player(1, "Test Name", "TEST", "QB", 5.5);
        System.out.println(testPlayer);  // Uses toString() to print details

        // Create the draft manager and start the command loop
        DraftManager dm = new DraftManager();
        dm.run();
    }

    // Represents a single fantasy football player with basic info and ADP
    public static class Player {

        private int playerId;
        private String name;
        private String team;
        private String position;
        private double adp;

        public Player(int playerId, String name,
                      String team, String position,
                      double adp) {
            this.playerId = playerId;
            this.name = name;
            this.team = team;
            this.position = position;
            this.adp = adp;
        }

        public int getPlayerId() {
            return playerId;
        }

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }

        public String getPosition() {
            return position;
        }

        public double getAdp() {
            return adp;
        }

        @Override
        public String toString() {
            return "ID: " + playerId +
                   " | " + position +
                   " | " + name +
                   " | " + team +
                   " | ADP: " + adp;
        }
    }

    // Manages all draft logic: storing players, commands, and user interaction
    public static class DraftManager {

        // Max players you expect to load 
        private static final int MAX_PLAYERS = 500;

        // Undrafted arrays
        private Player[] undraftedQb;
        private int undraftedQbCount;

        private Player[] undraftedRb;
        private int undraftedRbCount;

        private Player[] undraftedWr;
        private int undraftedWrCount;

        private Player[] undraftedTe;
        private int undraftedTeCount;

        // All undrafted players together
        private Player[] undraftedAll;
        private int undraftedAllCount;

        // Drafted players 
        private Player[] draftedPlayers;
        private int draftedCount;

        private Scanner scanner;

        // Constructor
        public DraftManager() {
            undraftedQb = new Player[MAX_PLAYERS];
            undraftedRb = new Player[MAX_PLAYERS];
            undraftedWr = new Player[MAX_PLAYERS];
            undraftedTe = new Player[MAX_PLAYERS];
            undraftedAll = new Player[MAX_PLAYERS];

            draftedPlayers = new Player[MAX_PLAYERS];

            scanner = new Scanner(System.in);

            // We will fill this in later
            loadData();
        }

        // Placeholder for loading player data (to be implemented later)
        private void loadData() {
            System.out.println("loadData() running... (not implemented yet)");
        }

        // Main loop: repeatedly read user input and process commands
        public void run() {
            System.out.println("Welcome to the Fantasy Draft Manager!");
            System.out.println("Type 'help' to see available commands.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("exit") || input.equals("quit")) {
                    System.out.println("Exiting draft manager. Goodbye!");
                    break;
                }

                processCommand(input);
            }
        }

        // Decide what to do based on the user's command
        private void processCommand(String input) {
            if (input.equals("help")) {
                showHelp();
            } else {
                System.out.println("Unknown command. Type 'help' to see options.");
            }
        }

        // Show a simple help menu with available commands
        private void showHelp() {
            System.out.println("Available commands (so far):");
            System.out.println("  help  - Show this help menu");
            System.out.println("  exit  - Quit the program");
        }
    }
}
