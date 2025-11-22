import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph (){
        nodes = new ArrayList<>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public int doesNodeExist(int nodeIdentifer){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getIdentifier() == nodeIdentifer){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }


    //for debugging lang
    public void printNodes(){
        for (Node node : nodes) {
            System.out.println(node.getIdentifier()+":"+node.getConnections());
        }
    }

    public void getFriendsList(int Identifier){

        int index = doesNodeExist(Identifier);

        if(index == -1){
            System.out.println("Account does not exist.");
            return;
        }

        System.out.println(Identifier+" has "+nodes.get(index).getConnections().size()+" friends");
        System.out.println(Identifier+" is friends with: "+nodes.get(index).getConnections()); // this would print out the friends list


    }

    /*
    This is to sort the array of nodes because the nodes are not in order sa file
    so the idea is that we add them to the list, to be sorted kasi we would want to avoid duplicates
    kasi sa file there are example: 35 0 and 0 35 and sa @addEdge we can just add them agad both.
    and if we want to avoid duplicates, we would want to check if the node and connection already exist.
     */
    public void sortNodes() {
        int length = nodes.size();
        int gap = length;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = (gap * 10) / 13;  // shrinking factor of 1.3 to the gap per iteration

            if (gap < 1)
                gap = 1;

            swapped = false;

            // Compare each element with the one "gap" positions ahead.
            for (int i = 0; i < length - gap; i++) {
                Node temp1 = nodes.get(i);
                Node temp2 = nodes.get(i + gap);

                // If they are out of order, swap them and mark swapped as true
                if (temp1.getIdentifier() > temp2.getIdentifier()) {
                    nodes.set(i, temp2);
                    nodes.set(i + gap, temp1);
                    swapped = true;
                }
            }
        }
    }
}
