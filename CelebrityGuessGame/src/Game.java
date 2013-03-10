import java.io.*;
import java.net.*;

public class Game implements Runnable {
    
	private BufferedReader inFromClient;
    private BufferedWriter outToClient;
    private RandomAccessFile randomAccessFile;
    private Socket s;
    BinaryTree gameTree;
    
    public Game(Socket s, BinaryTree tree) throws IOException {
        this.s = s;
        inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        outToClient = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        gameTree = tree;
        randomAccessFile = new RandomAccessFile("CelebTreeFile.txt", "rwd");
    }
    
    public void run() {
    	
    	Node celeb = null;
		String newCeleb = "";
		String newQuestion = "";
		String playerName = "";
		
        try {
        	
        	outToClient.write("What is your name?\n");
        	outToClient.flush();
        	playerName = inFromClient.readLine();
        	
        	while(true){
    			
    			Node root = gameTree.root;
    			
    			outToClient.write("Hello " + playerName + ", Would you like to play a celebrity guessing game?\n");
    			outToClient.flush();
    			String clientMessage = inFromClient.readLine();
                
    			if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    				while(root != null){
    					if (root.getLeftChild() == null && root.getRightChild() == null){
    						celeb = root;
    						outToClient.write("Is the celebrity you are thinking of " + celeb.getValue() + "?\n");
    						outToClient.flush();
    						clientMessage = inFromClient.readLine();
    						if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    							root = root.getLeftChild();
    						} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    							root = root.getRightChild();
    						}
    					} else {
    						outToClient.write(root.getValue() + "\n");
    						outToClient.flush();
    						clientMessage = inFromClient.readLine();
    						if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    							root = root.getLeftChild();
    						} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    							root = root.getRightChild();
    						}
    						
    					}
    					
    				}
    				
    				if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")){
    					outToClient.write("I'm so smart!\n");
    					outToClient.flush();
    				} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")){
    					outToClient.write("Who are you thinking of?\n");
    					outToClient.flush();
    					newCeleb = inFromClient.readLine();
    					outToClient.write("Ask a yes/no question that would distinguish between " + celeb.getValue() + " and " + newCeleb + "\n");
    					outToClient.flush();
    					newQuestion = inFromClient.readLine();
    					outToClient.write("Would a answer of yes indicate " + newCeleb + "?\n");
    					outToClient.flush();
    					clientMessage = inFromClient.readLine();
    					if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")){
    						Node newCelebNode = new Node(newQuestion);
    						
    						gameTree.insertNode(celeb, newCelebNode, false);
    						newCelebNode.setLeftChild(new Node(newCelebNode, null, null, newCeleb));
    						
//    						newCelebNode.setId(celeb.getId());
//    						newCelebNode.getLeftChild().setId(newCelebNode.getId() + 1);
//    						celeb.setId(newCelebNode.getId() + 2);
    						
    						String tree = gameTree.tranverseTree(gameTree.root);
//    						randomAccessFile.seek(newCelebNode.getId()*80);
//    						randomAccessFile.write(tree.getBytes(), newCelebNode.getId()*80, tree.length()-(newCelebNode.getId()*80));
    						randomAccessFile.seek(0);
    						randomAccessFile.write(tree.getBytes(), 0, tree.length());
    						gameTree.treeString = "";
    						
    						outToClient.write("Thank you for adding " + newCeleb + " to the database.\n");
    						outToClient.flush();
    					} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    						Node newCelebNode = new Node(newQuestion);
    						
    						gameTree.insertNode(celeb, newCelebNode, true); //might have been missing last night with other code
    						newCelebNode.setRightChild(new Node(newCelebNode, null, null, newCeleb));
    						
    						String tree = gameTree.tranverseTree(gameTree.root);
//    						randomAccessFile.seek(newCelebNode.getId()*80);
//    						randomAccessFile.write(tree.getBytes(), newCelebNode.getId()*80, tree.length()-(newCelebNode.getId()*80));
    						randomAccessFile.seek(0);
    						randomAccessFile.write(tree.getBytes(), 0, tree.length());
    						gameTree.treeString = "";
    						
    						outToClient.write("Thank you for adding " + newCeleb + " to the database.\n");
    						outToClient.flush();
    					}
    				}
    			} else {
    				s.close();
    			}
    		}
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}