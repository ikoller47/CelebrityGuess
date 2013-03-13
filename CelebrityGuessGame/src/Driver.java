import java.net.*;
import java.util.*;

public class Driver {
	
	private static ArrayList<Game> allGames = new ArrayList<Game>();
	
	public void notifyUser(String celebrity, String playerName)
	{	
		for (Game g : allGames)
		{
			g.notifyGuessed(celebrity, playerName);
		}
	}
    
    public static void main(String argv[]) throws Exception
    {
//		BinaryTree tree = new BinaryTree("Barack Obama");
//		tree.root.setId(0);
    	
    	BinaryTree tree = new BinaryTree();
		
        ServerSocket welcomeSocket = new ServerSocket(6001);
        System.out.println("Server is up...");
        while(true) {
            Socket connectionSocket = null;
            connectionSocket = welcomeSocket.accept();
            Game game = new Game(connectionSocket, tree);
            allGames.add(game);
            Thread t = new Thread(game);
            t.start();
        }
    }
}
