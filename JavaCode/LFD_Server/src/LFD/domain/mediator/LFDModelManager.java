package LFD.domain.mediator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;

import LFD.domain.model.Announcement;
import LFD.domain.model.AnnouncementList;
import LFD.domain.model.CompanyInfo;
import LFD.domain.model.Init;
import LFD.domain.model.UserInfo;
import utility.persistence.MyDatabase;
/**
 * 
 * Class that acts as a facade to the Domain Model, accesses methods in Persistence Interface and starts the Server
 * @see LFDPersistence
 * @see LFDServer
 *
 */
public class LFDModelManager extends Observable implements LFDModel {

	private AnnouncementList list;
	private LFDServer server;
	private LFDPersistence database;

	/**
	 * Instantiates the AnnouncementList and Database then starts the server in a thread
	 */
	public LFDModelManager() {

		this.list = new AnnouncementList();
		try {
			
			database = new LFDDatabaseAdapter();
			
			list = database.load();
			
			this.server = new LFDServer(Init.getInstance().getPort(), this);
			Thread t = new Thread(this.server);
			t.start();
			
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void apply(int announcementNumber, UserInfo user) {
		list.apply(announcementNumber, user);
		this.setChanged();
		this.notifyObservers("Applied to announcement");
	}

	@Override
	public AnnouncementList getAllAnnouncements() {
		return list;
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		list.addAnnouncements(announcement);
		this.setChanged();
		this.notifyObservers("Added announcement");
	}

	@Override
	public void removeAnnouncement(int announcementNumber) {
		list.remove(announcementNumber);
		this.setChanged();
		this.notifyObservers("Removed announcement");
	}

	@Override
	public void registerUser(UserInfo userInfo, String username, String password) {
		try {
			database.registerUser(userInfo, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers("Registered user");
	}

	@Override
	public boolean loginUser(String username, String password) {
		try {
			return database.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserInfo getUserInfo(String username, String password) {
		UserInfo userInfo = null;
		try
		{
			userInfo = database.getUserInfo(username, password);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	public void registerCompany(CompanyInfo companyInfo, String username, String password) {
		try {
			database.registerCompany(companyInfo, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers("Registered company");
	}

	@Override
	public boolean loginCompany(String username, String password) {
		try {
			return database.loginCompany(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CompanyInfo getCompanyInfo(String username, String password) {
		CompanyInfo companyInfo = null;
		try
		{
			companyInfo = database.getCompanyInfo(username, password);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return companyInfo;
	}
	
	@Override
	public void notifyView(String text)
	{
		super.setChanged();
		super.notifyObservers(text);
	}
	
	@Override
	public void save() {
		try {
			database.save(list);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
