import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
//import java.io.BufferedInputStream;

//Application client
public class Client {
	private static Socket socket;
	public static void main(String[] args) throws Exception {
		
			//Adresse et port du serveur
		    System.out.println("Please enter the IP address of the server you wish to reach :"  ); // envoi de message paramètre IP
		    Scanner scan = new Scanner(System.in);  // Create a Scanner object
		    String serverAddress = scan.nextLine();  // Read user input
		    System.out.println("Please enter the port (5000-5050) :"  ); // envoi de message paramètre IP
		    int port = scan.nextInt();  // Read user input

			//Création d'une nouvelle connexion aves le serveur
			socket = new Socket(serverAddress, port);
			System.out.format("Serveur lancé sur [%s:%d] \n", serverAddress, port);
			
			//Création d'un canal entrant pour recevoir les messages envoyés, par le serveur
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			//Attente de la réception d'un message envoyé par le server sur le canal
			
			//envoyer un message au serveur
			Thread sendMessage = new Thread(new Runnable() {
	            @Override
	            public void run() {
			while (true)
			{   
					String message = scan.nextLine();  // lire message tapé par le client
					try { 
						out.writeUTF(message); //envoi du message au serveur
						
					} 
					catch (IOException e) {
						System.out.println("Error : " + e);
					} 
			}
	            } }); 
			
			// recevoir un message du serveur
			
			Thread readMessage = new Thread(new Runnable() {
				@Override
	            public void run() {
			
			while (true){   
					
					try { 
						String message = in.readUTF(); //message recu du serveur
						System.out.println(message); // affichage du message du serveur
						
					} catch (IOException e) {
						System.out.println("Error : " + e);
						
					}
			
			}}});
			
			//fermeture de la connexion avec le serveur
			//socket.close();
			

}};

//reste du code client
// appels s/quentiels, on doit envoyer A, puis B et lire A PUIS B
// si 2 machines, on roule serveur sur machine A et on lance serveur sur cette adresse IP là, on donne l'adresse à la personne qui a l'autre machine pour rouler client 