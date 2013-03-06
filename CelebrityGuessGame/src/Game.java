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
		String playerName = "";
		
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		System.out.println("What is your name?");
		playerName = scanner.nextLine();

		while(true){
			
			Node root = gameTree.root;
			
			System.out.println("\nHello " + playerName + ", Would you like to play a celebrity guessing game?");
			
			input = scanner.nextLine();
	
			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
				while(root != null){
					if (root.getLeftChild() == null && root.getRightChild() == null){
						celeb = root;
						System.out.println("\nIs the celebrity you are thinking of " + celeb.getValue() + "?");
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
							root = root.getRightChild();
						}
					} else {
						System.out.println("\n" + root.getValue());
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
							root = root.getRightChild();
						}
						
					}
					
				}
				
				if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
					System.out.println("\nI'm so smart!");
				} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")){
					System.out.println("\nWho are you thinking of?");
					newCeleb = scanner.nextLine();
					System.out.println("\nAsk a yes/no question that would distinguish between " + celeb.getValue() + " and " + newCeleb);
					newQuestion = scanner.nextLine();
					System.out.println("\nIf someone answers yes to this question, would the answer be " + newCeleb + "?");
					input = scanner.nextLine();
					if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
						Node newCelebNode = new Node(newQuestion);
						
						gameTree.insertNode(celeb, newCelebNode, false);
						newCelebNode.setLeftChild(new Node(newCelebNode, null, null, newCeleb));
						
						System.out.println("\nThank you for adding " + newCeleb + " to the database.");
					} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
						Node newCelebNode = new Node(newQuestion);
						
						gameTree.insertNode(celeb, newCelebNode, true); //might have been missing last night with other code
						newCelebNode.setRightChild(new Node(newCelebNode, null, null, newCeleb));
						
						System.out.println("\nThank you for adding " + newCeleb + " to the database.");
					}
				}
			} else {
				System.out.println("\nGoodbye " + playerName + "!");
				System.exit(0);
			}
		}
		
	}

}
