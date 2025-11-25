import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphFileReader {
    private int numOfAccounts;
    private int numOfConnections;

    public Graph loadFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int left, right;
            String[] parts;
            Graph graph = new Graph();
            
            // Read number of accounts and connections
            line = reader.readLine();
            if (line == null) {
                System.out.println("Empty file");
                return null;
            }
            
            parts = line.split("\\s+");
            numOfAccounts = Integer.parseInt(parts[0]);
            numOfConnections = Integer.parseInt(parts[1]);

            // First, create all nodes
            for (int i = 0; i < numOfAccounts; i++) {
                graph.createNode(new Node(i)); 
            }

            // Then add connections (friendships are bidirectional)
            while ((line = reader.readLine()) != null) {
                parts = line.split("\\s+");
                if (parts.length < 2) continue;
                
                left = Integer.parseInt(parts[0]);
                right = Integer.parseInt(parts[1]);
                
                // Add connections (ASSUME THAT THE DATASET ALREADY HANDLES BIDIRECTIONALITY)
                graph.addConnection(left, right);
            }
            
            reader.close();
            System.out.println("Successfully loaded " + numOfAccounts + " accounts with " + numOfConnections + " connections");
            return graph;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file");
            return null;
        }
    }
}