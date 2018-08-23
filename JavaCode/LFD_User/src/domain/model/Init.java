package domain.model;

import domain.model.Init;

public class Init {
	private String ip;
	private int port;
	private static Init instance;

	private Init() {

	}

	public static Init getInstance() {

		if (instance == null)
			instance = new Init();
		return instance;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
