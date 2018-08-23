package LFD.domain.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import LFD.domain.model.Init;

public class Init {
	private int port;
	private static Init instance;
	private String driver;
	private String url;
	private String user;
	private String pw;
	private File connectionData = new File("connectionData.txt");

	private Init() {

	}

	public static Init getInstance() {

		if (instance == null)
			instance = new Init();
		return instance;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void getData() {
		boolean cont = true;
		Scanner sc;
		try {
			Scanner scan = new Scanner(connectionData);
			String[] connectionData = scan.nextLine().split(" ");
			Init.getInstance().setPort(Integer.parseInt(connectionData[0]));
			Init.getInstance().setDriver(connectionData[1]);
			Init.getInstance().setUrl(connectionData[2]);
			Init.getInstance().setUser(connectionData[3]);
			Init.getInstance().setPw(connectionData[4]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
