package domain.mediator;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;
/**
 * Class used to receive the Package classes from the server and convert the from Json 
 * Adaptee to @see LFDUserClient
 *
 */
public class Receiver {

	private DataInputStream in;

	public Receiver(Socket socket) {

		// create input stream attached to the socket.
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Package receive() {
		
		try
		{
		// Read reply from Server.
		String reply = in.readUTF();

		// convert from JSon
		Gson gson = new Gson();
		Package replyPackage = gson.fromJson(reply, Package.class);
		return replyPackage;
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
}
