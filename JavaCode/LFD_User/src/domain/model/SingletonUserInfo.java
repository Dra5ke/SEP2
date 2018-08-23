package domain.model;

public class SingletonUserInfo {
	private static SingletonUserInfo instance = null;
	private String name;
	private String address;
	private Date DOB;
	private String email;

	private SingletonUserInfo(String name, String address, String email, Date DOB) {

		this.name = name;
		this.address = address;
		this.email = email;
		this.DOB = DOB;

	}

	public static SingletonUserInfo getInstance(String name, String address, String email, Date DOB) {
		if (instance == null) {
			instance = new SingletonUserInfo(name, address, email, DOB);

		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return DOB;
	}

	public void setDate(Date date) {
		this.DOB = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name: " + name + " Address: " + address + " Email: " + email + " DOB: " + DOB;

	}

}
