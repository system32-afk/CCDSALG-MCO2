import java.util.ArrayList;

public class Node {

    private int Identifier;
    private ArrayList<Integer> connections = new ArrayList<Integer>(); //this is essentially the friends list (adjacency list)

    public Node (int Identifier){
        this.Identifier = Identifier; //the number representation of the account
    }

    //returns the identifier
    public int getIdentifier() {
        return Identifier;
    }

    //adds to the friends lsit
    public void addConnection(int friend){
        connections.add(friend);
    }

    //returns the friends list
    public ArrayList<Integer> getConnections() {
        return connections;
    }
}
