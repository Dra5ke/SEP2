package LFDCompany.domain.model;

public class SingletonCompanyInfo {
	private static SingletonCompanyInfo instance = null;
	private String name;
	private String address;
	private String email;
	
	private SingletonCompanyInfo(String name, String address, String email)
	{
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	public static SingletonCompanyInfo getInstance(String name, String address, String email) {
		if (instance == null) {
			instance = new SingletonCompanyInfo(name, address, email);

		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
