import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    
	private BufferedReader inFromClient;
    private BufferedWriter outToClient;
    private RandomAccessFile randomAccessFile;
    private Socket s;
    private Player player;
    private BinaryTree gameTree;
    
    public Game(Socket s, BinaryTree tree) throws IOException {
        this.s = s;
        inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        outToClient = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        gameTree = tree;
        randomAccessFile = new RandomAccessFile("CelebTreeFile.txt", "rwd");
    }
    
    public static String fixBackspace(String s){
    	StringBuilder sb = new StringBuilder();
    	int backspaceCount = 0;
    	boolean lastCharacterWasBackspace = false;
    	
    	for (int i=0; i<s.length(); i++){
    		sb.append(s.charAt(i));
    		if(s.charAt(i) == '\b'){
    			backspaceCount += 1;
    			lastCharacterWasBackspace = true;
    		}	
    		else{
    			if(lastCharacterWasBackspace){
    				sb.delete(sb.length() - 1 - (backspaceCount * 2), sb.length() - 1);
    				backspaceCount = 0;
    				lastCharacterWasBackspace = false;
    			}	
    		}
    	}
    	return sb.toString();	
    }
    
    public void run() {
    	
    	Node celeb = null;
		String newCeleb = "";
		String newQuestion = "";
		String playerName = "";
		
		Driver d = new Driver();		
		
        try {
        	
        	if (randomAccessFile.length() <= 0) {
        		gameTree.root = new Node(0,null,null,null,"Barack Obama");
        		gameTree.tree.add(gameTree.root);
        		randomAccessFile.write(gameTree.root.toString().getBytes());
        		randomAccessFile.seek(gameTree.root.getId()*92);
			} else {
				readTree(randomAccessFile);
				System.out.println(gameTree.tree);
			}
        	
        	outToClient.write("What is your name?\n");
        	outToClient.flush();
        	playerName = inFromClient.readLine();
        	playerName = fixBackspace(playerName);
        	player = new Player(playerName);
        	
        	while(true){
    			
    			Integer root = gameTree.root.getId();
    			String clientMessage = ""; 
    			boolean answered = false;
    			
//    			outToClient.write("Hello " + playerName + ", Would you like to play a celebrity guessing game?\n");
//    			outToClient.flush();
    			
    	        while(!answered) {   
    	        	outToClient.write("Hello " + playerName + ", Would you like to play a celebrity guessing game?\n");
    	        	outToClient.flush();
    	        	clientMessage = inFromClient.readLine();
    	        	clientMessage = fixBackspace(clientMessage);
    	        	if(clientMessage.equalsIgnoreCase("Y")){
    	        		answered = true;
    	        	} else if (clientMessage.equalsIgnoreCase("N")){
    	        		answered = true;
    	        	}   
    	        }
    			
//    			clientMessage = inFromClient.readLine();
//                clientMessage = fixBackspace(clientMessage);
                
    			if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    				while(root != null){
    					if (gameTree.tree.get(root).getLeftChild() == null && gameTree.tree.get(root).getRightChild() == null){
    						celeb = gameTree.tree.get(root);
    						outToClient.write("Is the celebrity you are thinking of " + celeb.getValue() + "?\n");
    						outToClient.flush();
    						clientMessage = inFromClient.readLine();
    		                clientMessage = fixBackspace(clientMessage);
    		                
    						if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    							root = gameTree.tree.get(root).getLeftChild();
    						} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    							root = gameTree.tree.get(root).getRightChild();
    						}
    					} else {
    						outToClient.write(gameTree.tree.get(root).getValue() + "\n");
    						outToClient.flush();
    						clientMessage = inFromClient.readLine();
    		                clientMessage = fixBackspace(clientMessage);
    		                
    						if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")) {
    							root = gameTree.tree.get(root).getLeftChild();
    						} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    							root = gameTree.tree.get(root).getRightChild();
    						}
    						
    					}
    					
    				}
    				
    				if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")){
    					outToClient.write("I'm so smart!\n");
    					outToClient.flush();
    					
    					d.notifyUser(celeb.getValue(), player.getName());
    				} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")){
    					outToClient.write("Who are you thinking of?\n");
    					outToClient.flush();
    					newCeleb = inFromClient.readLine();
		                newCeleb = fixBackspace(newCeleb);
    					outToClient.write("Ask a yes/no question that would distinguish between " + celeb.getValue() + " and " + newCeleb + "\n");
    					outToClient.flush();
    					newQuestion = inFromClient.readLine();
		                newQuestion = fixBackspace(newQuestion);
    					outToClient.write("Would a answer of yes indicate " + newCeleb + "?\n");
    					outToClient.flush();
    					clientMessage = inFromClient.readLine();
    	                clientMessage = fixBackspace(clientMessage);
    	                Node newCelebNode = null;
    	                
    					if (clientMessage.equalsIgnoreCase("Y") || clientMessage.equalsIgnoreCase("YES")){
    						newCelebNode = gameTree.addNode(celeb.getParent(), newQuestion);
    						
    						gameTree.insertNode(celeb, newCelebNode, false);
    						
    						newCelebNode.setLeftChild(gameTree.addNode(newCelebNode.getId(), newCeleb).getId());
    						randomAccessFile.seek(newCelebNode.getLeftChild()*92);
    						randomAccessFile.write(gameTree.tree.get(newCelebNode.getLeftChild()).toString().getBytes());
    						
    						ArrayList<String> playerAdded = player.getCelebritiesAdded();
    						playerAdded.add(newCeleb);
    						player.setCelebritiesAdded(playerAdded);
    						
    						outToClient.write("Thank you for adding " + newCeleb + " to the database.\n");
    						outToClient.flush();
    						
    					} else if (clientMessage.equalsIgnoreCase("N") || clientMessage.equalsIgnoreCase("NO")) {
    						newCelebNode = gameTree.addNode(celeb.getParent(), newQuestion);
    						
    						gameTree.insertNode(celeb, newCelebNode, true); //might have been missing last night with other code
    						newCelebNode.setRightChild(gameTree.addNode(newCelebNode.getId(), newCeleb).getId());
    						randomAccessFile.seek(newCelebNode.getRightChild()*92);
    						randomAccessFile.write(gameTree.tree.get(newCelebNode.getRightChild()).toString().getBytes());
    						
    						ArrayList<String> playerAdded = player.getCelebritiesAdded();
    						playerAdded.add(newCeleb);
    						player.setCelebritiesAdded(playerAdded);
//    						System.out.println(playerAdded);
    						
    						outToClient.write("Thank you for adding " + newCeleb + " to the database.\n");
    						outToClient.flush();
    					}
    					
    					randomAccessFile.seek(celeb.getId()*92);
    					randomAccessFile.write(celeb.toString().getBytes());
    					
    					randomAccessFile.seek(newCelebNode.getId()*92);
    					randomAccessFile.write(newCelebNode.toString().getBytes());
    					
    					randomAccessFile.seek(newCelebNode.getParent()*92);
    					randomAccessFile.write(gameTree.tree.get(newCelebNode.getParent()).toString().getBytes());
    					
    					System.out.println(gameTree.tree);
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
    
    private void readTree(RandomAccessFile file){
    	
    	try {
			String fileLine = file.readLine();
			List<String> nodeList = new ArrayList<String>();
			
			for (int i = 0; i < fileLine.length(); i+=92) {
				nodeList.add(fileLine.substring(i, i+92));
			}
			
			for (String node : nodeList) {
				Integer id = idInteger(node.substring(0, 2).trim());
				Integer parentID = idInteger(node.substring(3, 5).trim());
				Integer leftID = idInteger(node.substring(6, 8).trim());
				Integer rightID = idInteger(node.substring(9, 11).trim());
				String value = node.substring(12, 91).trim();
				
				gameTree.addNode(id, parentID, leftID, rightID, value);
			}
			gameTree.root = gameTree.tree.get(1);
			gameTree.nodeCount = nodeList.size()-1;
			
		} catch (IOException e) {
			System.out.println(e);
		}
    }
    
	private Integer idInteger(String idString){
		Integer id;
		if (idString.isEmpty() || idString.equals("") || idString.equals(" ")) {
			id = null;
		} else {
			 id = Integer.valueOf(idString);
		}
		
		return id;
	}
	
    public void notifyGuessed(String celebrity, String playerName)
    {
    	ArrayList<String> celebritiesAdded = player.getCelebritiesAdded();
    	
    	if(celebritiesAdded.contains(celebrity))
    	{
    		try{	
    			outToClient.write(playerName +  " thought of " + celebrity + " too!\n");
            	outToClient.flush();
    		}
    		catch (IOException e) {
                System.out.println(e);
            } 
    		
    	}
    }
}