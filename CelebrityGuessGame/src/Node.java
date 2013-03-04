
public class Node {
	
	private Node parent;
	private Node leftChild;
	private Node rightChild;
//	String question;
	private String value;

	Node(Node parent, Node leftChild, Node rightChild, String value) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
//		this.question = question;
		this.value = value;
	}
	
	Node(String value){
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
//	public String getQuestion() {
//		return question;
//	}
//	
//	public void setQuestion(String question) {
//		this.question = question;
//	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}