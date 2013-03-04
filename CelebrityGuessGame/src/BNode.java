public class BNode {

    public BNode leftBNode,  rightBNode; // the nodes
    public String value; //the AnyClass objext

    public BNode(String value) {//constructor
        this.value= value;
        this.leftBNode = null;
        this.rightBNode = null;
    }

    public void show() {
        //calls the show method of the AnyClass
        System.out.print(value);
    }
}