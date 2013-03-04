import java.util.Scanner;


public class Game {
	
	BinaryTree gameTree;
	
	public Game(BinaryTree tree) {
		gameTree = tree;
	}
	
	public void play(){
		
		Node celeb = null;
		String newCeleb = "";
		String newQuestion = "";
		
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		while(true){
			
			Node root = gameTree.root;
			
			System.out.println("Would you like to play a celebrity guessing game?");
			
			input = scanner.nextLine();
	
			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
				while(root != null){
					if (root.getLeftChild() == null && root.getRightChild() == null){
						celeb = root;
						System.out.println("Is the celebrity you are thinking of " + celeb.getValue() + "?");
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else {
							root = root.getRightChild();
						}
					} else {
						System.out.println(root.getValue());
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else {
							root = root.getRightChild();
						}
						
					}
					
				}
				
				if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
					System.out.println("I'm so smart!");
				} else {
					System.out.println("Who are you thinking of?");
					newCeleb = scanner.nextLine();
					System.out.println("Ask a yes/no question that would distinguish between " + celeb.getValue() + " and " + newCeleb);
					newQuestion = scanner.nextLine();
					System.out.println("Would a answer of yes indicate " + newCeleb + "?");
					input = scanner.nextLine();
					if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
						Node newCelebNode = new Node(newQuestion);
						
						gameTree.insertNode(celeb, newCelebNode, false);
						newCelebNode.setLeftChild(new Node(newCelebNode, null, null, newCeleb));
						
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
						Node newCelebNode = new Node(newQuestion);
						
						gameTree.insertNode(celeb, newCelebNode, true);
						newCelebNode.setRightChild(new Node(newCelebNode, null, null, newCeleb));
						
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					}
					System.out.println("\n");
					gameTree.printTree(gameTree.getRoot(), "Root");
					System.out.println("\n");
				}
			} else {
				scanner.close();
				System.exit(0);
			}
		}
		
	}

}
