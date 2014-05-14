//package testrun;

import java.util.*;

public class Main
{
	//declarations
	static String[] alpha = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
	static Random rand = new Random();

	//array lists
	static ArrayList<String> row1 = new ArrayList<String>();
	static ArrayList<String> row2 = new ArrayList<String>();
	static ArrayList<String> row3 = new ArrayList<String>();
	static ArrayList<String> row4 = new ArrayList<String>();
	static ArrayList<String> row5 = new ArrayList<String>();

	public static void main(String[] args)
	{
		code();

		for(int i = 0; i < row1.size(); i++)
		{
			System.out.print(row1.get(i) + " ");
		}
		System.out.println();

		for(int i = 0; i < row2.size(); i++)
		{
			System.out.print(row2.get(i) + " ");
		}
		System.out.println();

		for(int i = 0; i < row3.size(); i++)
		{
			System.out.print(row3.get(i) + " ");
		}
		System.out.println();

		for(int i = 0; i < row4.size(); i++)
		{
			System.out.print(row4.get(i) + " ");
		}
		System.out.println();

		for(int i = 0; i < row5.size(); i++)
		{
			System.out.print(row5.get(i) + " ");
		}
		System.out.println();

		System.out.println("To end game enter '//'");
		System.out.println("Enter words found: ");
		endGame();
	} // end main

	//iterator to remove brackets and commas
	public static String join(Iterable<?> elements, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		for(Object e: elements)
		{
			sb.append(delimiter);
			sb.append(e);
		}
		return sb.toString();
	}//end String join

	//generate arrayList items
	public static void code()
	{
		row1.add(alpha[produceNumber()]);
		row1.add(alpha[produceNumber()]);
		row1.add(alpha[produceNumber()]);
		row1.add(alpha[produceNumber()]);
		row1.add(alpha[produceNumber()]);

		row2.add(alpha[produceNumber()]);
		row2.add(alpha[produceNumber()]);
		row2.add(alpha[produceNumber()]);
		row2.add(alpha[produceNumber()]);
		row2.add(alpha[produceNumber()]);

		row3.add(alpha[produceNumber()]);
		row3.add(alpha[produceNumber()]);
		row3.add(alpha[produceNumber()]);
		row3.add(alpha[produceNumber()]);
		row3.add(alpha[produceNumber()]);

		row4.add(alpha[produceNumber()]);
		row4.add(alpha[produceNumber()]);
		row4.add(alpha[produceNumber()]);
		row4.add(alpha[produceNumber()]);
		row4.add(alpha[produceNumber()]);

		row5.add(alpha[produceNumber()]);
		row5.add(alpha[produceNumber()]);
		row5.add(alpha[produceNumber()]);
		row5.add(alpha[produceNumber()]);
		row5.add(alpha[produceNumber()]);
	}//end code

	//get random letter from string array
	public static int produceNumber()
	{
		return (rand.nextInt(alpha.length));
	}//end produceNumber

	//userInput for words found
	//endGame sentinel
	public static void endGame()
	{
		Scanner userInput = new Scanner(System.in);
		String input;
		input = userInput.nextLine().toUpperCase();
		if(input.contains("//"))
		{
			System.exit(0);
		}
		else
		{
			endGame();
		}
	}//end endGame
}
