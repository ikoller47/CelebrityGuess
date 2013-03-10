
public class Node {
	
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	private String value;
	private Integer id;

	Node(Node parent, Node leftChild, Node rightChild, String value) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
		this.id = null;
	}
	
	Node(String value){
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
		this.id = null;
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
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}