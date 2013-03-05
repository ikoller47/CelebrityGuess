import java.net.*;

public class Driver {
    
    public static void main(String argv[]) throws Exception
    {
		BinaryTree tree = new BinaryTree("Barack Obama");
		
        ServerSocket welcomeSocket = new ServerSocket(6001);
        System.out.println("Server is up...");
        while(true) {
            Socket connectionSocket = null;
            connectionSocket = welcomeSocket.accept();
            Game game = new Game(connectionSocket, tree);
            Thread t = new Thread(game);
            t.start();
        }
    }
}
