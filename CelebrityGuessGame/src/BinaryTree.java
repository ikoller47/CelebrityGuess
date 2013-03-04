import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	
	Node root;
	
	List<Node> tree = new ArrayList<Node>();

	BinaryTree(String value) {
		root = new Node(null,null,null,value);
		//tree.add(new Node(null,null,null,"",answer));
	}
	
//	BinaryTree(String question,String answer) {
//		tree.add(new Node(null,null,null,question,answer));
//	}
	
	public Node getRoot(){
		return root;
		//return tree.get(0);
	}
	
	public Node addNode(Node parent, String value) {
		Node newNode = new Node(parent,null,null,value);
		tree.add(newNode);
		return newNode;
	}
	
	public Node addNode(Node node){
		tree.add(node);
		return node;
	}
	
	public void insertNode(Node rootNode, Node newNode, boolean left){
		if (rootNode == root) {
			root = newNode;
			root.setParent(newNode);
			rootNode.setParent(root);
		} 
		if (left){
			rootNode.getParent().setLeftChild(newNode);
			newNode.setParent(rootNode);
			rootNode.setParent(newNode);
			newNode.setLeftChild(rootNode);
		} else {
			rootNode.getParent().setRightChild(newNode);
			newNode.setParent(rootNode);
			rootNode.setParent(newNode);
			newNode.setRightChild(rootNode);
			
		}
	}
	
	public void printTree(Node node, String source) {
		Node activeNode = node;
		System.out.println(source + " " + activeNode.toString());
		if(activeNode.getLeftChild() != null)
			printTree(activeNode.getLeftChild(), "Left");
		if(activeNode.getRightChild() != null)
			printTree(activeNode.getRightChild(), "Right");
	}
}
