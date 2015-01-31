package br.ueg.unucet.gymsys.properties;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Configuration {

	private Properties propeties;	

	private String ipServer;
	private int portServer;
	private String rmiName;	

	public Configuration(Properties propeties) {
		this.propeties = propeties;
		load();
	}

	/**
	 * Ler as propriedades a partir do arquivo de properties.
	 */
	private void load() {
		InetAddress thisIp = null;
		try {
			thisIp = InetAddress.getLocalHost();
			System.out.println("IP:" + thisIp.getHostAddress());
			this.ipServer = propeties.getProperty("ipServer", thisIp.getHostAddress());
			this.portServer = Integer.parseInt(propeties.getProperty("portSerer", "1099"));
			this.rmiName = propeties.getProperty("rmiName", "calcServer");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Properties getPropeties() {
		return propeties;
	}

	public String getRmiName() {
		return rmiName;
	}
	
	public String getIpServer() {
		return ipServer;
	}

	public int getPortServer() {
		return portServer;
	}
}
