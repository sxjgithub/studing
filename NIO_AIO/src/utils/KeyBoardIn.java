package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyBoardIn {
	
	public  static String println(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String  msg = null;
		try {
				msg = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
}
