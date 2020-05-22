package GitChangedPathCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Main2 {
	static ArrayList<String> commonBList = new ArrayList<>();
	static ArrayList<String> commonAGList = new ArrayList<>();
	static ArrayList<String> BList = new ArrayList<>();
	static ArrayList<String> AGList = new ArrayList<>();
	static ArrayList<String> traceList = new ArrayList<>();
	static ArrayList<String> formatChangeList = new ArrayList<>();

	public static void main(String[] args) {
		
		try {
            // txt 데이터 파일
            File txt1 = new File("/Users/yoon/Desktop/BObject.txt");
            File txt2 = new File("/Users/yoon/Desktop/AGObject.txt");
            File txt3 = new File("/Users/yoon/Desktop/TraceRecord.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(txt1));
            BufferedReader br2 = new BufferedReader(new FileReader(txt2));
            BufferedReader br3 = new BufferedReader(new FileReader(txt3));
            String line = "";
       
            while ( (line = br1.readLine()) != null) {
            	BList.add(line);
            }
            
            while ( (line = br2.readLine()) != null) {
            	AGList.add(line);
            }
            
            while ( (line = br3.readLine()) != null) {
            	traceList.add(line);
            }
              
            br1.close();
            br2.close();
            br3.close();
 
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		
		getCommonList();
		ArrayList<String> onlyBList = getOnlyBszzCommit();
		ArrayList<String> onlyAGList = getOnlyAGszzCommit();
		
		// PRINT
//		System.out.println("all B size: " + BList.size());
//		System.out.println("all AG size: " + AGList.size());
//		System.out.println("common B size: " + commonBList.size());
//		System.out.println("common AG size: " + commonAGList.size());
		
		writeResult(onlyBList, onlyAGList);
		
		
		formatChangeList = getFormatChange(onlyBList);
//		System.out.println("format change list size: " + formatChangeList.size());
		printArrayList(formatChangeList, 0);

	}

	public static void getCommonList() {
		ArrayList<String> cloneAGList = (ArrayList<String>) AGList.clone();
		for (int i = 0; i < BList.size(); i++) {
			for (int j = 0; j < cloneAGList.size(); j++) {
				if (BList.get(i).equals(cloneAGList.get(j))) {
					commonBList.add(BList.get(i));
					commonAGList.add(cloneAGList.get(j));
					cloneAGList.remove(j);
					break;
				}
			}
		}
	}

	public static ArrayList<String> getOnlyBszzCommit() {
		ArrayList<String> cloneBList = (ArrayList<String>) BList.clone();
		for (int i = 0; i < commonBList.size(); i++) {
			for (int j = 0; j < cloneBList.size(); j++) {
				if (commonBList.get(i).equals(cloneBList.get(j))) {
					cloneBList.remove(j);
					break;
				}
			}
		}

//		System.out.println("Only BList size: " + cloneBList.size());
//		printArrayList(BList, 0);

		return cloneBList;
	}

	public static ArrayList<String> getOnlyAGszzCommit() {
		ArrayList<String> cloneAGList = (ArrayList<String>) AGList.clone();
		for (int i = 0; i < commonAGList.size(); i++) {
			for (int j = 0; j < cloneAGList.size(); j++) {
				if (commonAGList.get(i).equals(cloneAGList.get(j))) {
					cloneAGList.remove(j);
					break;
				}
			}
		}
//		System.out.println("Only AGList size: " + cloneAGList.size());
//		printArrayList(AGList, 0);

		return cloneAGList;
	}
	
	public static ArrayList<String> getFormatChange(ArrayList<String> onlyBList) {
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < onlyBList.size(); i++) {
			for(int j = 0; j < traceList.size(); j++) {
				if(onlyBList.get(i).equals(traceList.get(j))) {
					list.add(onlyBList.get(i));
					traceList.remove(j);
					break;
				}
			}
		}
		
		return list;
	}

	public static void writeResult(ArrayList<String> b_list, ArrayList<String> ag_list) {
		File Bfile = new File("/Users/yoon/Desktop/BSZZOutput.txt");
		File AGfile = new File("/Users/yoon/Desktop/AGSZZOutput.txt");
		FileWriter Bwriter = null;
		FileWriter AGwriter = null;

		try {
			Bwriter = new FileWriter(Bfile, true);
			for (String str : b_list) {
				Bwriter.write(str + "\n");
			}
			Bwriter.flush();

			AGwriter = new FileWriter(AGfile, true);
			for (String str : ag_list) {
				AGwriter.write(str + "\n");
			}
			AGwriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Bwriter != null)
					Bwriter.close();
				if (AGwriter != null)
					AGwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printArrayList(ArrayList<String> arr, int op) {

		if(op == 0) {
			// print array
			for (String str : arr) {
				System.out.println(str);
			}
		} else {
			// print array with count
			for(int i = 0; i < arr.size(); i++) {
				System.out.println("count"+i+": " + arr.get(i));
			}
		}
		
	}

}
