package LFD.domain.mediator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import LFD.domain.model.Announcement;
import LFD.domain.model.AnnouncementList;
import LFD.domain.model.CompanyInfo;
import LFD.domain.model.Date;
import LFD.domain.model.ID;
import LFD.domain.model.Init;
import LFD.domain.model.UserInfo;
import utility.persistence.MyDatabase;


public class LFDDatabaseAdapter implements LFDPersistence {

	private MyDatabase db;
	private static final String DRIVER = Init.getInstance().getDriver();
	private static final String URL = Init.getInstance().getUrl();
	private static final String USER = Init.getInstance().getUser();
	private static final String PASSWORD = Init.getInstance().getPw();

	public LFDDatabaseAdapter() throws ClassNotFoundException {
		this.db = new MyDatabase(DRIVER, URL, USER, PASSWORD);
	}

	@Override
	public AnnouncementList load() throws SQLException {

		String sql = "SELECT \"Announcement\".description FROM public.\"Announcement\" ORDER BY \"Announcement\".\"announcementId\"";
		;
		ArrayList<Object[]> results = db.query(sql);
		AnnouncementList announcementList = new AnnouncementList();

		for (int i = 0; i < results.size(); i++) {

			String description = results.get(i)[0].toString();
			sql = "SELECT \"CompanyInfo\".name, \"CompanyInfo\".address, \"CompanyInfo\".email "
					+ "FROM public.\"CompanyInfo\", public.\"Announcement\" "
					+ "WHERE \"Announcement\".\"companyId\" = \"CompanyInfo\".\"companyId\" "
					+ "AND \"Announcement\".\"announcementId\" = ?;";
			ArrayList<Object[]> company = db.query(sql, i);
			CompanyInfo companyInfo = new CompanyInfo(company.get(0)[0].toString(), company.get(0)[1].toString(),
					company.get(0)[2].toString());

			sql = "SELECT \"Applications\".\"userId\" " + "FROM public.\"Applications\", public.\"Announcement\" "
					+ "WHERE \"Announcement\".\"announcementId\" = \"Applications\".\"announcementId\" "
					+ "AND \"Announcement\".\"announcementId\" = ?;";
			ArrayList<Object[]> userIds = db.query(sql, i);

			sql = "SELECT \"UserInfo\".name, \"UserInfo\".address, \"UserInfo\".dob, \"UserInfo\".email "
					+ "FROM public.\"UserInfo\" WHERE \"UserInfo\".\"userId\" = ?";
			ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
			for (int j = 0; j < userIds.size(); j++) {
				ArrayList<Object[]> user = db.query(sql, Integer.parseInt(userIds.get(j)[0].toString()));
				String[] dob = user.get(0)[2].toString().split("-");
				Date date = new Date();
				date.set(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]), Integer.parseInt(dob[2]));
				userList.add(new UserInfo(user.get(0)[0].toString(), user.get(0)[1].toString(), date,
						user.get(0)[3].toString()));
			}
			Announcement announcement = new Announcement(description, companyInfo);
			announcement.setUserList(userList);
			announcementList.addAnnouncements(announcement);
		}

		ArrayList<Object[]> numberOfUsers = db.query("SELECT COUNT(*) FROM public.\"UserInfo\"");
		ArrayList<Object[]> numberOfCompanies = db.query("SELECT COUNT(*) FROM public.\"CompanyInfo\"");
		ID.setCompany(Integer.parseInt(numberOfCompanies.get(0)[0].toString()));
		ID.setUser(Integer.parseInt(numberOfUsers.get(0)[0].toString()));
		return announcementList;
	}

	@Override
	public synchronized void save(AnnouncementList list) throws IOException, NumberFormatException, SQLException {
		// Save application table
		String sql = "SELECT \"applicationId\", \"userId\", \"announcementId\"" + "FROM \"Applications\"";
		ArrayList<Object[]> results = db.query(sql);
		int i = 0;
		// Truncate Announcement and auto truncate application
		try {
			sql = "TRUNCATE TABLE public.\"Announcement\" CASCADE;";
			db.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Populate announcement table
		for (i = 0; i < list.size(); i++) {
			save(list.getAnnouncement(i), i);
		}
		// Repopulate application with original table
		sql = "INSERT INTO public.\"Applications\" (\"applicationId\", \"userId\", \"announcementId\") "
				+ "VALUES (? , ? , ?);";
		for (i = 0; i < results.size(); i++) {
			db.update(sql, Integer.parseInt(results.get(i)[0].toString()),
					Integer.parseInt(results.get(i)[1].toString()), Integer.parseInt(results.get(i)[2].toString()));
		}
	}

	@Override
	public synchronized void save(Announcement announcement, int id)
			throws IOException, NumberFormatException, SQLException {
		String sql = "INSERT INTO public.\"Announcement\" (\"announcementId\", \"companyId\", \"description\") "
				+ "VALUES (? , ? , ?);";
		String name = announcement.getAnnouncer().getName();
		String sql1 = "SELECT \"CompanyInfo\".\"companyId\" FROM \"CompanyInfo\" WHERE \"CompanyInfo\".name = '" + name
				+ "';";
		ArrayList<Object[]> result = db.query(sql1);
		db.update(sql, id, Integer.parseInt(result.get(0)[0].toString()), announcement.getDescription());
	}

	@Override
	public boolean login(String username, String password) throws SQLException {
		String sql = "SELECT \"userId\" FROM public.\"UserInfo\" WHERE \"userName\" = ? AND pw = ? ;";
		ArrayList<Object[]> results = db.query(sql, username, password);
		if (results.size() < 1)
			return false;
		return true;

	}

	@Override
	public UserInfo getUserInfo(String username, String password) throws SQLException {
		String sql = "SELECT \"UserInfo\".name, \"UserInfo\".address, \"UserInfo\".dob, \"UserInfo\".email "
				+ "FROM public.\"UserInfo\" WHERE \"UserInfo\".\"pw\" = ? AND \"UserInfo\".\"userName\" = ?";
		ArrayList<Object[]> user = db.query(sql, password, username);
		String[] dob = user.get(0)[2].toString().split("-");
		Date date = new Date();
		date.set(Integer.parseInt(dob[0]), Integer.parseInt(dob[1]), Integer.parseInt(dob[2]));
		return new UserInfo(user.get(0)[0].toString(), user.get(0)[1].getClass().toString(), date,
				user.get(0)[3].toString());
	}

	@Override
	public void registerUser(UserInfo userInfo, String username, String password) throws SQLException {
		String sql = "INSERT INTO public.\"UserInfo\" (\"userId\", name, address, dob, email, \"userName\", pw) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		db.update(sql, ID.getUserId(), userInfo.getName(), userInfo.getAddress(), userInfo.getDate().toString(),
				userInfo.getEmail(), username, password);
	}

	@Override
	public boolean loginCompany(String username, String password) throws SQLException {
		String sql = "SELECT \"companyId\" FROM public.\"CompanyInfo\" WHERE \"userName\" = ? AND pw = ? ;";
		ArrayList<Object[]> results = db.query(sql, username, password);
		if (results.size() < 1)
			return false;
		return true;
	}

	@Override
	public CompanyInfo getCompanyInfo(String username, String password) throws SQLException {
		String sql = "SELECT \"CompanyInfo\".name, \"CompanyInfo\".address, \"CompanyInfo\".email "
				+ "FROM public.\"CompanyInfo\" WHERE \"CompanyInfo\".\"pw\" = ? AND \"CompanyInfo\".\"userName\" = ?";
		ArrayList<Object[]> company = db.query(sql, password, username);
		return new CompanyInfo(company.get(0)[0].toString(), company.get(0)[1].getClass().toString(),
				company.get(0)[2].toString());
	}

	@Override
	public void registerCompany(CompanyInfo companyInfo, String username, String password) throws SQLException {
		String sql = "INSERT INTO public.\"CompanyInfo\" (\"companyId\", name, address, email, \"userName\", pw) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		db.update(sql, ID.getCompanyId(), companyInfo.getName(), companyInfo.getAddress(), companyInfo.getEmail(),
				username, password);
	}
}
