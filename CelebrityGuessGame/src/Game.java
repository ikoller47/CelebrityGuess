import java.util.Scanner;


public class Game {
	
	BinaryTree gameTree;
	//BinTree gameTree;

	/*public Game(BinTree tree) {
		gameTree = tree;
	}*/
	
	public Game(BinaryTree tree) {
		gameTree = tree;
	}
	
	public void play(){
		
		Node celeb = null;
//		BNode celeb = null;
		String newCeleb = "";
		String newQuestion = "";
		
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		while(true){
			
//			BNode root = gameTree.theBTRootNode;
//			BNode celebQuestion = root.leftBNode;
//			BNode celebAnswer = root.rightBNode;
			Node root = gameTree.getRoot();
			
			System.out.println("Would you like to play a celebrity guessing game?");
			
			input = scanner.nextLine();
	
			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
				while(root != null){
					if (root.getLeftChild() == null && root.getRightChild() == null){
						celeb = root;
						System.out.println("Is the celebrity you are thinking of " + celeb.getAnswer() + "?");
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else {
							root = root.getRightChild();
						}
					} else {
						System.out.println(root.getQuestion());
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.getLeftChild();
						} else {
							root = root.getRightChild();
//							celebQuestion = celebQuestion.leftBNode;
						}
						
					}
					
				}
				
//				input = scanner.nextLine();
				if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
					System.out.println("I'm so smart!");
				} else {
					System.out.println("Who are you thinking of?");
					newCeleb = scanner.nextLine();
					System.out.println("Ask a yes/no question that would distinguish between " + celeb.getAnswer() + " and " + newCeleb);
					newQuestion = scanner.nextLine();
					System.out.println("Would a answer of yes indicate " + newCeleb + "?");
					input = scanner.nextLine();
					if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
						//BNode newCelebNode = new BNode(newQuestion);
						//newCelebNode.leftBNode = new BNode(newCeleb);
						//gameTree.insertAB(celeb, newCelebNode, true);
						Node yesNode = gameTree.addNode(celeb, newQuestion, newCeleb);
						celeb.setLeftChild(yesNode);
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
						//BNode newCelebNode = new BNode(newQuestion);
						//newCelebNode.rightBNode = new BNode(newCeleb);
						//gameTree.addNode(celeb, newQuestion, newCeleb);
						Node noNode = gameTree.addNode(celeb, newQuestion, newCeleb);
						celeb.setRightChild(noNode);
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					}
				}
			} else {
				System.exit(0);
			}
		}
		
	}

}
