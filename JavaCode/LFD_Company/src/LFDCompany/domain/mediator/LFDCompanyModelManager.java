package LFDCompany.domain.mediator;

import java.io.IOException;
import java.util.Observable;

import LFDCompany.domain.model.Announcement;
import LFDCompany.domain.model.AnnouncementList;
import LFDCompany.domain.model.CompanyInfo;
import LFDCompany.domain.model.Init;

/**
 * Class that acts as Proxy to the RealSubject @see LFDCompanyClient
 *
 */
public class LFDCompanyModelManager extends Observable implements LFDCompanyModel {

	private LFDCompanyClient client;
	
	public LFDCompanyModelManager() {
		
		try
	      {
	         this.client = new LFDCompanyClient(Init.getInstance().getIp(), Init.getInstance().getPort());
	      }
	      catch (IOException e)
	      {
	         e.printStackTrace();
	      }

	}

	@Override
	public String addAnnouncement(Announcement announcement) {
		return this.client.addAnnouncement(announcement);
	}

	@Override
	public String removeAnnouncement(int announcementNumber) {
		return this.client.removeAnnouncement(announcementNumber);
	}

	@Override
	public String register(CompanyInfo companyInfo, String username, String password) {
		return client.register(companyInfo, username, password);
	}

	@Override
	public boolean login(String username, String password) {
		return client.login(username, password);
	}

	@Override
	public CompanyInfo getCompanyInfo(String username, String password) {
		return client.getCompanyInfo(username, password);
	}

	@Override
	public AnnouncementList getAnnouncements(String name) {
		return client.getAnnouncements(name);
	}

}
