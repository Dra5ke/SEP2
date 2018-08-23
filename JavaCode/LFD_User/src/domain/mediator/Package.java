package domain.mediator;

import domain.model.CompanyInfo;
import domain.model.Announcement;
import domain.model.AnnouncementList;
import domain.model.UserInfo;
/**
 * Class used to send data between the client and the server by using Json
 * 
 *
 */
public class Package {
	public static final String LOGIN = "LOGIN";
	public static final String LOGINC = "LOGINC";
	public static final String ADD = "ADD";
	public static final String REMOVE = "REMOVE";
	public static final String REGISTERUSER = "REGISTERUSER";
	public static final String USER = "USER";
	public static final String REGISTERCOMPANY = "REGISTERCOMPANY";
	public static final String COMPANY = "COMPANY";
	public static final String GET = "GET";
	public static final String APPLY = "APPLY";
	public static final String EXIT = "EXIT";
	
	private UserInfo userInfo;
	private Announcement announcement;
	private String text;
	private AnnouncementList list;
	private int announcementNumber;
	private String username;
	private String password;
	private boolean success;
	private CompanyInfo companyInfo;
	
	public Package(String text, AnnouncementList list)
	{
		this.text = text;
		this.list = list;
	}
	public Package(String text, CompanyInfo companyInfo, String username, String password)
	{
		this.text = text;
		this.companyInfo = companyInfo;
		this.username = username;
		this.password = password;
	}
	public Package(String text, UserInfo userInfo, String username, String password)
	{
		this.text = text;
		this.userInfo = userInfo;
		this.username = username;
		this.password = password;
	}
	public Package(String text, UserInfo userInfo, int announcementNumber)
	{
		this.text = text;
		this.announcementNumber = announcementNumber;
		this.userInfo = userInfo;
		this.list = null;
	}
	public Package(String text, CompanyInfo companyInfo)
	{
		this.text = text;
		this.companyInfo = companyInfo;
	}
	public Package(String text, UserInfo userInfo)
	{
		this.text = text;
		this.userInfo = userInfo;
	}
	public Package(String text, String username, String password)
	{
		this.text = text;
		this.username = username;
		this.password = password;
	}
	
	public Package(String text, boolean succes)
	{
		this.text = text;
		this.success = succes;
	}

	public Package(String text, int announcementNumber)
	{
		this.text = text;
		this.announcementNumber = announcementNumber;
	}
	
	public Package(String text, Announcement announcement)
	{
		this.text = text;
		this.announcement = announcement;
	}
	
	public Package (String operation)
	{
		this.text = operation;
		this.list = null;
	}
	
	public String getText()
	{
		return text;
	}
	public boolean getSuccess()
	{
		return success;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public AnnouncementList getAllAnnouncements()
	{
		return list;
	}
	public UserInfo getUserInfo()
	{
		return userInfo;
	}
	public int getAnnouncementNumber()
	{
		return announcementNumber;
	}
	public Announcement getAnnouncement()
	{
		return announcement;
	}
	public CompanyInfo getCompanyInfo()
	{
		return companyInfo;
	}
	@Override
	public String toString()
	{
		if (list == null)
			 return "" + text;
	      else
	         return text + " " + list;
	}
	
}
