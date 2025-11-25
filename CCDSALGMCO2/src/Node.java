import java.util.ArrayList;

/**
 * This class represents a node (account) in the graph
 */
public class Node {

    private int Identifier;
    private ArrayList<Integer> friends = new ArrayList<Integer>(); //this is essentially the friends list (adjacency list)

    public Node (int Identifier){
        this.Identifier = Identifier; //the number representation of the account
    }

    /**
     * Getter for the account ID number
     * @return ID number of the account
     */
    public int getIdentifier() {
        return Identifier;
    }

    /**
     * Adds a friend to the friends list of a node
     * @param friend the friend to be added
     */
    public void addFriend(int friend){
        friends.add(friend);
    }

    /**
     * Getter for the friends list
     * @return the friends list
     */
    public ArrayList<Integer> getFriends() {
        return friends;
    }
}
