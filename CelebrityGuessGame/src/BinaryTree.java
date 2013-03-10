import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	
	Node root;
	String treeString = "";
	
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
			root.setParent(null);
			rootNode.setParent(root);
		} 
		if (left){
			if (rootNode.getParent().getRightChild() == rootNode && rootNode.getParent().getRightChild().getLeftChild() == null && rootNode.getParent().getRightChild().getRightChild() == null) {
				rootNode.getParent().setRightChild(newNode);
			} else {
				rootNode.getParent().setLeftChild(newNode);
			}
			
			newNode.setParent(rootNode.getParent());
			rootNode.setParent(newNode);
			newNode.setLeftChild(rootNode);
		} else {
			if(rootNode.getParent().getLeftChild() == rootNode && rootNode.getParent().getLeftChild().getLeftChild() == null && rootNode.getParent().getLeftChild().getRightChild() == null){
				rootNode.getParent().setLeftChild(newNode);
			} else {
				rootNode.getParent().setRightChild(newNode);
			}
			
			newNode.setParent(rootNode.getParent()); 
			rootNode.setParent(newNode);
			newNode.setRightChild(rootNode);
			
		}
	}
	
	public String tranverseTree(Node newNode){
		if (newNode == null) {
			return null;
		} else {
			String temp = "";
			if (newNode == root) {
				temp = new String(newNode.getValue());
			} else {
				temp = new String("\n" + newNode.getValue());
			}
				
			for (int i = 0; i < (80-temp.length())+temp.length(); i++) {
				temp += " ";
			}
			treeString += new String(temp.getBytes(), 0, 80);
			tranverseTree(newNode.getLeftChild());
			tranverseTree(newNode.getRightChild());
		}
		return treeString;
	}
	
}
