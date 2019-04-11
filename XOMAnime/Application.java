/* Author: Corbin Tanchip
 * Created on: May 19 2017
 * Last Modified on: May 24 2017
 * 
 * This creates an application which provides users with an interactive library
 * where they can add, search, and view data of their animes.
 */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import nu.xom.*;

public class Application {
	
	/* These are the fields for this class
	 * 
	 * names is the element at the highest hierarchy in the parent structure
	 * doc creates a document for names' data to be inputed
	 * file creates a xml file which is saved into the project's folder
	 * builder is used for building objects from the saved xml file
	 */
	
	private static Element names = new Element("names");
	private static Document doc = new Document(names);
	private static File file = new File("myfile.xml");
	private static Builder builder = new Builder();

	/*	
	 *  
	 */
	
	public static void addData() {
		
		try 
		  { 
		   Document doc = builder.build(file);
		   Element root = doc.getRootElement();
		   Elements names2 = root.getChildElements();
		   
		  for (int i = 0; i < names2.size(); i++) {
			  
			 Element info = new Element ("info");
			 Element anime = new Element("anime");
			 Element  author = new Element("author");
			 Element rating = new Element("rating");
			
			 names.appendChild(info);
			 info.appendChild(anime);
			 info.appendChild(author);
			 info.appendChild(rating);
				
			 anime.appendChild(names2.get(i).getFirstChildElement("anime").getValue());
			 author.appendChild(names2.get(i).getFirstChildElement("author").getValue());
			 rating.appendChild(names2.get(i).getFirstChildElement("rating").getValue());
			 //  
		   }
			 }
		
		 catch(IOException e)
		  {
		   System.out.println("Your data has been successfully saved to the xml file");
		   System.out.println("The error states either");
		   System.out.println("Your library was empty previously");
		   System.out.println("The format of elements in the xml document is different from the expected");
		   System.out.println();
		  }

		catch(ParsingException e)
		  {
			System.out.println("Your data has been successfully saved to the xml file");
			System.out.println("The error states either");
			System.out.println("Your library was empty previously");
			System.out.println("The format of elements in the xml document is different from the expected");
			System.out.println();
		  } 
		
	}

	public static void main(String[] args) {
	
		boolean notDone = false;
		
		Scanner a = new Scanner(System.in);
		Scanner b = new Scanner(System.in);
		Scanner c = new Scanner(System.in);
		Scanner bRating = new Scanner(System.in);
		
		do {
		System.out.println();
		System.out.println("> | Anime Library |");
		System.out.println("> Available commands");
		System.out.println("> add: Add an anime to your library");
		System.out.println("> search: Search an anime in your library");
		System.out.println("> view: Display your library");
		System.out.println("> quit: Exit program");
		String test = a.nextLine();
		System.out.println();
		
		if (test.equals("add")) { //add
			
			addData();
			
			System.out.println("User Information");
			System.out.print("Enter anime: ");
			String test2 = b.nextLine();
			System.out.print("Enter author: ");
			String test3 = b.nextLine();
			int test4;
			
			do {
			System.out.print("Enter rating <1 - 10>: ");
			test4 = bRating.nextInt();
			if (test4 < 0 || test4 > 10) {
				System.out.println("Please enter a valid rating");
			}
			} while (test4 < 0 || test4 > 10);
		
			String test5 = Integer.toString(test4);
			Element info = new Element ("info");
			Element anime = new Element("anime");
			Element author = new Element("author");
			Element rating = new Element("rating");
		
			names.appendChild(info);
			info.appendChild(anime);
			info.appendChild(author);
			info.appendChild(rating);
			
			anime.appendChild(test2);
			author.appendChild(test3);
			rating.appendChild(test5);
	
			try //writes
			{
			FileWriter xmlfile = new FileWriter("myfile.xml");
			BufferedWriter writer = new BufferedWriter(xmlfile);
			
			writer.write(names.toXML());
			writer.close();
			}

			catch (IOException e)
			{

			} //writes
						
			test2 = new String();
			test3 = new String();
			test5 = new String();
		} //add
		
		//search
		
		else if (test.equals("search")) {
			
			System.out.println("> Anime Title");
			String test5 = c.nextLine();
			System.out.println();
			
			try {
				
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			Elements names2 = root.getChildElements();
			boolean found = false;
			System.out.println("> Result");
			System.out.println();
			for (int j = 0; j < names2.size(); j++)
			  {
				if (names2.get(j).getFirstChildElement("anime").getValue().equals(test5))
			          {
					System.out.println("Anime: " + names2.get(j).getFirstChildElement("anime").getValue());
					System.out.println("Author: " + names2.get(j).getFirstChildElement("rating").getValue());
					System.out.println("Rating: " + names2.get(j).getFirstChildElement("author").getValue());
					System.out.println();
					found = true;
					
					System.out.println("XML Format");
					System.out.println(names2.get(j).toXML());
					System.out.println();
				  }
				
			  }
			
			if (found == false) {
				System.out.println("The anime you have searched for does not exist in your library");
				System.out.println();
			}
			
			}
			
			catch(IOException e)
			  {
				System.out.println("Error: " + e);
			  }

			catch(ParsingException e)
			  {
				System.out.println("Error: " + e);
			  }
			
		} //search 
		
		else if (test.equals("view")) { //view
			
			try
			  {
				
			   Document doc = builder.build(file);
			   Element root = doc.getRootElement();
			   Elements names2 = root.getChildElements();
			   System.out.println("> Library");
			   
			  for (int i = 0; i < names2.size(); i++) {
				 
				  
				   System.out.println("Anime: " + names2.get(i).getFirstChildElement("anime").getValue());
				   System.out.println("Author: " + names2.get(i).getFirstChildElement("author").getValue());
				   System.out.println("Rating: " + names2.get(i).getFirstChildElement("rating").getValue());  
				   System.out.println();
			  }
			  
			  }
			
			   catch(IOException e)
				  {
				    System.out.println("The error states either");
					System.out.println("Your library is empty");
					System.out.println("The format of elements in the xml document is different from the expected");
				  }

				catch(ParsingException e)
				  {
					System.out.println("The error states either");
					System.out.println("Your library is empty");
					System.out.println("The format of elements in the xml document is different from the expected");
				  }
		} //view
		
		else if (test.equals("quit")) { //quit
			notDone = true;
		} //quit
		
		else {
			System.out.println("Please enter a valid command");
		}
		
		}while(notDone == false);
		
		
		 a.close();
		 b.close();
		 c.close();
		 bRating.close();
		
}
}
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	