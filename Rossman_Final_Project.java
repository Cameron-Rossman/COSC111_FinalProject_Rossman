
/*
 * Final Project Demo
 * -------------------------------------
* @author- Cameron Rossman
* @version- 12/15/2025
* @class- COSC-111
 */
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Rossman_Final_Project {

    public static void main(String[] args) {

        // Create the draft manager and start the command loop
        DraftManager dm = new DraftManager();
        dm.run();
    }

    // Represents a single fantasy football player with basic info
    public static class Player {

        private int playerId;
        private String name;
        private String team;
        private String position;
        private double adp;
        private int overallRank;

        public Player(int playerId, int overallRank, String name,
                String team, String position,
                double adp) {
            this.playerId = playerId;
            this.overallRank = overallRank;
            this.name = name;
            this.team = team;
            this.position = position;
            this.adp = adp;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getOverallRank() {
            return overallRank;
        }

        public String getName() {
            return name;
        }

        public String getTeam() {
            return team;
        }

        public double getAdp() {
            return adp;
        }

        public String getPosition() {
            return position;
        }

        // format ID as P0001, P0002, etc.
        private String formatId() {
            return String.format("P%04d", playerId);
        }

        public void setPosition(String position) {
            this.position = position;
        }

        @Override
        public String toString() {
            // Print player info in a formatted string
            return String.format(
                    "ID:%-6s | Rank:%4d | %-2s | %-25s | %-3s",
                    formatId(),
                    overallRank,
                    position,
                    name,
                    team);

        }
    }

    // Manages all draft logic: storing players, commands, and user interaction
    public static class DraftManager {

        // Max players you expect to load
        private static final int MAX_PLAYERS = 2000;

        // Undrafted lists by position
        private Player[] undraftedQb;
        private int undraftedQbCount;

        private Player[] undraftedRb;
        private int undraftedRbCount;

        private Player[] undraftedWr;
        private int undraftedWrCount;

        private Player[] undraftedTe;
        private int undraftedTeCount;

        private Player[] undraftedK;
        private int undraftedKCount;

        // All undrafted players together
        private Player[] undraftedAll;
        private int undraftedAllCount;

        // players you drafted
        private Player[] draftedPlayers;
        private int draftedCount;

        // Unique ID Tracker
        private int nextPlayerId = 1;

        private Scanner scanner;

        // Constructor
        public DraftManager() {
            undraftedQb = new Player[MAX_PLAYERS];
            undraftedRb = new Player[MAX_PLAYERS];
            undraftedWr = new Player[MAX_PLAYERS];
            undraftedTe = new Player[MAX_PLAYERS];
            undraftedK = new Player[MAX_PLAYERS];
            undraftedAll = new Player[MAX_PLAYERS];

            draftedPlayers = new Player[MAX_PLAYERS];

            // position and all player counts
            undraftedQbCount = 0;
            undraftedRbCount = 0;
            undraftedWrCount = 0;
            undraftedTeCount = 0;
            undraftedAllCount = 0;
            draftedCount = 0;

            scanner = new Scanner(System.in);

            // loads all players
            loadData();
        }

        // loads all players first
        private void loadData() {
            System.out.println("loadData() running... loading CSV files...");

            // Overall rankings create Player objects and IDs
            loadOverallCsv("Overall_data.csv");

            // match + assign positions
            loadPositionCsv("QB_data.csv", "QB");
            loadPositionCsv("RB_data.csv", "RB");
            loadPositionCsv("WR_data.csv", "WR");
            loadPositionCsv("TE_data.csv", "TE");
            loadPositionCsv("K_data.csv", "K");

            System.out.println("Total players loaded (overall undraftedAll): " + undraftedAllCount);
        }

        // Main loop: repeatedly read user input and process commands
        public void run() {
            System.out.println("Welcome to the Fantasy Draft Manager!");
            System.out.println("Type 'help' to see available commands.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase();

                // exit
                if (input.equals("exit") || input.equals("quit")) {
                    System.out.println("Exiting draft manager. Goodbye!");
                    break;
                }

                processCommand(input);
            }
        }

        // Reads the overall CSV file
        private void loadOverallCsv(String fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine(); // header
                if (line == null)
                    return;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 6)
                        continue;

                    String rankStr = parts[0].trim().replace("\"", "");
                    int rank;
                    try {
                        rank = Integer.parseInt(rankStr);
                    } catch (NumberFormatException e) {
                        rank = -1;
                    }

                    String playerName = parts[2].trim();
                    String team = parts[3].trim();

                    double adp;
                    try {
                        adp = Double.parseDouble(parts[5].trim());
                    } catch (NumberFormatException e) {
                        adp = 999.9;
                    }

                    // Create the player
                    Player p = new Player(
                            nextPlayerId++,
                            rank,
                            playerName,
                            team,
                            "",
                            adp);

                    undraftedAll[undraftedAllCount++] = p;
                }
            } catch (IOException e) {
                System.out.println("Error loading file " + fileName + ": " + e.getMessage());
            }
        }

        // reads position csv files and matches players that exist in undraftedAll
        private void loadPositionCsv(String fileName, String position) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line = br.readLine(); // header
                if (line == null) {
                    System.out.println("File " + fileName + " is empty.");
                    return;
                }

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 6)
                        continue;

                    String playerName = parts[2].trim();
                    String team = parts[3].trim();

                    Player p = findPlayerInAll(playerName, team);
                    if (p == null)
                        continue;

                    p.setPosition(position);

                    if (position.equals("QB"))
                        undraftedQb[undraftedQbCount++] = p;
                    else if (position.equals("RB"))
                        undraftedRb[undraftedRbCount++] = p;
                    else if (position.equals("WR"))
                        undraftedWr[undraftedWrCount++] = p;
                    else if (position.equals("TE"))
                        undraftedTe[undraftedTeCount++] = p;
                    else if (position.equals("K"))
                        undraftedK[undraftedKCount++] = p;
                }
            } catch (IOException e) {
                System.out.println("Error loading file " + fileName + ": " + e.getMessage());
            }
        }

        private Player findPlayerInAll(String name, String team) {
            for (int i = 0; i < undraftedAllCount; i++) {
                Player p = undraftedAll[i];
                if (p.getName().equalsIgnoreCase(name) && p.getTeam().equalsIgnoreCase(team)) {
                    return p;
                }
            }
            return null;
        }

        // Decide what to do based on the user's command
        private void processCommand(String input) {
            if (input.equals("help")) {
                showHelp();
            } else if (input.equals("show qb")) {
                showQb();
            } else if (input.equals("show rb")) {
                showRb();
            } else if (input.equals("show wr")) {
                showWr();
            } else if (input.equals("show te")) {
                showTe();
            } else if (input.equals("show k")) {
                showK();
            } else if (input.equals("show all")) {
                showAll();
            } else if (input.startsWith("draft ")) {
                int id = parseId(input);
                if (id == -1) {
                    System.out.println("Invalid draft command. Use: draft <id>");
                } else {
                    draftPlayer(id);
                }
            } else if (input.startsWith("cross ")) {
                int id = parseId(input);
                if (id == -1) {
                    System.out.println("Invalid cross command. Use: cross <id>");
                } else {
                    crossPlayer(id);
                }
            } else if (input.equals("show team")) {
                showTeam();
            } else {
                System.out.println("Unknown command. Type 'help' to see options.");
            }
        }

        // help menu with commands
        private void showHelp() {
            System.out.println("Available commands:");
            System.out.println("show all - show all players");
            System.out.println("help - show this menu");
            System.out.println("show qb - show quarterbacks");
            System.out.println("show rb - show running backs");
            System.out.println("show wr - show wide receivers");
            System.out.println("show te - show tight ends");
            System.out.println("show k - show kickers");
            System.out.println("draft <id> - draft a player");
            System.out.println("cross <id> - cross a player off");
            System.out.println("show team - show your drafted team");
            System.out.println("exit - quit the program");
        }

        private void showQb() {
            for (int i = 0; i < Math.min(10, undraftedQbCount); i++) {
                System.out.println(undraftedQb[i]);
            }
        }

        private void showRb() {
            for (int i = 0; i < Math.min(10, undraftedRbCount); i++) {
                System.out.println(undraftedRb[i]);
            }
        }

        private void showWr() {
            for (int i = 0; i < Math.min(10, undraftedWrCount); i++) {
                System.out.println(undraftedWr[i]);
            }
        }

        private void showTe() {
            for (int i = 0; i < Math.min(10, undraftedTeCount); i++) {
                System.out.println(undraftedTe[i]);
            }
        }

        private void showK() {
            for (int i = 0; i < Math.min(10, undraftedKCount); i++) {
                System.out.println(undraftedK[i]);
            }
        }

        private void showAll() {
            for (int i = 0; i < Math.min(20, undraftedAllCount); i++) {
                System.out.println(undraftedAll[i]);
            }
        }

        // draft process flow helpers
        private int parseId(String input) {
            // expects: "draft 12" (or "cross 12" later)
            String[] parts = input.trim().split("\\s+");
            if (parts.length < 2)
                return -1;
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        private void draftPlayer(int id) {
            int allIndex = findIndexById(undraftedAll, undraftedAllCount, id);
            if (allIndex == -1) {
                System.out.println("Player ID " + id + " not found in undrafted list.");
                return;
            }

            Player p = undraftedAll[allIndex];
            System.out.println("Draft: " + p);
            System.out.print("Do you really want to draft them? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (!confirm.equals("y")) {
                System.out.println("Draft cancelled.");
                return;
            }

            // Remove from undraftedAll
            removeAtIndex(undraftedAll, allIndex, undraftedAllCount);
            undraftedAllCount--;

            // Remove from the position list
            removeFromPositionArray(p);

            // Add to drafted list
            draftedPlayers[draftedCount++] = p;

            System.out.println("Drafted: " + p.getName() + " (" + p.getPosition() + ")");
        }

        // remove player from the correct position list based on position
        private void removeFromPositionArray(Player p) {
            String pos = p.getPosition();

            if (pos.equals("QB")) {
                int idx = findIndexById(undraftedQb, undraftedQbCount, p.getPlayerId());
                if (idx != -1) {
                    removeAtIndex(undraftedQb, idx, undraftedQbCount);
                    undraftedQbCount--;
                }
            } else if (pos.equals("RB")) {
                int idx = findIndexById(undraftedRb, undraftedRbCount, p.getPlayerId());
                if (idx != -1) {
                    removeAtIndex(undraftedRb, idx, undraftedRbCount);
                    undraftedRbCount--;
                }
            } else if (pos.equals("WR")) {
                int idx = findIndexById(undraftedWr, undraftedWrCount, p.getPlayerId());
                if (idx != -1) {
                    removeAtIndex(undraftedWr, idx, undraftedWrCount);
                    undraftedWrCount--;
                }
            } else if (pos.equals("TE")) {
                int idx = findIndexById(undraftedTe, undraftedTeCount, p.getPlayerId());
                if (idx != -1) {
                    removeAtIndex(undraftedTe, idx, undraftedTeCount);
                    undraftedTeCount--;
                }
            } else if (pos.equals("K")) {
                int idx = findIndexById(undraftedK, undraftedKCount, p.getPlayerId());
                if (idx != -1) {
                    removeAtIndex(undraftedK, idx, undraftedKCount);
                    undraftedKCount--;
                }
            }
        }

        private int findIndexById(Player[] arr, int count, int id) {
            for (int i = 0; i < count; i++) {
                if (arr[i] != null && arr[i].getPlayerId() == id)
                    return i;
            }
            return -1;
        }

        private void removeAtIndex(Player[] arr, int index, int count) {
            for (int i = index; i < count - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[count - 1] = null;
        }

        // remove players that were drafted by other people
        private void crossPlayer(int id) {
            int allIndex = findIndexById(undraftedAll, undraftedAllCount, id);
            if (allIndex == -1) {
                System.out.println("Player ID " + id + " not found in undrafted list.");
                return;
            }

            Player p = undraftedAll[allIndex];
            System.out.println("Cross off: " + p);
            System.out.print("Do you really want to cross the player off? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (!confirm.equals("y")) {
                System.out.println("Cross cancelled.");
                return;
            }

            // Remove from undraftedAll
            removeAtIndex(undraftedAll, allIndex, undraftedAllCount);
            undraftedAllCount--;

            // Remove from the correct position array
            removeFromPositionArray(p);

            System.out.println("Crossed off: " + p.getName() + " (" + p.getPosition() + ")");
        }

        private void showTeam() {
            if (draftedCount == 0) {
                System.out.println("Your team is empty (no drafted players yet).");
                return;
            }

            System.out.println("Your drafted team:");
            for (int i = 0; i < draftedCount; i++) {
                System.out.println(draftedPlayers[i]);
            }
        }

    }
}
