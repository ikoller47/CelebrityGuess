
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree("Barack Obama");

		Game game = new Game(tree);
		game.play();

	}

}
