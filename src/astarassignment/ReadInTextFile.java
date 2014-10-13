/* Astar Implementation using Java
 * Avinash Sewpersadh
 * References - http://www-cs-students.stanford.edu/~amitp/gameprog.html
 * 				http://www.aiwisdom.com/
 * 				http://wiki.gamegardens.com/Path_Finding_Tutorial"
 * 
 * avi.ukzn@gmail.com					
 * 
 * Notes:
 * Diagonal movement is not allowed, therefore the Manhattan Distance formula was used
 * 
 */

package astarassignment;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.apache.commons.lang3.ArrayUtils;

import astarassignment.CreateNodeGraph.Node;


public class ReadInTextFile {
	private static int WIDTH;
	private static  int HEIGHT;
	
	public static List<Node> routez;
	public static String mapfilepath;
	public static double mapmatrix[][];	
	
	//This method performs string manipulation on the next input line from the Scanner
	private static List<Character> createRow(String line) {
	    String s1 = line.replace("~", "9");
		String s2 = s1.replace(".","1");
		String s3 = s2.replace("@","1");
		String s4 = s3.replace("X","1");
		String s5 = s4.replace("x","1");
		String s6 = s5.replace("*","2");
		String s7 = s6.replace("^","3"); 
		char[] c = s7.toCharArray();
	    List<Character> chars = Arrays.asList(ArrayUtils.toObject(c));
	    return chars;
	}		 	
	
	public ReadInTextFile() {
		//Opens a dialog to choose the text file to be entered as a terrain map
		List<List<Character>> gamemap = new ArrayList<List<Character>>();
		JFileChooser fileChooser = new JFileChooser();

		if ( fileChooser.showOpenDialog (null) == JFileChooser.APPROVE_OPTION) {
		 java.io.File file = fileChooser.getSelectedFile(); // Get the selected file
		 mapfilepath = file.getAbsolutePath();
			try (Scanner s = new Scanner(new BufferedReader(new FileReader(file)))) {
				while(s.hasNextLine()) {
					gamemap.add(createRow(s.nextLine()));
									
				}																	
					System.out.println(gamemap);
			} catch (FileNotFoundException e) 	{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		mapmatrix = new double[gamemap.size()][gamemap.size()];
		for (int i=0; i <gamemap.size() ; i++) {
			List<Character> sr = gamemap.get(i);
			WIDTH = gamemap.size()-1;		//-1 to reference the correct destination!!
			HEIGHT = sr.size()-1;
			for (int j=0; j< sr.size(); j++) {
				mapmatrix[i][j] = Double.valueOf(String.valueOf(sr.get(j)));
			}
		} 
		
		CreateNodeGraph terrainmap = new CreateNodeGraph(mapmatrix);
		routez  = terrainmap.findPath(0, 0, WIDTH, HEIGHT);		
		System.out.println(routez);	
		
		}
		

public static void main(String[] args) {
	new ReadInTextFile();
	WriteTextFile();
	}

private static void WriteTextFile() {
	List<String> finalmap = new ArrayList<String>();
	try (Scanner s = new Scanner(new BufferedReader(new FileReader(mapfilepath)))) {
		while(s.hasNextLine()) {
		
							finalmap.add(s.nextLine());
		}																	
			System.out.println("finalmap "+finalmap);
	} catch (FileNotFoundException e) 	{
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	
	for (Node n: routez) {
		
		int curx = n.getX();
		int cury = n.getY();	
		String S1 = finalmap.get(curx);
		char[] c = S1.toCharArray();
		c[cury] = '#';
		
		System.out.println(c);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("TheRoute.txt"), true));
			StringBuffer sb = new StringBuffer();
			sb.append(c);
			bw.newLine();
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		
	}
	



}
}