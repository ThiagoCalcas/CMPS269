/*
Name: 		Matthew Baskharion and Thiago Lopes
Date:		October 15, 2015
Filename:	asn2.java

Analysis:
	Inputs
		osCyl		real 2 dp
		osSpher 	real 2 dp
		odCyl 		real 2 dp
		odSpher 	real 2 dp
		filename 	String
		designer 	String
		sgColor 	char
		sunGlasses 	Boolean
		numGlasses 	int
		
	Outputs
		counterGlasses	int
		osCyl			real 2 dp
		osSpher 		real 2 dp
		odCyl 			real 2 dp
		odSpher 		real 2 dp
		costGlasses		real 2 dp
		totalCost		real 2 dp
		designer		String
		designerCost	real 2dp
		numGlasses		int
		sgColor			char
	Constants
		OSCAR 150.00
		FRANKLIN 250.00
		GREEN_SUNNIES 10.00
		BROWN_SUNNIES 15.00
		OVERAGE 1.15
		DOLLAR "$"
		PRESCRIPTION_THRESHOLD 10
	Formula
		totalPrescription = osCyl + osSpher + odCyl + odSpher
		costGlasses = designerCost * OVERAGE
		costGlasses = costGlasses + sgColor
		totalCost = totalCost + costGlasses 
		
*/

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class asn2
{
	static final double OSCAR = 150.00;
	static final double FRANKLIN = 250.00;
	static final double GREEN_SUNNIES = 10.00;
	static final double BROWN_SUNNIES = 15.00;
	static final double OVERAGE = 1.15;
	static final double PRESCRIPTION_THRESHOLD = 10.00;
	static final String DOLLAR = "$";
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String filename = JOptionPane.showInputDialog("Input filepath");
		UtilityClass outfile = new UtilityClass(filename);
		outfile.openFile();
		String name = outfile.myName();
		outfile.writeLineToFile(name);
		System.out.println(name);
		String date = outfile.myDate();
		outfile.writeLineToFile(date);
		System.out.println(date);
		
		char sgColor = '0';
		String designer = "";
		double costColor = 0.0;
		double designerCost = 0.0;
		double costGlasses = 0.0;
		double totalCost = 0.0;
		String sub1 = "";
		String sub2 = "";
		
		System.out.println("Input OS Cylinder");
		double osCyl = sc.nextDouble();
		System.out.println("Input OS Sphere");
		double osSpher = sc.nextDouble();
		System.out.println("Input OD Cylinder");
		double odCyl = sc.nextDouble();
		System.out.println("Input OD Sphere");
		double odSpher = sc.nextDouble();
		double totalPrescription = osCyl + osSpher + odCyl + odSpher;
		System.out.printf("Total Prescription is %.2f\n", totalPrescription);
		outfile.writeLineToFile("Total Prescription is %.2f\n", totalPrescription);
		System.out.println("Input number of glasses");
		int numGlasses = sc.nextInt();	// loop condition
		
		int counterGlasses = 1;			// initialize loop variable
		while(counterGlasses <= numGlasses)	// loop condition
		{
			
//			// Ask if user wants to change the prescription
//			if (counterGlasses > 1)
//			{
//				
//			} //end if - asking user for change prescription
			
			designer = ""; // initialize loop variable (nested loop #1)
			while(!(designer.equals("Oscar") || designer.equals("Franklin"))) // loop condition (nested loop #1)
			{
				designer = JOptionPane.showInputDialog("Input designer");
				sub1 = designer.substring(0,1).toUpperCase();
				sub2 = designer.substring(1).toLowerCase();
				designer = sub1 + sub2; // update loop variable - nested loop #1
				if (designer.equals("Oscar"))
				{
					designerCost = OSCAR;
				}
				else if (designer.equals("Franklin"))
				{
					designerCost = FRANKLIN;
				}
				else
				{
					System.out.println("Designer must be Oscar or Franklin");
				}
				
			} // end nested loop #1
			
			// p+d subtotal
			System.out.printf("Subtotal of %s is %s%.2f\n", designer,DOLLAR,designerCost);
			outfile.writeLineToFile("Subtotal of %s is %s%.2f\n", designer,DOLLAR,designerCost);
			
			
			if (totalPrescription > PRESCRIPTION_THRESHOLD)
			{
				costGlasses = designerCost * OVERAGE;
			}
			else
			{
				costGlasses = designerCost;
			}
			
			// ask user if is sunglasses
			System.out.println("Are you purchasing sunglasses? Type True for yes, and False for no.");
			boolean sunGlasses = sc.nextBoolean();
			
			if (sunGlasses)
			{
				
				sgColor = '0'; // initialize loop variable (nested loop #2)
				while (!(sgColor == 'B' || sgColor == 'G')) // loop condition (nested loop #2)
				{
					System.out.println("Input sunglasses color");
					sgColor = JOptionPane.showInputDialog("Input sunglasses color").toUpperCase().charAt(0); // update loop variable (nested loop #2)
					switch(sgColor)
					{
						case 'G':
						{
							costColor = GREEN_SUNNIES;
							break;
						}
						case 'B':
						{
							costColor = BROWN_SUNNIES;
							break;
						}
						default:
						{
							System.out.println("Sunglasses color must be green or brown.");
							break;
						}
					}
					costGlasses = costGlasses + costColor;
				} // end nested loop #2
			}
			
			totalCost = totalCost + costGlasses;
			
			// p+d cost for this pair of glasses
			System.out.printf("## || Designer || osCyl || osSpher || odCyl || odSpher || Cost\n");
			System.out.printf("%-6d%-10s%7.2f%11.2f%9.2f%11.2f%5s%9.2f\n", counterGlasses,designer,osCyl,osSpher,odCyl,odSpher,DOLLAR,costGlasses);
			
			outfile.writeLineToFile("## || Designer || osCyl || osSpher || odCyl || odSpher || Cost\n");
			outfile.writeLineToFile("%-6d%-10s%7.2f%11.2f%9.2f%11.2f%5s%9.2f\n", counterGlasses,designer,osCyl,osSpher,odCyl,odSpher,DOLLAR,costGlasses);
			
			counterGlasses = counterGlasses + 1; //Update loop condition
		}//end main loop
		
		// p+d total cost for all glasses
		System.out.printf("Your %d pairs of glasses is %s%.2f",numGlasses,DOLLAR,totalCost);
		outfile.writeLineToFile("Your %d pairs of glasses is %s%.2f\n\n", numGlasses,DOLLAR,totalCost);
		outfile.closeFile();
	}//end main
}