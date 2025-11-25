import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GraphFileReader rdr = new GraphFileReader();
        Scanner userInput = new Scanner(System.in);
        Graph graph;
        int input = 0;
        int input1;
        int input2ID1;
        int input2ID2;
        
        System.out.print("Enter file name (no need to include file extension): ");

        // file loading with exception handling
        do {
            try {
                String filename = userInput.next() + ".txt";
                graph = rdr.loadFile(filename);
                if (graph == null) {
                    System.out.println("Please select a valid file");
                    System.out.println("Enter file name (no need to include file extension): ");
                }
            } catch (Exception e) {
                System.out.println("Error reading file. Please try again.");
                System.out.println("Enter file name (no need to include file extension): ");
                userInput.nextLine(); // Clear any invalid input
                graph = null;
            }
        } while (graph == null);

        System.out.println();

        // Main menu loop 
        do {
            try {
                System.out.println("\nMAIN MENU");
                System.out.println("[1] Get friend list");
                System.out.println("[2] Get Connection");
                System.out.println("[3] Exit");
                System.out.println(); 
                System.out.print("Enter your choice: ");
                
                input = userInput.nextInt();

                switch (input) {
                    case 1:
                        System.out.print("Enter ID Number of Person: ");
                        try {
                            input1 = userInput.nextInt();
                            if (input1 < 0) {
                                System.out.println("Error: ID Number cannot be negative.");
                                break;
                            }
                            graph.getFriendsList(input1);
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Please enter a valid integer for ID Number.");
                            userInput.nextLine(); 
                        }

                        break;

                    case 2:
                        try {
                            System.out.print("Enter ID of first person: ");
                            input2ID1 = userInput.nextInt();
                            
                            System.out.print("Enter ID of second person: ");
                            input2ID2 = userInput.nextInt();
                            
                            // Validate inputs are non-negative
                            if (input2ID1 < 0 || input2ID2 < 0) {
                                System.out.println("Error: Account numbers cannot be negative. Try again.");
                            }
                            
                            else
                            {
                                boolean result = graph.getConnection(input2ID1, input2ID2);
                                
                                if (result) {
                                    System.out.println("There is a connection from Person " + input2ID1 + " to Person " + input2ID2);
                                    
                                    // get the path from Graph
                                    ArrayList<Integer> path = graph.getPath();
                                    
                                    // print the obtained friendship chain
                                    for (int i = 0; i < path.size() - 1; i++) {
                                        System.out.println(path.get(i) + " is friends with " + path.get(i + 1));
                                    }
                                } 
                                
                                else {
                                    System.out.println("No connection found between " + input2ID1 + " and " + input2ID2);
                                }
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Please enter valid integers for account numbers.");
                            userInput.nextLine(); // Clear invalid input
                        }
                        break;
                        
                    case 3:
                        System.out.println("Exiting...");
                        break;
                        
                    default:
                        System.out.println("Try again. Please enter 1, 2, or 3 only.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number (1, 2, or 3).");
                userInput.nextLine(); // clear the invalid input
                input = 0; // reset input to continue loop

            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                userInput.nextLine(); // Clear any invalid input
                input = 0; // Reset input to continue loop
            }

            System.out.println();

        } while (input != 3);

        userInput.close();
    }
}