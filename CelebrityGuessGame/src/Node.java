
public class Node {
	Node parent;
	Node leftChild;
	Node rightChild;
	String question;
	String answer;

	Node(Node parent, Node leftChild, Node rightChild, String question, String answer) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.question = question;
		this.answer = answer;
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
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}