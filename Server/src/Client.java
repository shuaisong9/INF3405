import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

//Application client
public class Client {
	private static Socket socket;
	public static void main(String[] args) throws Exception {
			//Adresse et port du serveur
			String serverAddress = "127.0.0.1";
			int port = 5000;
			//Création d'une nouvelle connexion aves le serveur
			socket = new Socket(serverAddress, port);
			System.out.format("Serveur lancé sur [%s:%d]", serverAddress, port);
			//Création d'un canal entrant pour recevoir les messages envoyés, par le serveur
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			//Attente de la réception d'un message envoyé par le server sur le canal
			String helloMessageFromServer = in.readUTF(); //lecture du message de clienthandler, bloque jusqua en lire un
			System.out.println(helloMessageFromServer);
			out.writeUTF("Ceci est un message du client");
			//fermeture de la connexion avec le serveur
			socket.close();
			
	}
}

//reste du code client
// appels s/quentiels, on doit envoyer A, puis B et lire A PUIS B