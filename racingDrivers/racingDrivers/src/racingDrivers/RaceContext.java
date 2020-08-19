package racingDrivers.driver;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;


import racingDrivers.util.FileProcessor;
import racingDrivers.util.Results;

import racingDrivers.util.MyLogger;
import racingDrivers.util.MyLogger.DebugLevel;


public class RaceContext implements DriverContextI{

	FileProcessor file;
	Results result;

	public RaceContext(String input){
		file = new FileProcessor(input);
		result = new Results();
	}

	public void raceStart(String output){
		String n =file.readLine();
		ArrayList<DriverContext> list=new ArrayList<>();
		for(int i=0;i<Integer.parseInt(n);i++){
			list.add(new DriverContext());
		}
		while(n!=null){
			n=file.readLine();
			if(n == null){
				break;
			}
			list = racer(list, n);
			String temp=new String();
			for(int i=0;i<list.size();i++){
				temp=temp+list.get(i).getCurrentState().toString() + "\t";
			}
			result.storeNewResult(temp);
		}
		result.writeToFile(output);
	}
		
	public ArrayList<DriverContext> racer(ArrayList<DriverContext> list, String n) {
		//reads one entire line and with space as delimiter breaks data accordingly.
		StringTokenizer tokenizer=new StringTokenizer(n," ");
		int i=0;
		while(tokenizer.hasMoreTokens()){
			//updating previous miles with new miles
			double newMiles=list.get(i).getMiles()+Double.parseDouble(tokenizer.nextToken());
			list.get(i).setMiles(newMiles);
			i++;
		}
		
		//assigning position after miles drove
		list=setRank(list);
		//MyLogger.writeMessage("State Change", DebugLevel.STATE);
		//setting the state from each state
		for(i=0;i<list.size();i++){
			//current state of driver to be set
			DriverContext d = list.get(i);
			DriverStateI s = d.getCurrentState().getState(d.getPosition(), list.size());
			//MyLogger.writeMessage("\t" + list.get(i), DebugLevel.STATE);
			d.setCurrentState(s);

		}
		return list;
	}

	public ArrayList<DriverContext> setRank(ArrayList<DriverContext> list){
		//position of drivers based on miles
		ArrayList<DriverContext> list1 = new ArrayList<>();
		
		//Treemap stores miles and corresponding list 
		Map<Double, List<Integer>> map =new TreeMap<>(Collections.reverseOrder());
		for(int i=0;i<list.size();i++){
			double mile = list.get(i).getMiles();
			int index = i;
			if(!map.containsKey(mile)){
				List temp=new ArrayList<>();
				temp.add(index);
				map.put(mile, temp);
			} else {
				map.get(mile).add(index);
			}
		}

		Set set=map.entrySet();
		Iterator it =set.iterator();
		int i=1;
		for(Map.Entry<Double, List<Integer>> e : map.entrySet()) {
			double miles=e.getKey();
			List<Integer> listIndex=map.get(miles);
			if(listIndex.size()>1){
				i=list.size();
				for(int l: listIndex){
					list.get(l).setPosition(i);
				}
			}
			else{
				int k=listIndex.get(0);
				list.get(k).setPosition(i);
				i++;
			}

		}

		return list;
	}
	
}