package test;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

//import java.io.Console; 

public class testJava {

	public static void main(String[] args) throws IOException {
		// Code in main(): only this is run when we run the file.
		
        
		// Primitive types in Java: 8 (all lower cased) 
		int myInt = 7;
		double shoeSize = 9.5;
		char myInitial = 'J';		

		// Non primitive types
		String myName = "Jonh";
		int legnth = myName.length(); 
			// calling methods on a non primitive type (does not work on primitive types)
		
		
		//System.out.println(myInt);
		
		// Calling a class method;
		newMethod("Shu ai");
		String greeting = anotherMethod("Shu ai");
		
		// Loop
		for (int i = 0; i < 10; i++) {
			//System.out.println("Hi!");
		}
		
		// Using another class
		testClass.dingDong();	// static method
		
		// Creating an object (class object)
		testClass myCat = new testClass();	// object: myCat
		myCat.name = "Lily";
		myCat.age = 3;
		
		
		// A mettre dans ClientHandler
		// 1) Verifier si fichier (base de donnees) existe
			// Si oui: ouvrir
			// Si non: creer
		// 2) ** Client entre un nom d'utilisateur et un mot de passe, Serveur les recoit
		// 3) Serveur verifie si nom d'utilisateur existe (Lire du fichier)
				// ??? Est-ce qu'il faut garder dans un tableau la liste de noms d'utilisateurs?
			// Si existe: serveur verifie correspondance avec mot de passe
				// Si correspondance: Connexion salle de clavardage; Serveur envoie au client 15 derniers msgs
				// Si non correspondance: Serveur rejette connexion du client. Client affiche "Erreur mot de passe"
			// Si n'existe pas: serveur cree nouveau mot de passe
		
//	    try {
//	        File myObj = new File("filename.txt");
//	        
//	        if (myObj.createNewFile()) {
//	          System.out.println("File created: " + myObj.getName());
//	        } 
//	        else {
//	          System.out.println("File already exists.");
//	        }
//	    } 
//	    catch (IOException e) {
//	        System.out.println("An error occurred.");
//	        e.printStackTrace();
//	    }
	    

		File f = new File("filename.txt");
	    boolean isFile = f.exists();
	    if (isFile == false) {
	    	f.createNewFile();
	    	System.out.println("New file!");
	    }
	    else {
	    	System.out.println("Opening existing file!");
	    }
	    
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
	    BufferedReader br = new BufferedReader(new  FileReader(f));

	    ///////
//		String name = System.console().readLine();
//		String password = System.console().readLine();
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = sc.nextLine();		
        System.out.println("Enter a password: ");
        String password = sc.nextLine();
        //////
        
 
        
	    // Reading
	    String line = br.readLine();
//	    line.replaceAll("[\\n ]", "");
	    boolean existingUser = false;
	    
        while (line != null) {
        	
        	if (line.equals(name)) {
        		existingUser = true;
        		System.out.println("Existing username: " + line); 
        		line = br.readLine();
        		
        		if (line.equals(password)) {
        			System.out.println("Right password: " + line); 
        		}
        		else {
        			System.out.println("Wrong password! Right password is: " + line); 
        		}
        		break;
        	}
//        	else {
//        		System.out.println("Found username: " + line);
//        	}

            line = br.readLine();
        }
        
        // Writing
        if (!existingUser) {   
        	System.out.println("Not existing username. Creating new user!");
    	    bw.write(name + "\n");
    	    bw.write(password + "\n");
    	    bw.flush();	   
        }

	    
	    // Closing read write streams
	    bw.flush();
	    bw.close();
	    br.close();
	    
	    sc.close();
	    
	}
	
	private static void newMethod(String name) {
		System.out.println("Hello this is " + name); 
		if (name.equals("Nobody")) {
			System.out.println("Sad.");
		}
	}
	
	private static String anotherMethod(String name) {	// Private method: can only be called in this file
//		System.out.println("Hello world!" + name); 
		return "Hello this is " + name;
	}
	
}
