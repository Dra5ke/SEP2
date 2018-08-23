package LFDCompany.domain.mediator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import LFDCompany.domain.model.Announcement;
import LFDCompany.domain.model.AnnouncementList;
import LFDCompany.domain.model.CompanyInfo;
import LFDCompany.domain.model.UserInfo;

/**
 * Client class that connects to the server and also communicates with it by sending request Packages
 * @see Package
 *
 * Adapter to @see Receiver
 */
public class LFDCompanyClient implements LFDCompanyModel {

	private DataOutputStream out;
	private Socket socket;
	private Receiver receiver;

	public LFDCompanyClient(String host, int port) throws UnknownHostException, IOException {
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
	public String addAnnouncement(Announcement announcement) {
		Package request = new Package(Package.ADD, announcement);
		Package reply = remoteCall(request);
		return reply.getText();
		
	}

	@Override
	public String removeAnnouncement(int announcementNumber) {
		Package request = new Package(Package.REMOVE);
		Package reply = remoteCall(request);
		return reply.getText();
		
	}

	@Override
	public String register(CompanyInfo companyInfo, String username, String password) {
		Package request = new Package(Package.REGISTERCOMPANY, companyInfo, username, password);
		Package reply = remoteCall(request);
		return reply.getText();
	}

	@Override
	public boolean login(String username, String password) {
		Package request = new Package(Package.LOGINC, username, password);
		Package reply = remoteCall(request);
		return reply.getSuccess();
	}

	@Override
	public CompanyInfo getCompanyInfo(String username, String password) {
		Package request = new Package(Package.COMPANY, username, password);
		Package reply = remoteCall(request);
		return reply.getCompanyInfo();
	}

	@Override
	public AnnouncementList getAnnouncements(String name) {
		Package request = new Package(Package.GET);
		Package reply = remoteCall(request);
		AnnouncementList list = new AnnouncementList();
		for(int i = 0; i < reply.getAllAnnouncements().size(); i++)
		{
			if(name.equals(reply.getAllAnnouncements().getAnnouncement(i).getAnnouncer().getName()))
			{
				list.addAnnouncements(reply.getAllAnnouncements().getAnnouncement(i));
			}
		}
		return list;
	}
}
