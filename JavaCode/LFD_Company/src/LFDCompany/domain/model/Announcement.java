package LFDCompany.domain.model;

import java.util.ArrayList;

public class Announcement {
	public ArrayList<UserInfo> userList;
	public String description;
	public CompanyInfo announcer;

	public Announcement(String description, CompanyInfo announcer) {
		userList = new ArrayList<>();
		this.description = description;
		this.announcer = announcer;
	}

	public CompanyInfo getAnnouncer() {
		return announcer;
	}

	public void setUserList(ArrayList<UserInfo> userList) {
		this.userList = userList;
	}

	public void addUser(UserInfo user) {
		userList.add(user);
	}

	public String getDescription() {
		return description;
	}

	public String getUserList() {
		String returnString = "";
		int i = 1;
		for (UserInfo userInfo : userList) {
			returnString = "" + i + ". " + userInfo.getName() + " Address: " + userInfo.getAddress() + " Email: "
					+ userInfo.getEmail() + "\n";
		}
		return returnString;
	}

}
