/*MOURLANETTE Alexis & BAHU Florian - I4GRIT - Réseaux IOT / Serveur Java*/

/*Bibliothèques nécessaires*/
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Serveur {	 	
    public static void main(String[] args){
		int port = 8010; //port personnel (choisi dans notre association)
		ServerSocket serv; //déclaration d'une serversocket
		Socket socket; //déclaration d'une socket
		
		try{
			serv= new ServerSocket(port); //création d'une nouvelle socket connectée à notre port
		}
		catch(Exception e){ //affichage des erreurs
			e.printStackTrace();
			return;
		}
		System.out.println("Serveur demarre, en ecoute sur le port "+port);
		
		while(true){ //boucle infinie
			try{
				socket = serv.accept(); //se connecter au serveur
				
				System.out.println("Arrivee d'un message");
				BufferedReader buf=new BufferedReader(new InputStreamReader(socket.getInputStream())); //nouveau buffer stockant les données reçues
				String ligne = buf.readLine(); //lecture des données
				String message = ""; //message ligne par ligne
				while(ligne != null){ //tant que la ligne n'est pas null = trame pas terminée
					message=message+ligne;
					ligne=buf.readLine();
				}
				
				//1 caractère = 1 unité
				int indexDebut = message.indexOf("compteur"); //'c' = 840e caractère
				//System.out.println(indexDebut);
				int indexFin = message.indexOf("temperature"); //'t' = 869e caractère
				//System.out.println(indexFin);
				//fin = 883e caractère
				
				//Affichage des données reçues
				System.out.println("Compteur : " + message.charAt(850) + message.charAt(851) + message.charAt(852));
				System.out.println("Humidite : " + message.charAt(865) + message.charAt(866));
				System.out.println("Temperature : " + message.charAt(882) + message.charAt(883));	
			}
			catch(Exception e){ //affichage des erreurs
				 e.printStackTrace();
			}
		}
	}
}
