import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        helperFunctions helper = new helperFunctions();
        Scanner userInput = new Scanner(System.in);
        Graph graph;
        int input;
        System.out.println("Enter file name (no need to include file extension): ");
        do{
            graph = helper.loadFile(userInput.next()+".txt");
            if(graph==null){
                System.out.println("Please select a valid file");
                System.out.println("Enter file name (no need to include file extension): ");
            }
        }while (graph == null);


            do {
                System.out.println("Main Menu");
                System.out.println("1] Get friend list");
                System.out.println("2] Get Connection");
                System.out.println("3] Exit");
                input = userInput.nextInt();

                switch (input){
                    case 1:
                        System.out.println("Please input an account number: ");
                        input = userInput.nextInt();
                        graph.getFriendsList(input);
                        break;
                    case 2:
                        //code here
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid input, please try again.");
                }
            }while (input != 3);



    }
}