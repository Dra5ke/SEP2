package domain.mediator;

import domain.model.AnnouncementList;
import domain.model.UserInfo;

/**
 * The Interface to the facade of the Domain Model
 * 
 * @see LFDModelManager
 *
 */
public interface LFDUserModel {
	/**
	 * Method used to send to the server an announcement's number and the
	 * information of a user in order to add it to the announcement's list of
	 * applied users
	 * 
	 * @param announcementNumber
	 *            specifies which announcement
	 * @param user
	 *            The information of the user that wants to apply
	 * @return A message from the server
	 */
	public String apply(int announcementNumber, UserInfo user);

	/**
	 * Method used to retrieve a list with all published announcements from the
	 * server
	 * 
	 * @return List with all published announcements
	 */
	public AnnouncementList getAllAnnouncements();

	/**
	 * Method used to send login credentials to the server so they can be verified
	 * 
	 * @param username
	 * @param password
	 * @return True or False depending on whether the login attempt was successful
	 *         or not
	 */
	public boolean login(String username, String password);

	/**
	 * Method used to have the server retrieve the information of the user with
	 * the given login credentials from the persistence
	 * 
	 * @param username
	 * @param password
	 * @return A UserInfo type object containing the information of the
	 *         successfully logged in user
	 */
	public UserInfo getUserInfo(String username, String password);
	/**
	 * Method used to send the information of the user and their chosen login
	 * credentials so the server can then store them by using the Persistence
	 * 
	 * @param companyInfo
	 * @param username
	 * @param password
	 * @return A message from the server
	 */
	public String register(UserInfo userInfo, String username, String password);

}
