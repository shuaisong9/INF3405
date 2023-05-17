
import java.net.InetAddress; //
import java.net.InetSocketAddress; //
import java.net.ServerSocket; //
import java.util.Scanner;  // Import the Scanner class

//Application server
public class Server {
	
	private static ServerSocket Listener; 
		 // Application Serveur
		 public static void main(String[] args) throws Exception { 
		 // Compteur incrémenté à chaque connexion d'un client au serveur
		 int clientNumber = 0;
		 
		// Adresse et port du serveur
		 System.out.println("Please enter the IP address of this server :"  ); // envoi de message paramètre IP
		 Scanner scan = new Scanner(System.in);  // Create a Scanner object
		 String serverAddress = scan.nextLine();  // Read user input
		 System.out.println("Please enter the port (5000-5050) :"  ); // envoi de message paramètre IP
		 int serverPort = scan.nextInt();  // Read user input
		 
		 // Création de la connexion pour communiquer avec les clients
		 Listener = new ServerSocket();
		 Listener.setReuseAddress(true);
		 InetAddress serverIP = InetAddress.getByName(serverAddress);
		 
		 // Association de l'adresse et du port à la connexion
		 Listener.bind(new InetSocketAddress(serverIP, serverPort));
		 System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);
		 try {
		 // À chaque fois qu'un nouveau client se connecte, on exécute la fonction
		 // run() de l'objet ClientHandler
		 while (true) {
		 // Important : la fonction accept() est bloquante: attend qu'un prochain client se connecte
		 // Une nouvelle connection : on incrémente le compteur clientNumber 
			 new ClientHandler(Listener.accept(), clientNumber++).start();
		 }
		 } 
		 
		 
		 finally {
		 // Fermeture de la connexion
		 Listener.close();
		 } }}





