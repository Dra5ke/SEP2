package LFD.domain.mediator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that accepts clients and starts the thread handler
 * 
 *
 */
public class LFDServer implements Runnable {

	private ServerSocket welcomeSocket;
	private LFDModel model;

	public LFDServer(int port, LFDModel model) throws IOException {
		super();
		this.model = model;
		this.welcomeSocket = new ServerSocket(port);
	}

	@Override
	public void run()
	{
		while (true)
	      {
	         model.notifyView("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            LFDThreadHandler c;
	            c = new LFDThreadHandler(socket, model);
	            Thread t = new Thread(c);
	            t.start();
	         }
	         catch (IOException e)
	         {
	            model.notifyView("Error in server. Message: " + e.getMessage());
	         }
	      }
	}

}
