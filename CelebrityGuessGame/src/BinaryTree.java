import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	
	Node root;
	
	List<Node> tree = new ArrayList<Node>();

	BinaryTree(String answer) {
		root = new Node(null,null,null,"",answer);
		//tree.add(new Node(null,null,null,"",answer));
	}
	
	BinaryTree(String question,String answer) {
		tree.add(new Node(null,null,null,question,answer));
	}
	
	public Node getRoot(){
		return root;
		//return tree.get(0);
	}
	
	public Node addNode(Node parent, String question, String answer) {
		Node newNode = new Node(parent,null,null,question,answer);
		tree.add(newNode);
		return newNode;
	}
	
}
