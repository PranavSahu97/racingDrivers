package racingDrivers.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

	private BufferedReader br = null;
	
	public BufferedReader getBr() {
		return br;
	}
	public void setBr(BufferedReader brIn) {
		this.br = brIn;
	}
	
	public FileProcessor(String inputIn) {
		
		try {
			br = new BufferedReader(new FileReader(inputIn));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine(){
		String str=null;	
		try{
			str = br.readLine();	    	
		} catch(Exception e){
			e.printStackTrace();
		}
		return str;

	}

	public void close(){
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}