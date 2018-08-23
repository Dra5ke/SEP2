package LFD.domain.mediator;

import LFD.domain.model.Announcement;
import LFD.domain.model.AnnouncementList;
import LFD.domain.model.CompanyInfo;
import LFD.domain.model.UserInfo;
/**
 * The Interface to the facade of the Domain Model
 * 
 * @see LFDModelManager
 *
 */
public interface LFDModel
{
	/**
	 * Method used to add a user to a specific announcement's list of applied users
	 * @param announcementNumber Used to specify which announcement the user wants to apply to
	 * @param user Holds the information of the user @see UserInfo
	 */
   public void apply(int announcementNumber, UserInfo user);
   /**
    * Method that returns the AnnouncementList from the model 
    * @see AnnouncementList
    * @return List that contains all the published announcements 
    */
   public AnnouncementList getAllAnnouncements();
   /**
    * Method used to add a new Announcement to the AnnouncementList
    * @see Announcement
    * @param announcement
    */
   public void addAnnouncement(Announcement announcement);
   /**
    * Method that removes an Announcement from the AnnouncementList
    * @see Announcement
    * @see AnnouncementList
    * @param announcementNumber Used to specify which Announcement should be removed
    */
   public void removeAnnouncement(int announcementNumber);
   /**
    * Method that uses the Persistence Interface to register a user
    * @see LFDPersistence
    * 
    * @param userInfo The user's information stored in a UserInfo class
    * @param username The username chosen by the user stored only in the Persistence
    * @param password The password chosen by the user stored only in the Persistence
    */
   public void registerUser(UserInfo userInfo, String username, String password);
   /**
    * Method that uses the Persistence Interface to login a user
    * @see LFDPersistence
    * 
    * @param username The username entered by the user
    * @param password The password entered by the user
    * @return
    */
   public boolean loginUser(String username, String password);
   /**
    * Method used to return the user's information after a successful login attempt
    * @see LFDPersistence
    * 
    * @param username
    * @param password
    * @return Returns a UserInfo type object used by the system
    */
   public UserInfo getUserInfo(String username, String password);
   /**
    * Method that uses the Persistence Interface to register a company
    * @see LFDPersistence
    * 
    * @param companyInfo The company's information stored in a UserInfo class
    * @param username The username chosen by the company stored only in the Persistence
    * @param password The password chosen by the company stored only in the Persistence
    */
   public void registerCompany(CompanyInfo companyInfo, String username, String password);
   /**
    * Method that uses the Persistence Interface to login a company
    * @see LFDPersistence
    * 
    * @param username The username entered by the company
    * @param password The password entered by the company
    * @return
    */
   public boolean loginCompany(String username, String password);
   /**
    * Method used to return the company's information after a successful login attempt
    * @see LFDPersistence
    * 
    * @param username
    * @param password
    * @return Returns a CompanyInfo type object used by the system
    */
   public CompanyInfo getCompanyInfo(String username, String password);
   /**
    * Method that uses the Persistence interface to save the data
    */
   public void save();
   public void notifyView(String text);
}

