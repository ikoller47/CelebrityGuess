import java.util.Scanner;


public class Game {
	
	BinTree gameTree;

	public Game(BinTree tree) {
		gameTree = tree;
	}
	
	public void play(){
		
		BNode celeb = null;
		String newCeleb = "";
		String newQuestion = "";
		
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		
		while(true){
			
			BNode root = gameTree.theBTRootNode;
//			BNode celebQuestion = root.leftBNode;
//			BNode celebAnswer = root.rightBNode;
		
			System.out.println("Would you like to play a celebrity guessing game?");
			
			input = scanner.nextLine();
	
			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
				while(root != null){
					if (root.leftBNode == null && root.rightBNode == null){
						celeb = root;
						System.out.println("Is the celebrity you are thinking of " + celeb.value + "?");
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.leftBNode;
						} else {
							root = root.rightBNode;
						}
					} else {
						System.out.println(root.value);
						input = scanner.nextLine();
						if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
							root = root.leftBNode;
						} else {
							root = root.rightBNode;
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
					System.out.println("Ask a yes/no question that would distinguish between " + celeb.value + " and " + newCeleb);
					newQuestion = scanner.nextLine();
					System.out.println("Would a answer of yes indicate " + newCeleb + "?");
					input = scanner.nextLine();
					if(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")){
						BNode newCelebNode = new BNode(newQuestion);
						newCelebNode.leftBNode = new BNode(newCeleb);
						gameTree.insertAB(celeb, newCelebNode, true);
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					} else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
						BNode newCelebNode = new BNode(newQuestion);
						newCelebNode.rightBNode = new BNode(newCeleb);
						gameTree.insertAB(celeb, newCelebNode, false);
						System.out.println("Thank you for adding " + newCeleb + " to the database.");
					}
				}
			} else {
				System.exit(0);
			}
		}
		
	}

}
