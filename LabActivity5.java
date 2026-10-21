import java.util.Scanner;

public class LabActivity5 {
    public static void main(){
        BinarySearchTree tree = new BinarySearchTree();
        Scanner sc = new Scanner (System.in);
        while (true) {
            line(50);
            center("BST Tree Operations", 50);
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
            options(sc, tree);
        }
    }

    public static void options (Scanner sc, BinarySearchTree tree){
        String input = sc.nextLine().trim();
        if (input.isEmpty() || !Character.isLetter(input.charAt(0))) {
            System.out.println("\nInvalid input! Only letters 'S', 'I', 'D', 'T', and 'Q' are accepted.\n");
            pause(sc);
            return;
        }
        char choice = input.charAt(0);
        switch (choice){
            case 'S':
            case 's':
                System.out.println("\u000C");
                header("Show", 50);
                tree.show();
                pause(sc);
                break;
            case 'I':
            case 'i':
                insertOption(sc, tree);
                break;
            case 'D':
            case 'd':
                break;
            case 'T':
            case 't':
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
    
    public static void insertOption(Scanner sc, BinarySearchTree tree) {
        System.out.println("\u000C");
        header("Insert", 50);
        boolean stop = false;

        while (!stop) {
            System.out.print("\nEnter Value to Insert or -99 to terminate the insert operation: ");
            String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("Please enter at least one value.");
                    continue;
                }
            String[] values = line.split("\\s+");
            
            for (String v : values) {
                try {
                    int val = Integer.parseInt(v);
                    if (val == -99) {
                        stop = true;
                        break;
                    }
                    if (tree.contains(val)) {
                        System.out.println(val + " is already in the tree. Try again.");
                    } else {
                        tree.insert(new Node(val));
                        System.out.println(val + " successfully inserted.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(v + " is not a whole number. Try again.");
                }
            }
        }
        System.out.println();
        pause(sc);
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
        System.out.print("\nPress any key to continue...");
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
    private int counter;

    public void show(){
        if (root == null){
            System.out.println("The tree is empty!\n");
            return;
        }

        int h = height(root);
        int unit = 4;

        for (int level = 1; level <= h; level++) {
            int spacing = (int) Math.pow(2, h - level) * unit;

            System.out.print(" ".repeat((spacing - unit) / 2));
            printLevel(root, level, spacing - unit);
            System.out.println();

            if (level < h) {
                printBranches(root, level, spacing, unit);
                System.out.println();
            }
        }
    }
    
    public void insert(Node n) {
        root = insertHelper(root, n);
    }
    
    private Node insertHelper(Node root, Node n) {
        int data = n.data;
        
        if(root == null) {
            root = n;
            return root;
        } else if (data < root.data) {
            root.left = insertHelper(root.left, n); 
        } else {
            root.right = insertHelper(root.right, n);
        }
        return root;
    }
    
    public boolean contains(int data) {
        Node current = root;
            while (current != null) {
                if (data == current.data) 
                return true;
                
                current = (data < current.data) ? current.left : current.right;
            }
        return false;
    }   
    
        private void printLevel(Node n, int level, int gap) {
        if (level == 1) {
            if (n == null) {
                System.out.print("    " + " ".repeat(gap)); 
            } else {
                System.out.print(String.format("%-4d", n.data) + " ".repeat(gap));
            }
            return;
        }

        Node left = null, right = null;
        if (n != null) {
            left = n.left;
            right = n.right;
        }
        printLevel(left, level - 1, gap);
        printLevel(right, level - 1, gap);
    }

    private void printBranches(Node n, int level, int spacing, int unit) {
        if (level == 1) {
            char[] slot = new char[spacing];
            java.util.Arrays.fill(slot, ' ');
            if (n != null) {
                int p = (spacing - unit) / 2;      
                if (n.left != null)  slot[p - spacing / 8] = '/';
                if (n.right != null) slot[p + spacing / 8 + 1] = '\\';
            }
            System.out.print(new String(slot));
            return;
        }
        Node left = null, right = null;
        if (n != null) {
            left = n.left;
            right = n.right;
        }
        printBranches(left, level - 1, spacing, unit);
        printBranches(right, level - 1, spacing, unit);
    }

    private int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }
}