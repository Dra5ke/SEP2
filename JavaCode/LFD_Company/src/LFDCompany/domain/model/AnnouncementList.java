package LFDCompany.domain.model;

import java.util.ArrayList;

public class AnnouncementList {
	private ArrayList<Announcement> list;

	public AnnouncementList() {
		list = new ArrayList<Announcement>();
	}

	public Announcement getAnnouncement(int i) {
		return list.get(i);
	}

	public void addAnnouncements(Announcement announcement) {
		list.add(announcement);
	}

	public void apply(int announcementNumber, UserInfo user) {
		list.get(announcementNumber).addUser(user);
	}

	public void remove(int announcementNumber) {
		list.remove(announcementNumber);
	}

	public int size() {
		return list.size();
	}

	public String toString() {
		String lst = "";
		int j = 1;
		for (int i = 0; i < list.size(); i++) {
			lst += j + ". " + list.get(i).getDescription() + " posted by " + list.get(i).getAnnouncer().getName()
					+ " " + "\n";
			j++;
		}

		return lst;
	}
}
