import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.sql.SQLOutput;

public class helperFunctions {
    private Graph graph=null;


    public Graph loadFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int left, right, numOfAccounts, numOfConnections; //left and right values
            String[] parts;
            graph = new Graph();
            int index = 0;
            int leftIndex;

            //this reads the number of accounts and number of connections
            line = reader.readLine();
            parts = line.split("\\s+");
            numOfAccounts = Integer.parseInt(parts[0]);
            numOfConnections = Integer.parseInt(parts[1]);

            //OPTIONAL: idk the purpose of these yet
            System.out.println("numOfAccounts: " + numOfAccounts + "\n" + "numOfConnections: " + numOfConnections);

            while ((line = reader.readLine()) != null) {
                parts = line.split("\\s+");


                left = Integer.parseInt(parts[0]); // put the left part of the line to left int
                right = Integer.parseInt(parts[1]); // put the right part of the line to the right int
                leftIndex = graph.doesNodeExist(left);

                //if node doesn't exist yet, just add normally
                if (leftIndex == -1) {
                    graph.addNode(new Node(left));
                    leftIndex = graph.getNodes().size() - 1; //the index of the new node is at the end of the list - 1
                }

                //add the left number as their friend
                graph.getNodes().get(leftIndex).addConnection(right);
                index++;
            }

            return graph;


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
       return graph;
    }


}
