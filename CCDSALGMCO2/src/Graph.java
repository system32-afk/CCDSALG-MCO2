import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    /**
     * Creates graph
     */
    public Graph (){
        nodes = new ArrayList<>();
    }

    /**
     * Adds node to the list
     * @param node
     */
    public void addNode(Node node){
        nodes.add(node);
    }


    /**
    lieanrly searches the @nodes arraylist.
    This is faster than using linkedList then sorting than searching lol.
     @param nodeIdentifer  nodeIdentifier the account number to look for
     */
    public int doesNodeExist(int nodeIdentifer){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getIdentifier() == nodeIdentifer){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @return returns the list of nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }


    /**
    Prints all the nodes and their friends list
    3: [1,2,3,4]
    0: [6,7]
     */
    public void printNodes(){
        for (Node node : nodes) {
            System.out.println(node.getIdentifier()+":"+node.getConnections());
        }
    }


    /**
     * Prints out the connections/friends of a node.
     *
     * @param Identifier the account number to be looked up
     */
    public void getFriendsList(int Identifier){

        int index = doesNodeExist(Identifier);

        if(index == -1){
            System.out.println("Account does not exist.");
            return;
        }

        System.out.println(Identifier+" has "+nodes.get(index).getConnections().size()+" friends");
        System.out.println(Identifier+" is friends with: "+nodes.get(index).getConnections()); // this would print out the friends list


    }

}
