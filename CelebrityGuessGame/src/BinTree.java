public class BinTree {
    BNode theBTRootNode;

    public BinTree() // constructor
    {
        theBTRootNode = null;
    }

    // ------------------ Addition of the node to the BST-------------------
    protected BNode insertAB(BNode theRootNode, BNode myNewNode, boolean left) {
    	if (theRootNode == theBTRootNode) {
    		theBTRootNode = myNewNode;
    		theBTRootNode.leftBNode = theRootNode;
		}
    	else if (theRootNode == null) {
            theRootNode = myNewNode;
            //checks if the username is smaller than 
            //the root object, if smaller appends to the left
        } else if (left){
        	myNewNode.rightBNode = theRootNode;
        	theBTRootNode.rightBNode  = myNewNode;
//            theRootNode.leftBNode = insertAB(theRootNode.leftBNode, myNewNode, false);
        } else {
        	myNewNode.leftBNode = theRootNode;
        	theBTRootNode.leftBNode  = myNewNode;
//        	theRootNode.rightBNode = insertAB(theRootNode.rightBNode, myNewNode, true);
        }
        return theRootNode;
    }

    // ------------------ InOrder traversal-------------------
    protected void inorder(BNode theRootNode) {
        if (theRootNode != null) {
            inorder(theRootNode.leftBNode);
            theRootNode.show();
            inorder(theRootNode.rightBNode);
        }
    }

    //calls the method to do in order
    public void inorderBST() {
        inorder(theBTRootNode);
    }

    // ----- Search for key name and  returns ref. 
    //              to BNode or null if not found--------
    protected BNode search(BNode theRootNode, String keyName) {
        //if the root is null returns null
        if (theRootNode == null) {
            return null;
        } else {
            //checks if they are equal
            if (keyName.compareTo(theRootNode.value) == 0) {
                return theRootNode;
            //checks id the key is smaller than the current
            //record  if smaller traverses to the left
            } else if (keyName.compareTo(theRootNode.value) < 0) {
                return search(theRootNode.leftBNode, keyName);
            } else {
                // if bigger traverses to the left
                return search(theRootNode.rightBNode, keyName);
            }
        }
    }
}