package domain.mediator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import domain.model.AnnouncementList;
import domain.model.UserInfo;

/**
 * Client class that connects to the server and also communicates with it by sending request Packages
 * @see Package
 *
 * Adapter to @see Receiver
 */
public class LFDUserClient implements LFDUserModel {
	private DataOutputStream out;
	private Socket socket;
	private Receiver receiver; 

	public LFDUserClient(String host, int port) throws UnknownHostException, IOException {
		// create client socket, connect to server.
		socket = new Socket(host, port);

		receiver = new Receiver(this.socket);

		// create output stream attached to the socket.
		out = new DataOutputStream(socket.getOutputStream());
	}

	private Package remoteCall(Package request) {
		try {
			// convert to JSon
			Gson gson = new Gson();
			String json = gson.toJson(request);

			out.writeUTF(json);

			return receiver.receive();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String apply(int announcementNumber, UserInfo user) {
		Package request = new Package(Package.APPLY, user, announcementNumber);
		Package reply = remoteCall(request);
		return reply.getText();

	}

	@Override
	public AnnouncementList getAllAnnouncements() {
		Package request = new Package(Package.GET);
		Package reply = remoteCall(request);
		return reply.getAllAnnouncements();
	}

	@Override
	public boolean login(String username, String password) {
		Package request = new Package(Package.LOGIN, username, password);
		Package reply = remoteCall(request);
		return reply.getSuccess();
	}

	@Override
	public UserInfo getUserInfo(String username, String password) {
		Package request = new Package(Package.USER, username, password);
		Package reply = remoteCall(request);
		return reply.getUserInfo();
	}

	@Override
	public String register(UserInfo userInfo, String username, String password) {
		Package request = new Package(Package.REGISTERUSER, userInfo, username, password);
		Package reply = remoteCall(request);
		return reply.getText();
	}

}
