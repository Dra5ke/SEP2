package LFD.domain.mediator;

import java.io.IOException;
import java.sql.SQLException;

import LFD.domain.model.Announcement;
import LFD.domain.model.AnnouncementList;
import LFD.domain.model.CompanyInfo;
import LFD.domain.model.UserInfo;
/**
 * Interface to the database adapter
 * 
 *
 */
public interface LFDPersistence {
	/**
	 * Method that takes the stored data and initializes the model
	 * @return An AnnouncementList corresponding to the data stored
	 * @throws SQLException
	 */
	public AnnouncementList load() throws SQLException;
	/**
	 * Method that takes the AnnouncementList and stores it
	 * {@link LFD.domain.mediator.LFDPersistence#save(Announcement, int)}
	 * @param list The AnnouncementList that needs to be stored
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void save(AnnouncementList list) throws IOException, NumberFormatException, SQLException;
	/**
	 * Method used to store the data of one Announcement
	 * {@link LFD.domain.mediator.LFDPersistence#save(AnnouncementList)}
	 * @param announcement
	 * @param id
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void save(Announcement announcement, int id) throws IOException, NumberFormatException, SQLException;
	/**
	 * Method used to check if there is any user stored with the given username and password
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean login(String username, String password) throws SQLException;
	/**
	 * Method that returns the information of a specific stored user
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public UserInfo getUserInfo(String username, String password) throws SQLException;
	/**
	 * Method that stores the user's information along with his login credentials
	 * @param userInfo
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void registerUser(UserInfo userInfo, String username, String password) throws SQLException;
	/**
	 * Method used to check if there is any company stored with the given username and password
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean loginCompany(String username, String password) throws SQLException;
	/**
	 * Method that returns the information of a specific stored user
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public CompanyInfo getCompanyInfo(String username, String password) throws SQLException;
	/**
	 * Method that stores the company's information along with his login credentials
	 * @param companyInfo
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void registerCompany(CompanyInfo companyInfo, String username, String password) throws SQLException;
}
