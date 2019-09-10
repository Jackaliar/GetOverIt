
package Encoder;

import java.util.*;
	
public class Encoder {
	
	
	public Encoder() {

	}

	public String encode (String plainText)
	{
		Scanner input= new Scanner(System.in);
		
		List<String> table= new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
 				"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
 				"(", ")", "*", "+", ",", "-", ".", "/"));
		
		ArrayList<Integer> positionList = new ArrayList<Integer>();
		
		// Get index of plaintext in original table
		for(int i=0; i<plainText.length(); i++) 
		{	

			for(int j=0; j<table.size(); j++)
			{
				if(table.get(j).charAt(0) == plainText.charAt(i))
				{
					positionList.add(j);
				}
				if(plainText.isEmpty())
				{
					positionList.add(j);
				}
			}		

		}
		
		/*System.out.println("Enter encryption key:");
		String offset= input.next();*/
		
		// Random generate encryption key
        String randomSalt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();  
        
        while (salt.length() < 1) { // length of the random string.
            int index = (int) (rnd.nextFloat() * randomSalt.length());
            salt.append(randomSalt.charAt(index));
        }
        String offset = salt.toString().toUpperCase();
        
        //Check encryption key and create shifted table 
		String encoded="";
        System.out.println("Encrypting with "+ offset+"\n");
		if(offset.equals("A")) {
			encoded= plainText;
		}
		if(!offset.equals("A")) {
			System.out.println("Index of "+ offset+ " at"+ table.indexOf(offset));

			// Shift table by offset
			Collections.rotate(table, table.indexOf(offset));		
			System.out.println("Shifted Table: " + table);
			
			// Get encoded string from shifted table
			for(int i=0; i<positionList.size(); i++) 
			{
				System.out.println("Pos "+ positionList.get(i));					
				System.out.print(table.get(positionList.get(i))+"\n");
				encoded= encoded.concat(table.get(positionList.get(i)));				
			}	
		}
		
		encoded= offset.concat(encoded);
		input.close();
		return encoded;	
	}
	

	public String decode (String encodedText)
	{
		Scanner input= new Scanner(System.in);
		
		List<String> table= new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
 				"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
 				"(", ")", "*", "+", ",", "-", ".", "/"));
		
		int offsetPosition= 0;
		
		// Get index of offset in original table
		for(int j=0; j<table.size(); j++)
		{
			if(table.get(j).charAt(0) == encodedText.charAt(0))
			{
				offsetPosition= table.indexOf(encodedText.substring(0, 1));
				System.out.println("Offset " + encodedText.substring(0, 1) + " is at " + offsetPosition);
			}
			
		}
		
		// Shift table by offset
		Collections.rotate(table, offsetPosition);
		System.out.println("Shifted Table: " + table);
		
		
		ArrayList<Integer> positionList = new ArrayList<Integer>();
		
		// Get index of encoded text in shifted table
		for(int i=1; i<encodedText.length(); i++) 
		{	

			for(int j=0; j<table.size(); j++)
			{
				if(table.get(j).charAt(0) == encodedText.charAt(i))
				{
					positionList.add(j);
				}
				if(encodedText.isEmpty())
				{
					positionList.add(j);
				}
			}		

		}
		
		// Shift table back to original table
		Collections.rotate(table, -offsetPosition);
		System.out.println("Original Table: " + table);
		
		// Get decoded string from original table
		String decoded="";
		for(int i=0; i<positionList.size(); i++) 
		{
			System.out.println("Pos "+ positionList.get(i));					
			System.out.print(table.get(positionList.get(i))+"\n");
			decoded= decoded.concat(table.get(positionList.get(i)));				
		}
		
		System.out.println("Decoding...");

		input.close();
		return decoded;		
	}
	
	public static void main(String[] args)
	{	
		//List<String> alphabets= table.subList(0, 26);
		//List<String> numbers= table.subList(26, 35);
		//List<String> symbols= table.subList(36, 43);*/
		
		Scanner input= new Scanner(System.in);
		
		Encoder e = new Encoder();
		
		System.out.println("Do you want to encode or decode?");
		String choice= input.next().toLowerCase();
		input.nextLine();
		
		while(!choice.equals("encode") && !choice.equals("decode")) {
			System.out.println("Sorry we didnt get you, enter encode or decode");
			choice= input.next().toLowerCase();
			input.nextLine();
		}
		
		if(choice.equals("encode"))
		{	
			System.out.println("Enter the plaintext:");
			
			String plain= input.nextLine();
			
			System.out.println("Encoding "+ plain+"...");
			System.out.println("Encrypted text is " + e.encode(plain.toUpperCase()));	
			
		}
		
		if(choice.toLowerCase().equals("decode"))
		{	
			System.out.println("Enter the encoded text:");
			
			String encoded= input.next();
			
			System.out.println("Plaintext is " + e.decode(encoded.toUpperCase()));	
		}


		input.close();
	}
}




