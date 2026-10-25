import java.util.Scanner;

public class LabActivity5 {
    public static void main(){
        BinarySearchTree tree = new BinarySearchTree();
        Scanner sc = new Scanner (System.in);
        while (true) {
            System.out.println("\u000C");
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
                deleteOption(sc, tree);
                break;
            case 'T':
            case 't':
                BinarySearchTree.traverse(sc, tree);
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
                        System.out.println(val + " is already in the tree.");
                    } else {
                        tree.insert(new Node(val));
                        System.out.println(val + " successfully inserted!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(v + " is not a whole number. Try again.");
                }
            }
        }
        System.out.println();
        pause(sc);
    }
    
    public static void deleteOption (Scanner sc, BinarySearchTree tree) {
        System.out.println("\u000C");
        header ("Delete", 50);
        
        if (tree.root == null) {
            System.out.println("\nThe tree is empty!");
            pause(sc);
            return;
        }
        
        boolean stop = false;
        
        while (!stop) {
            System.out.print("\nEnter Value to Delete or -99 to terminate the deletion operation: ");
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
                        tree.delete(val);
                        System.out.println(val + " successfully deleted!");
                    } else {
                        System.out.println(val + " is not in the tree.");
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
    
    public void delete(int data) {
        root = deleteHelper(root, data);
    }
    
    private Node deleteHelper (Node root, int data) {
        if (root == null) return null;
        
        if (data < root.data) {
            root.left = deleteHelper(root.left, data);
        } else if (data > root.data) {
            root.right = deleteHelper(root.right, data);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            
            Node successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.data = successor.data;
            root.right = deleteHelper (root.right, successor.data);
        }
        return root;
    }
    
    public static void traverse(Scanner sc, BinarySearchTree tree){
        boolean back = false;
        while (!back) {
            System.out.println("\u000C");
            LabActivity5.line(50);
            LabActivity5.center("Tree Traversal", 50);
            System.out.println();
            LabActivity5.center("Menu", 50);
            System.out.println();
            LabActivity5.line(50);
            System.out.print("\n[ 1 ] InOrder");
            System.out.print("\n\n[ 2 ] PreOrder");
            System.out.print("\n\n[ 3 ] PostOrder");
            System.out.print("\n\n[ 4 ] Exit");
            System.out.print("\n\nEnter Your Choice: ");
            back = traverseOptions(sc, tree);
        }
    }

    public static boolean traverseOptions(Scanner sc, BinarySearchTree tree){
        String traverseInput = sc.nextLine().trim();
        switch (traverseInput){
            case "1":
                traverseOperations(sc, tree, "InOrder", 1);
                return false;
            case "2":
                traverseOperations(sc, tree, "PreOrder", 2);
                return false;
            case "3":
                traverseOperations(sc, tree, "PostOrder", 3);
                return false;
            case "4":
                return true;
            default:
                System.out.println("\nInvalid input! Only integers 1-4 are accepted.\n");
                LabActivity5.pause(sc);
                return false;
        }
    }
    
     private static void traverseOperations(Scanner sc, BinarySearchTree tree, String head, int chosenTraversal) {
        System.out.println("\u000C");
        LabActivity5.header(head, 50);
        
        if(tree.root == null){
            System.out.println("The tree is empty!");
            LabActivity5.pause(sc);
        } else if (chosenTraversal == 1) {
            inOrderHelper(tree.root);
            System.out.println();
            LabActivity5.pause(sc);
        } else if (chosenTraversal == 2) {
            preOrderHelper(tree.root);
            System.out.println();
            LabActivity5.pause(sc);
        } else if (chosenTraversal == 3) {
            postOrderHelper(tree.root);
            System.out.println();
            LabActivity5.pause(sc);
        } 
    }
    
    private static void inOrderHelper(Node root){
        if(root != null){
            inOrderHelper(root.left);
            System.out.printf("%6s", root.data + "  ");
            inOrderHelper(root.right);
        }
    }

    private static void preOrderHelper(Node root){
        if(root != null){
            System.out.printf("%6s", root.data + "  ");
            preOrderHelper(root.left);
            preOrderHelper(root.right);
        }
    }
    
    private static void postOrderHelper(Node root) {
        if(root != null) {
            postOrderHelper(root.left);
            postOrderHelper(root.right);
            System.out.printf("%6s", root.data + "  ");
        }
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