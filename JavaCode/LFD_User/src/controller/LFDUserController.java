package controller;

import domain.mediator.LFDUserModel;
import domain.model.Date;
import domain.model.SingletonUserInfo;
import domain.model.UserInfo;
import view.LFDUserView;
/**
 * Class that performs actions on both the view and model
 * 
 */
public class LFDUserController {
	private LFDUserModel model;
	private LFDUserView view;

	public LFDUserController(LFDUserModel model, LFDUserView view) {
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
			view.show(model.getAllAnnouncements().toString());
			break;
		case 2:
			int announcementNumber = Integer.parseInt(view.get("announcement number"));
			model.apply(announcementNumber-1,
					new UserInfo(SingletonUserInfo.getInstance("", "", "", null).getName(),
							SingletonUserInfo.getInstance("", "", "", null).getAddress(),
							SingletonUserInfo.getInstance("", "", "", null).getDate(),
							SingletonUserInfo.getInstance("", "", "", null).getEmail()));
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
					UserInfo userInfo = model.getUserInfo(username, password);
					SingletonUserInfo.getInstance(userInfo.getName(), userInfo.getAddress(), userInfo.getEmail(),
							userInfo.getDate());
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
			String day = view.get("day of birth");
			String month = view.get("month of birth");
			String year = view.get("year of birth");
			Date DOB = new Date();
			DOB.set(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
			SingletonUserInfo.getInstance(name, address, email, DOB);
			view.show(model.register(new UserInfo(name, address, DOB, email), username, password));
			break;
		default:
			view.show("Invalid input");
			break;
		}
	}
}
