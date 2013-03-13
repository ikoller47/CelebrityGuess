
public class Node {
	
	private Integer parent;
	private Integer leftChild;
	private Integer rightChild;
	private String value;
	private Integer id;

	Node(Integer id, Integer parent, Integer leftChild, Integer rightChild, String value) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
		this.id = id;
	}
	
	Node(String value){
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
		this.id = null;
	}
	
	public Integer getParent() {
		return parent;
	}
	
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	public Integer getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Integer rightChild) {
		this.rightChild = rightChild;
	}
	
	public Integer getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(Integer leftChild) {
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
	
	public String toString(){
		String idString = idString(id);
		String parentIDString = idString(parent);
		String leftIDString = idString(leftChild);
		String rightIDString = idString(rightChild);
		String valueIDString = value;

		int temp = 80-valueIDString.length();
		
		for (int i = 0; i < temp; i++) {
			valueIDString += " ";
		}
		
		return idString +  parentIDString + leftIDString  + rightIDString + valueIDString;
	}
	
	private String idString(Integer node){
		String idString;
		if (node == null) {
			idString = " ";
		} else {
			idString = node.toString();
		}
		
		for (int i = 0; i <= 3-idString.length(); i++) {
			idString += " ";
		}
		
		return idString;
	}
	
}