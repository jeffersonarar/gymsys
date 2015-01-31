package br.ueg.unucet.gymsys.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class Manipulador { 
	public static Properties getProp() throws IOException {
		Properties props = new Properties(); 
		try{
		FileInputStream file = new FileInputStream( "C:/ZZZimagensGymSys/config.properties"); 
		props.load(file); 
		return props; 
		} catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	} 
	
	public static Properties getPropriets() throws IOException {
		Properties prop = getProp(); 
		return prop;
	}
	
	public static void main(String args[]) throws IOException {
		String login; //Variavel que guardar� o login do servidor. 
		String host; //Variavel que guardar� o host do servidor.
		String password; //Vari�vel que guardar� o password do us�ario.
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		Properties prop = getPropriets(); 
		login = prop.getProperty("prop.server.login"); 
		host = prop.getProperty("prop.server.host");
		password = prop.getProperty("prop.server.password"); 
		System.out.println("Login = " + login); 
		System.out.println("Host = " + host);
		System.out.println("Password = " + password);
	}
}
	
