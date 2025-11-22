import java.util.ArrayList;

public class Node {

    private int Identifier;
    private ArrayList<Integer> connections = new ArrayList<Integer>(); //this is essentially the friends list (adjacency list)

    public Node (int Identifier){
        this.Identifier = Identifier; //the number representation of the account
    }

    public int getIdentifier() {
        return Identifier;
    }

    //
    public void addConnection(int friend){
        connections.add(friend);
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }
}
