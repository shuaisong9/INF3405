package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread { // pour traiter la demande de chaque client sur un socket particulier
	private Socket socket; 
	private int clientNumber; 
	public ClientHandler(Socket socket, int clientNumber) {
		this.socket = socket;
		this.clientNumber = clientNumber; System.out.println("New connection with client#" + clientNumber + " at" + socket);
	}
	
	
	public boolean verifyUsernamePassword(String name, String password) throws IOException {
		
		// Opening file
		File f = new File("filename.txt");
	    boolean isFile = f.exists();
	    if (isFile == false) {
	    	f.createNewFile();
	    	System.out.println("New database (.txt file)!");
	    }
	    else {
	    	System.out.println("Opening existing database (.txt file)!");
	    }
	    
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
	    BufferedReader br = new BufferedReader(new  FileReader(f));
         
	    // Reading from file
	    String line = br.readLine();

	    boolean existingUser = false;
	    boolean loginSuccess = false;
	    
        while (line != null) {        	
        	if (line.equals(name)) {
        		existingUser = true;
        		System.out.println("Existing username: " + line); 
        		line = br.readLine();
        		
        		if (line.equals(password)) {
        			System.out.println("Right password: " + line); 
        			loginSuccess = true;
        		}
        		else {
        			System.out.println("Wrong password! Right password is: " + line); 
        		}
        		break;
        	}
            line = br.readLine();
        }
        
        // Writing to file
        if (!existingUser) {   
        	System.out.println("Not existing username. Creating new user!");
        	loginSuccess = true;
    	    bw.write(name + "\n");
    	    bw.write(password + "\n");
    	    bw.flush();	   
        }
	    
	    // Closing read write streams
	    bw.flush();
	    bw.close();
	    br.close();	
	    
	    return loginSuccess;
	}
	
	
	public void run() { // Création de thread qui envoi un message à un client
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream()); // cr/ation du canal de recevoir
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // création de canal d’envoi 
			out.writeUTF("Hello from server - you are client#" + clientNumber); // envoi de message
			String helloMessageFromClient = in.readUTF(); //lecture du message de clienthandler, bloque jusqua en lire un
			System.out.println(helloMessageFromClient);
			
			////// Verification username et password:
			String clientName = in.readUTF();
			System.out.println("Client name is: " + clientName);
			String clientPassword = in.readUTF();
			System.out.println("Client password is: " + clientPassword);
			
			out.writeUTF("Server : Please wait while we validate your credentials\n");;
			boolean loginSuccess = verifyUsernamePassword(clientName, clientPassword);
			if (loginSuccess) {
				out.writeUTF("You successfully logged in, welcome to the chat room!\n");
				// Code pour chat room? [...]
			}
			else {
				out.writeUTF("Wrong credentials...We failed to identify\n");
				// Terminate client
				return;
			}			
			
			//////
		} 
		catch (IOException e) {
			System.out.println("Error handling client# " + clientNumber + ": " + e);
		} 	
		
		finally {
			try {
				socket.close();
			} 
			catch (IOException e) {
				System.out.println("Couldn't close a socket, what's going on?");
			}
			System.out.println("Connection with client# " + clientNumber + " closed");
		}
	}
}

// Envoi de messages ici DANS la fonction run, val pass, donn/es etc