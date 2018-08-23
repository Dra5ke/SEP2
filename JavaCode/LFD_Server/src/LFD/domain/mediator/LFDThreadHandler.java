package LFD.domain.mediator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import LFD.domain.model.AnnouncementList;

/**
 * Class that handles the communication between the client and the server
 * 
 *
 */
public class LFDThreadHandler implements Runnable {

	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private LFDModel model;
	private String ip;

	public LFDThreadHandler(Socket clientSocket, LFDModel model) throws IOException {
		super();
		this.clientSocket = clientSocket;
		this.model = model;

		// Read from stream : String tmp = inputStream.readUTF();
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into stream : outputStream.writeUTF(new String("text to send"));
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.ip = clientSocket.getInetAddress().getHostAddress();
		model.notifyView(ip + " connected");
	}

	/**
	 * This method waits for a request Package from the client then sends a reply Package back to him
	 * 
	 * @see operation
	 * @see Package
	 */
	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {

				String line = inputStream.readUTF();
				model.notifyView(ip + "> " + line);

				// convert from JSon
				Gson gson = new Gson();
				Package request = gson.fromJson(line, Package.class);
				model.notifyView("package: " + request.getText());

				Package reply = operation(request);

				// convert to JSon
				// gson = new Gson();
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				model.notifyView("Server to " + ip + "> " + reply);
				if (reply.getText().equalsIgnoreCase("EXIT")) {
					continueCommuticating = false;
				}
			}
			model.notifyView("Closing connection to client: " + ip);

		} catch (Exception e) {
			String message = e.getMessage();
			if (message == null) {
				message = "Connection lost";
			}
			model.notifyView("Error for client: " + ip + " - Message: " + message);
		}
	}

	/**
	 * Method that takes the request Package then uses the model to create a reply Package depending on the request 
	 * @param request The Package received from the client
	 * @return a Package containing what the client requested
	 * @see Package
	 */
	private Package operation(Package request) {
		switch (request.getText()) {
		case Package.GET:
			AnnouncementList list = model.getAllAnnouncements();
			if (list.size() == 0)
				return new Package("NO ANNOUNCEMENTS - EMPTY ANNOUNCEMENT LISTS", list);
			return new Package(Package.GET, list);

		case Package.APPLY:
			model.apply(request.getAnnouncementNumber(), request.getUserInfo());
			return new Package("APPLIED TO ANNOUNCEMENT");

		case Package.ADD:
			model.addAnnouncement(request.getAnnouncement());
			return new Package("ANNOUNCEMENT ADDED");

		case Package.REMOVE:
			model.removeAnnouncement(request.getAnnouncementNumber());
			return new Package("ANNOUNCEMENT REMOVED");

		case Package.LOGIN:
			return new Package("Login verification", model.loginUser(request.getUsername(), request.getPassword()));

		case Package.USER:
			return new Package("User information", model.getUserInfo(request.getUsername(), request.getPassword()));

		case Package.REGISTERUSER:
			model.registerUser(request.getUserInfo(), request.getUsername(), request.getPassword());
			return new Package("USER REGISTERED");

		case Package.LOGINC:
			return new Package("Login verification", model.loginCompany(request.getUsername(), request.getPassword()));

		case Package.COMPANY:
			return new Package("Company information",
					model.getCompanyInfo(request.getUsername(), request.getPassword()));

		case Package.REGISTERCOMPANY:
			model.registerCompany(request.getCompanyInfo(), request.getUsername(), request.getPassword());
			return new Package("COMPANY ACCOUNT REGISTERED");

		case Package.EXIT:
			return new Package("EXIT");

		default:
			return new Package("WRONG FORMAT");

		}

	}

	public void close() {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
