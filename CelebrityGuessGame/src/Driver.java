
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//BinTree binTree = new BinTree();
//		BNode root = new BNode("Would you like to play a celebrity guessing game?");
//		BNode right = new BNode("Barack Obama");
		//BNode root = new BNode("Barack Obama");
		BinaryTree tree = new BinaryTree("Barack Obama");
		//binTree.theBTRootNode = root;
//		root.rightBNode = right;
		
		//Game game = new Game(binTree);
		Game game = new Game(tree);
		game.play();

	}

}
