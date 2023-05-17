import java.io.DataInputStream;
import java.io.DataOutputStream;
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
			String messageC = "";
			while (!messageC.equals("bye"))
			{   
					messageC = scan.nextLine();  // lire message tapé par le client
					out.writeUTF(messageC); //envoi du message au serveur
		
					String messageS = in.readUTF(); //message recu du serveur
					System.out.println(messageS); // affichage du message du serveur
			}
			
			//fermeture de la connexion avec le serveur
			socket.close();
			
	}
}

//reste du code client
// appels s/quentiels, on doit envoyer A, puis B et lire A PUIS B
// si 2 machines, on roule serveur sur machine A et on lance serveur sur cette adresse IP là, on donne l'adresse à la personne qui a l'autre machine pour rouler client 