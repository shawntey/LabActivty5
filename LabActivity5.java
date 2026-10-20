import java.util.Scanner;

public class LabActivity5 {
    public static void main(){
        BinarySearchTree tree = new BinarySearchTree();

        Scanner sc = new Scanner (System.in);
        while (true) {
            line(50);
            center("Stack Application Conversion", 50);
            System.out.println();
            center("Menu", 50);
            System.out.println();
            line(50);
            System.out.print("\n[ S ] Show");
            System.out.print("\n\n[ I ] Insert");
            System.out.print("\n\n[ D ] Delete");
            System.out.print("\n\n[ T ] Traverse");
            System.out.print("\n\n[ Q ] Quit");
            System.out.print("\n\nEnter First Letter of Your Choice: ");
            options(sc);
        }
    }

    public static void options (Scanner sc){
        if (!Character.isLetter(sc.next().charAt(0))) {
            sc.nextLine();
            System.out.println("\nInvalid input! Only letters 'S', 'I', 'D', 'T', and 'Q' are accepted.\n");
            pause(sc);
            return;
        }
        char choice = sc.next().charAt(0);
        sc.nextLine();
        switch (choice){
            case 'S':
            case 's':
                show(sc);
                break;
            case 'I':
            case 'i':
                break;
            case 'D':
            case 'd':
                break;
            case 'T':
            case 't':
                traverse(sc);
                break;
            case 'Q':
            case 'q':
                System.out.println("\u000C");
                header("PROGRAM TERMINATED", 50);
                System.exit(0);
            default:
                System.out.println("\nInvalid input! Only letters 'S', 'I', 'D', 'T', and 'Q' are accepted.\n");
                pause(sc);
        }
    }

    public static void show(){
        if(root == null){
            System.out.println("The tree is empty!");
        }
    }

    public static void header (String text, int width) {
        String line = "-".repeat(width);
        int totalpadding = Math.max(width - text.length(), 0);
        int leftpadding = totalpadding / 2;
        int rightpadding = totalpadding - leftpadding;
    
        System.out.println(line);
        System.out.println(" ".repeat(leftpadding) + text + " ".repeat(rightpadding));
        System.out.println(line);
    }

    public static void line (int width) {
        String line = "-".repeat(width);
        System.out.println(line);
    }

    public static void center (String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.printf("%" + padding + "s%s%" + padding + "s", "", text, "");
    }

    public static void pause(Scanner sc) {
        System.out.print("Press any key to continue...");
        sc.nextLine();
        System.out.println("\u000C");
    }
}

class Node{
    int data;
    Node left, right;

    public Node(int data){
        this.data = data;
    }
}

class BinarySearchTree{
    Node root;


}