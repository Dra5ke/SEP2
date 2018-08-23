package LFDCompany.domain.mediator;

import LFDCompany.domain.model.Announcement;
import LFDCompany.domain.model.AnnouncementList;
import LFDCompany.domain.model.CompanyInfo;

/**
 * The Interface to the facade of the Domain Model
 * 
 * @see LFDModelManager
 *
 */
public interface LFDCompanyModel {
	/**
	 * Method used to send an announcement to the server in order to add it to the
	 * list
	 * 
	 * @see Announcement
	 * @param announcement
	 * @return A message from the server
	 */
	public String addAnnouncement(Announcement announcement);

	/**
	 * Method used to send the number of an announcement to the server and then
	 * remove it from the list
	 * 
	 * @param announcementNumber
	 * @return A message from the server
	 */
	public String removeAnnouncement(int announcementNumber);

	/**
	 * Method used to send the information of the company and their chosen login
	 * credentials so the server can then store them by using the Persistence
	 * 
	 * @param companyInfo
	 * @param username
	 * @param password
	 * @return A message from the server
	 */
	public String register(CompanyInfo companyInfo, String username, String password);

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
	 * Method used to have the server retrieve the information of the company with
	 * the given login credentials from the persistence
	 * 
	 * @param username
	 * @param password
	 * @return A CompanyInfo type object containing the information of the
	 *         successfully logged in company
	 */
	public CompanyInfo getCompanyInfo(String username, String password);

	/**
	 * Method used to send the name of the company to the server so it can send back
	 * an AnnouncementList containing all the announcements published by this
	 * company
	 * 
	 * @param name Name of the company that published announcements
	 * @return AnnouncementList containing announcements published by the company
	 */
	public AnnouncementList getAnnouncements(String name);
}
