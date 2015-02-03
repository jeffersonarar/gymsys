package br.ueg.unucet.gymsys.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	/**
	 * Método que retorna string com a primeira letra em maiúscula, o restante permanece o mesmo.
	 * 
	 * @param str valor
	 * @return valor com a primeira letra em maiúsculo
	 */
	public static String firstToUpperCase(String str) {
		return str.substring(0, 1).toUpperCase().concat(str.substring(1));
	}
	/**
	 * Método que retorna string com a primeira letra em minúsculo, o restante permanece o mesmo.
	 * 
	 * @param str valor
	 * @return valor com a primeira letra em minúsculo
	 */
	public static String firstToLowerCase(String str) {
		return str.substring(0, 1).toLowerCase().concat(str.substring(1));
	}
	
	public static String totalLowerCase(String str) {
		return str.toLowerCase();
	}
	
	public static String retirarExtensao(String string) {
		String resposta = null;
		if(string == null) return null;
		String extensao = null;
		int tamanho = string.length();
		extensao = string.substring((tamanho-4), tamanho);
		if(extensao.substring(0, 1).equalsIgnoreCase(".")){
			resposta= extensao;
		}else{
			resposta = string.substring((tamanho-5), tamanho);
		}
		return Utils.totalLowerCase(resposta);
	}
	
	public static Properties getProp() throws IOException {
		 Properties props = new Properties(); 
		 FileInputStream file = new FileInputStream( "C:/PROGII/Nova pasta/com.gymsys/src/main/java/br/ueg/unucet/gymsys/properties/config.properties"); 
		 props.load(file); 
		 return props; 
	} 
}