package racingDrivers.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private ArrayList<String> result= new ArrayList<>();

	@Override
	public void writeToFile(String str) {
		BufferedWriter br = null;
		//System.out.println(result);
		try {
			br = new BufferedWriter(new FileWriter(str));
			for(String res: result){
				br.append(res);
				br.newLine();
				//br.append('\n');
				//br.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Write to console.

	@Override
	public void writeToStdout(String s) 
	{
		System.out.println(s + " \n");
	}


	public void storeNewResult(String res) 
	{
		result.add(res);
		//result=result+"\n";
		writeToStdout(res);
	}

		
}

