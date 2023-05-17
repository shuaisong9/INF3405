import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
//Application client
public class Client {
	private static Socket socket;
	public static void main(String[] args) throws Exception {
			
		Scanner scanner = new Scanner(System.in);

		System.out.print("Entrez une adresse IP selon le format IPv4 (ex: 127.0.0.1): ");
        String serverAddress = scanner.nextLine();

        System.out.print("Entrez un numéro de port entre 5000 et 5050: ");
        int port = scanner.nextInt();
      
        if (verifyIP(serverAddress)) {
        	if (verifyPort(port)) {//Création d'une nouvelle connexion aves le serveur
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
        	} else {
                System.out.println("Port invalide");
        	}  
        }
        else if (!verifyPort(port)) {
                System.out.println("Adresse IP et Port invalide!");
            
        } else {
        	System.out.println("Adresse IP invalide");
        } 
        
        System.out.print("Entrez votre nom d'utilisateur: ");
        String username = scanner.nextLine();

        System.out.print("Entrez votre mot de passe: ");
        int password = scanner.nextInt();
	}
	public static boolean verifyIP(String serverAddress) {
		String serverAddressPattern =  "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	    return serverAddress.matches(serverAddressPattern);
	}
	public static boolean verifyPort(int port) {
        return port >= 5000 && port <= 5050;
    }
}

//reste du code client
// appels s/quentiels, on doit envoyer A, puis B et lire A PUIS B