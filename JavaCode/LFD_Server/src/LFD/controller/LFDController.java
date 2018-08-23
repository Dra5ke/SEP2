package LFD.controller;

import java.util.Observable;

import LFD.domain.mediator.LFDModel;
import LFD.domain.model.Announcement;
import LFD.domain.model.CompanyInfo;
import LFD.domain.model.Date;
import LFD.domain.model.UserInfo;
import LFD.view.LFDView;
/**
 * Class that performs actions on both the view and model
 * 
 */
public class LFDController {

	private LFDModel model;
	private LFDView view;

	public LFDController(LFDModel model, LFDView view) {
		this.model = model;
		this.view = view;
		
		Observable obs = (Observable) this.model;
		obs.addObserver(view);
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
			String name = view.get("company name");
			String address = view.get("company's address");
			String email = view.get("company's email address");
			String description = view.get("a description for the announcement");

			model.addAnnouncement(new Announcement(description, new CompanyInfo(name, address, email)));
			break;

		case 3:
			name = view.get("name of user");
			address = view.get("address of user");
			email = view.get("email address of user");
			Date DOB = new Date();
			DOB.set(Integer.parseInt(view.get("Day")), Integer.parseInt(view.get("Month")),
					Integer.parseInt(view.get("Year")));

			model.apply(Integer.parseInt(view.get("number of announcement")),
					new UserInfo(name, address, DOB, email));
			break;

		case 4:
			model.removeAnnouncement(Integer.parseInt(view.get("number of announcement"))-1);
			break;

		case 0:
			model.save();
			System.exit(1);
			break;
			
		default:
			view.show("INVALID INPUT");
			break;
		}
	}
}
