package GitChangedPathCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FormatChange {

	public static void main(String[] args) {
		ArrayList<String> onlyB = new ArrayList<>();
		ArrayList<String> onlyAG= new ArrayList<>();
		
		ArrayList<String> formatB= new ArrayList<>();
		ArrayList<String> formatAG= new ArrayList<>();
		
		try {
            // csv 데이터 파일
            File csv1 = new File("/Users/yoon/Desktop/onlyB.txt");
            File csv2 = new File("/Users/yoon/Desktop/onlyAG.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(csv1));
            BufferedReader br2 = new BufferedReader(new FileReader(csv2));
            String line = "";
       
            while ( (line = br1.readLine()) != null) {
            	onlyB.add(line);
            }
            
            while ( (line = br2.readLine()) != null) {
            	onlyAG.add(line);
            }
              
            br1.close();
            br2.close();
 
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		for(int i = 0; i < onlyB.size(); i++) {
			for(int j = 0; j < onlyAG.size(); j++) {
				String[] BList = onlyB.get(i).split(",");
				String[] AGList = onlyAG.get(j).split(",");
				
				//compare
				if(BList.length == AGList.length) {			//equal size
					if(BList[0].equals(AGList[0])) {		//equal BFC
						String pathContentB = "";
						String pathContentAG = "";
						for(int k = 2; k < BList.length; k++) {
							pathContentB += BList[k].trim();
							pathContentAG += AGList[k].trim(); 
						}
						if(pathContentB.equals(pathContentAG)) {
							formatB.add(onlyB.get(i));
							formatAG.add(onlyAG.get(j));
						}
					}
				}
			}
		}
		
		System.out.println("format change B");
		for(String b : formatB) {
			System.out.println(b);
		}
		
		System.out.println("format change AG");
		for(String ag : formatAG) {
			System.out.println(ag);
		}
		

	}

}
