import java.io.DataInputStream;
import java.util.Scanner;  // Import the Scanner class
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread { // pour traiter la demande de chaque client sur un socket particulier
	private Socket socket; 
	private int clientNumber; 
	private String name;
	boolean isloggedin;
	
	public ClientHandler(Socket socket, int clientNumber) {
		this.socket = socket;
		this.clientNumber = clientNumber; System.out.println("New connection with client #" + clientNumber + " at " + socket);
		this.isloggedin = true;
		this.name = "";
}
public void run() { // Création de thread qui envoi un message à un client
	try {
DataInputStream in = new DataInputStream(socket.getInputStream()); // création du canal de recevoir
DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // création de canal d’envoi 

out.writeUTF("Hello from server - you are client#" + clientNumber); // envoi de message d'accueil
Scanner scan = new Scanner(System.in);  // Create a Scanner object

String received;
//while (true)
//{  
//	try {
//		received = in.readUTF(); //reception du message des clients
//		//System.out.println(received); // affichage du message du client ! pas nécessaire, utile pour historique
//		//messageS = scan.nextLine();  // Read user input in server ! pas besoin
//		
//		if(received.equals("logout")){
//            this.isloggedin=false;
//            this.socket.close();
//            break;
//		}
//		
//		out.writeUTF(this.name+ " : " +received); //envoi du message aux clients
//		break;
//		         
//        // break the string into message and recipient part
//       // StringTokenizer st = new StringTokenizer(received, "#");
//       // String MsgToSend = st.nextToken();
//      //  String recipient = st.nextToken();
//
//        // search for the recipient in the connected devices list.
//        // ar is the vector storing client of active users
//       // for (ClientHandler mc : Server.ar)
//       // {
//            // if the recipient is found, write on its
//            // output stream
//         //   if (mc.name.equals(recipient) && mc.isloggedin==true)
//          //  {
//          //      mc.dos.writeUTF(this.name+" : "+MsgToSend);
//          //      break;
//          //  }

} catch (IOException e) {
	System.out.println("Error handling client # " + clientNumber + ": " + e);
} finally {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Couldn't close a socket, what's going on?");
				}
					System.out.println("Connection with client# " + clientNumber + " closed");
		}
	}
}}}

// Envoi de messages ici DANS la fonction run, val pass, donn/es etc
