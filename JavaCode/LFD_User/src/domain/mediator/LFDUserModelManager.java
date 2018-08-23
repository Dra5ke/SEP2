package domain.mediator;

import java.io.IOException;

import domain.model.Init;
import domain.model.AnnouncementList;
import domain.model.UserInfo;
/**
 * Class that acts as Proxy to the RealSubject @see LFDUserClient
 *
 */
public class LFDUserModelManager implements LFDUserModel {
	private LFDUserClient client;

	public LFDUserModelManager() {

		try {
			this.client = new LFDUserClient(Init.getInstance().getIp(), Init.getInstance().getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String apply(int announcementNumber, UserInfo user) {
		// TODO Auto-generated method stub
		return client.apply(announcementNumber, user);
	}

	@Override
	public AnnouncementList getAllAnnouncements() {
		return client.getAllAnnouncements();
	}

	@Override
	public boolean login(String username, String password) {
		return client.login(username, password);
	}

	@Override
	public UserInfo getUserInfo(String username, String password) {
		return client.getUserInfo(username, password);
	}

	@Override
	public String register(UserInfo userInfo, String username, String password) {
		return client.register(userInfo, username, password);
	}

}
