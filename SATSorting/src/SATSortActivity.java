
/**
 * This class is reads in the SAT data from a file and will have the ability to sort it by math, english, or total sat score
 * then print it out
 * 
 * @author Jeff Borland
 * @version 3-28-7
 */
import java.io.*;
import java.util.Scanner;
public class SATSortActivity
{    
	private Scanner scan; 
	StateSAT[] allSAT = new StateSAT[51];
	StateSAT[] sorted = new StateSAT[allSAT.length];


	public SATSortActivity()
	{
		//initialize allSAT
		reset();
		int i=0;
		while (scan.hasNext())
		{
			String theLine=scan.nextLine();
			String[] theLineSplit=theLine.split(","); //This will split that line by , returning an array
			String state=theLineSplit[0];
			int mathScore=Integer.parseInt(theLineSplit[1]);
			int englishScore=Integer.parseInt(theLineSplit[2]);         
			allSAT[i]=new StateSAT(state, mathScore, englishScore);
			i++;
		}
	}
	//Method will sort the math sat scores
	public void sortMath()
	{
		int[] mathScores = new int[allSAT.length];
		for (int i=0; i<allSAT.length; i++) {
			mathScores[i] = allSAT[i].getMathScore();
		}
		ArraySort m = new ArraySort();
		int[] sorted = m.quickSort(mathScores);
	}

	//Method will sort the english sat scores
	public void sortEnglish()
	{
		int[] engScores = new int[allSAT.length];
		for (int i=0; i<allSAT.length; i++) {
			engScores[i] = allSAT[i].getMathScore();
		}
		ArraySort m = new ArraySort();
		int[] sorted = m.quickSort(engScores);
	}

	//Method will sort the total sat scores
	public void sortSAT()
	{
		int[] totalScores = new int[allSAT.length];
		for (int i=0; i<allSAT.length; i++) {
			totalScores[i] = allSAT[i].getMathScore() + allSAT[i].getEnglishScore();
		}
		ArraySort m = new ArraySort();
		int[] sorted = m.quickSort(totalScores);
	}

	//Method will sort the total sat scores
	public void sortState()
	{
		String[] states = new String[allSAT.length];
		for (int i=0; i<allSAT.length; i++) {
			states[i] = allSAT[i].getState();
		}
		ArraySort m = new ArraySort();
		String[] sorted = m.quickSort(states);
	}    



	//Method will print out all of the arrays
	public void printAll()
	{
		System.out.println("***************************");
		for (int i=0; i<allSAT.length; i++)
			System.out.println(allSAT[i]); 
	}

	//method initializes and resets scanner
	private void reset()
	{
		//To reset your scanner:
		try
		{
			scan=new Scanner(new File("sats.csv"));
		}
		catch (IOException e){
			System.out.println("Cant find file");
		}
	}    

	public static void main (String[] args)
	{
		SATSortActivity s=new SATSortActivity();
		s.printAll();
	}


}
