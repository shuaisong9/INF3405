package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//Application client
public class Client {
	private static Socket socket;
	
	public static void main(String[] args) throws Exception {
		
		//Adresse et port du serveur
		String serverAddress = "127.0.0.1";
		int port = 5000;
		
		//Création d'une nouvelle connexion avec le serveur
		socket = new Socket(serverAddress, port);
		System.out.format("Serveur lancé sur [%s:%d]", serverAddress, port);
		
		//Création d'un canal entrant pour recevoir les messages envoyés, par le serveur
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		//Attente de la réception d'un message envoyé par le server sur le canal
		String helloMessageFromServer = in.readUTF(); //lecture du message de clienthandler, bloque jusqua en lire un
		System.out.println(helloMessageFromServer);
		out.writeUTF("Ceci est un message du client");
		
		
	    ////// Verification username et password:
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = sc.nextLine();		
        System.out.println("Enter a password: ");
        String password = sc.nextLine();
        
        System.out.println("Requesting communication with the server...\n");
        out.writeUTF(name);
        out.writeUTF(password);
        String waitMessageFromServer = in.readUTF();
        System.out.println(waitMessageFromServer);
        
        String loginMessageFromServer = in.readUTF();    
        System.out.println(loginMessageFromServer);
        //////
        
		
		//fermeture de la connexion avec le serveur
        System.out.println("Terminating client..."); // Right place for this?? Have to receive it from somewhere??
		socket.close();
		sc.close();
		System.out.println("Client terminated");
	}
}

//reste du code client
// appels s/quentiels, on doit envoyer A, puis B et lire A PUIS B
