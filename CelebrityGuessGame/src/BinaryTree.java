import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	
	Node root;
	int nodeCount;
	String treeString = "";
	
	List<Node> tree = new ArrayList<Node>();
	
	BinaryTree() {
		nodeCount = 0;
	}

	BinaryTree(String value) {
		nodeCount = 0;
		root = new Node(nodeCount,null,null,null,value);
		tree.add(root);
		//tree.add(new Node(null,null,null,"",answer));
	}
	
	
//	BinaryTree(String question,String answer) {
//		tree.add(new Node(null,null,null,question,answer));
//	}
	
	public Node getRoot(){
		return root;
		//return tree.get(0);
	}
	
	public Node addNode(Integer parent, String value) {
		nodeCount++;
		Node newNode = new Node(nodeCount, parent,null,null,value);
		tree.add(newNode);
		return newNode;
	}
	
	public Node addNode(Integer id, Integer parent, Integer leftChild, Integer rightChild, String value) {
		nodeCount++;
		Node newNode = new Node(id, parent, leftChild, rightChild, value);
		tree.add(newNode);
		return newNode;
	}
	
	public void insertNode(Node oldNode, Node newNode, boolean left){
		
		if (oldNode == root) {
			root = newNode;
			root.setParent(null);
			oldNode.setParent(root.getId());
		} 
		if (left){
			if (tree.get(oldNode.getParent()).getRightChild() == oldNode.getId() && tree.get(tree.get(oldNode.getParent()).getRightChild()).getLeftChild() == null && tree.get(tree.get(oldNode.getParent()).getRightChild()).getRightChild() == null) {
				tree.get(oldNode.getParent()).setRightChild(newNode.getId());
			} else {
				tree.get(oldNode.getParent()).setLeftChild(newNode.getId());
			}
			
			newNode.setParent(oldNode.getParent());
			oldNode.setParent(newNode.getId());
			newNode.setLeftChild(oldNode.getId());
		} else {
			if(tree.get(oldNode.getParent()).getLeftChild() == oldNode.getId() && tree.get(tree.get(oldNode.getParent()).getLeftChild()).getLeftChild() == null && tree.get(tree.get(oldNode.getParent()).getLeftChild()).getRightChild() == null){
				tree.get(oldNode.getParent()).setLeftChild(newNode.getId());
			} else {
				tree.get(oldNode.getParent()).setRightChild(newNode.getId());
			}
			
			newNode.setParent(oldNode.getParent()); 
			oldNode.setParent(newNode.getId());
			newNode.setRightChild(oldNode.getId());
			
		}
	}
	
	public String traverseTree(Node newNode){
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
//			treeString += new String(temp.getBytes(), 0, 80);
			treeString += temp;
			traverseTree(tree.get(newNode.getLeftChild()));
			traverseTree(tree.get(newNode.getRightChild()));
		}
		return treeString;
	}
	
}
