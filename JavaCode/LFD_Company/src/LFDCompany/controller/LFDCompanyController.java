package LFDCompany.controller;

import LFDCompany.domain.mediator.LFDCompanyModel;
import LFDCompany.domain.model.Announcement;
import LFDCompany.domain.model.AnnouncementList;
import LFDCompany.domain.model.CompanyInfo;
import LFDCompany.view.LFDCompanyView;
import LFDCompany.domain.model.SingletonCompanyInfo;
/**
 * Class that performs actions on both the view and model
 * 
 */
public class LFDCompanyController {
	private LFDCompanyModel model;
	private LFDCompanyView view;

	public LFDCompanyController(LFDCompanyModel model, LFDCompanyView view) {
		this.model = model;
		this.view = view;
	}
	/**
	 * Method that handles what the user chose
	 * @param choice Corresponds to the items displayed in the menu for the user
	 */
	public void execute(int choice) {
		switch (choice) {

		case 1:
			String description = view.get("description for announcement");

			model.addAnnouncement(new Announcement(description,
					new CompanyInfo(SingletonCompanyInfo.getInstance("", "", "").getName(),
							SingletonCompanyInfo.getInstance("", "", "").getAddress(),
							SingletonCompanyInfo.getInstance("", "", "").getEmail())));

			break;
		case 2:
			int announcementNumber = Integer.parseInt(view.get("announcement number"));
			model.removeAnnouncement(announcementNumber-1);
			break;
		case 3:
			AnnouncementList list = model.getAnnouncements(SingletonCompanyInfo.getInstance("", "", "").getName());
			view.show(list.toString());
			announcementNumber = Integer
					.parseInt(view.get("an announcement's number to see the people who applied to it or 0 to return"));
			try {
				if (announcementNumber != 0) {
					view.show(list.getAnnouncement(announcementNumber - 1).getUserList());
					int user = Integer
							.parseInt(view.get("a user's number to hire him or 0 if you do not want to hire someone"));
					if (user != 0) {
						model.removeAnnouncement(announcementNumber - 1);
						view.show("User hired and announcement removed.");
					}
				}
			} catch (IndexOutOfBoundsException e) {
				view.show("You have published no announcements");
			}
			break;
		case 0:
			System.exit(1);
			break;
		case -1:
			boolean succesfulLogin = false;
			view.show("Please enter the username and password: ");
			while (succesfulLogin == false) {
				String username = view.get("Username");
				String password = view.get("Password");
				if (model.login(username, password) == true) {
					succesfulLogin = true;
					CompanyInfo companyInfo = model.getCompanyInfo(username, password);
					SingletonCompanyInfo.getInstance(companyInfo.getName(), companyInfo.getAddress(),
							companyInfo.getEmail());
				} else {
					view.show("The username or password were incorrect");
				}
			}
			break;
		case -2:
			String username = view.get("Username");
			String password = view.get("Password");
			String name = view.get("your name");
			String address = view.get("your address");
			String email = view.get("your email address");
			SingletonCompanyInfo.getInstance(name, address, email);
			view.show(model.register(new CompanyInfo(name, address, email), username, password));
			break;
		default:
			view.show("Invalid input");
			break;
		}
	}
}
