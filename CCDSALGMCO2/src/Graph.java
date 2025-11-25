import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class contains the graph data structure and its associated methods.
 */
public class Graph {
    private ArrayList<Node> nodes;  // List of all nodes in the graph
    private ArrayList<Integer> path; // Store the path for retrieval

    /**
     * Constructor to initialize the graph
     */
    public Graph (){
        nodes = new ArrayList<>();
        path = new ArrayList<>();
    }

    /**
     * Adds an instantiated Node to the list
     * @param node the node to be added
     */
    public void createNode(Node node){
        nodes.add(node);
    }


    /**
     * Adds a connection (friendship) between two nodes; hence the edges
     * @param from the source node ID
     * @param to the target node ID to connect with the source node
     */
    public void addConnection(int from, int to) {
        int fromIndex = doesNodeExist(from);
        if (fromIndex != -1) {
            nodes.get(fromIndex).addFriend(to);
        }
    }

    /**
     * Linearly searches the @nodes arraylist.
     * @param nodeIdentifer  nodeIdentifier the account number to look for
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
            System.out.println(node.getIdentifier()+":"+node.getFriends());
        }
    }

    /**
     * Prints out the connections/friends of a node.
     * @param Identifier the account number to be looked up
     */
    public void getFriendsList(int Identifier){

        int index = doesNodeExist(Identifier);

        if(index == -1){
            System.out.println("Account does not exist.");
            return;
        }

        System.out.println("Person " + Identifier +" has "+ nodes.get(index).getFriends().size()+" friends");
        System.out.println(Identifier+" is friends with: "+ nodes.get(index).getFriends()); // this would print out the friends list
    }

    /**
     * BFS implementation to find connection between two nodes
     * Returns boolean: true if connection exists, false otherwise
     * Stores the path for retrieval via getPath() method
     * 
     * @param ID1 first account ID (origin node)
     * @param ID2 second account ID (target node)
     * @return boolean true if connection exists, false otherwise
     */
    public boolean getConnection(int ID1, int ID2) {
        // clear previous path
        path.clear();
        
        // check if both nodes exist
        if (doesNodeExist(ID1) == -1 || doesNodeExist(ID2) == -1) {
            System.out.println("One or both account IDs do not exist.");
            return false;
        }

        // handle case where both IDs are the same
        if (ID1 == ID2) {
            path.add(ID1);
            System.out.println("Both account IDs are the same.");
            return false;
        }


        // consists of Boolean values to track which nodes have been visited during BFS traversal. 
        // each index in this list corresponds to a node ID, thus, prevents revisiting nodes and infinite loops in cyclic graphs
        ArrayList<Boolean> visited = new ArrayList<>(); 

        // integers to track the parent-child relationships for path reconstruction; each index stores the parent node ID that led to discovering this node,
        // parent.get(childNode) returns the parent node that discovered this child
        // e.g. if parent.get(8) = 3, it means node 3 discovered node 8 during BFS
        ArrayList<Integer> parent = new ArrayList<>(); // Track parent nodes for path reconstruction


        // a queue using LinkedList for managing BFS traversal order, in line with its FIFO principle for level-by-level traversal
        // enqueue nodes to be explored and dequeue the next node to explore, and provides O(1) enqueue/dequeue operation
        Queue<Integer> queue = new LinkedList<>();

        // let the first node be the starting point
        int start = ID1;

        // Initialize visited and parent ArrayLists
        for (int i = 0; i < nodes.size(); i++) {
            visited.add(false);
            parent.add(-1);
        }

        // start BFS from the starting node
        visited.set(start, true);
        queue.add(start);

        // while there are nodes to explore, do BFS
        while (!queue.isEmpty()) {
            // dequeue current node and get the front node from the queue for exploration
            int current = queue.poll();
            // locate the position of current node in our nodes list to access the node's adjacency list (friend connections)
            int currentIndex = doesNodeExist(current);
            
            if (currentIndex != -1) {
                // retrieve all direct friends/connections of the current node,
                // access the adjacency list stored at this node's index (all the people who are friends with the "current" person)
                ArrayList<Integer> neighbors = nodes.get(currentIndex).getFriends();
                
                // explore each neighbor,check each friend to see if they lead us closer to the target person
                for (int i = 0; i < neighbors.size(); i++) {
                    int neighbor = neighbors.get(i);
                    
                    // if neighbor hasn't been visited yet
                    if (!visited.get(neighbor)) {
                        // set parent to gradually create the tree; records that the current node discovered this neighbor
                        parent.set(neighbor, current);
                        visited.set(neighbor, true); // sets the neighbor as visited for avoiding revisits and cyclic graphing
                        queue.add(neighbor); // enqueue neighbor for further exploration
                        
                        // test if this neighbor is the person we're looking for; if yes, then a connection path from start to target is found
                        if (neighbor == ID2) {
                            // starting node, target node, and parent relationships to construct the path via backtracking
                            constructPath(ID1, ID2, parent);
                            return true;
                        }
                    }
                }
            }
        }

        // no connection found at all
        return false;
    }

    /**
     * Graph ultility method to reconstruct the path from origin to target, and stores the path in the array for easy retrieval
     * @param start origin node ID
     * @param end target node ID
     * @param parent parent-child relationships recorded during BFS
     */
    private void constructPath(int start, int end, ArrayList<Integer> parent) {
        ArrayList<Integer> tempPath = new ArrayList<>();
        int currentNode = end;
        
        // trace path backwards from target (latest) to origin (earliest), since the traversal recorded parent-child relationships from origin to target
        while (currentNode != -1) {
            tempPath.add(currentNode);
            currentNode = parent.get(currentNode);
        }
        
        // store the path in reverse order (start to end)
        for (int i = tempPath.size() - 1; i >= 0; i--) {
            path.add(tempPath.get(i));
        }
    }

    /**
     * Getter method to retrieve the path found by BFS
     * @return the path from start to end
     */
    public ArrayList<Integer> getPath() {
        return path;
    }
}

