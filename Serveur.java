import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class Serveur {	 	
    public static void main(String[] args)
	{
		int port=8010;
		ServerSocket serv;
		Socket socket;
		try
			{
			 serv= new ServerSocket(port); //création d'une nouvelle socket connectée à notre port
			}
			catch(Exception e){
				 e.printStackTrace();
				 return;
			}
		System.out.println("Serveur demarre, en ecoute sur le port "+port);
		
		while(true){
			try{
				socket = serv.accept(); //se connecter au serveur
				
				System.out.println("Arrive d'un message");
				BufferedReader buf=new BufferedReader(new InputStreamReader(socket.getInputStream())); //nouveau buffer stockant les données reçues
				String ligne=buf.readLine(); //lecture des données
				String message=""; //message ligne par ligne
				while(ligne!=null) //tant que la ligne n'est pas null = trame pas terminée
				{
					message=message+ligne;
					ligne=buf.readLine();
				}
				// System.out.println(message);
				
				int indexDebut = message.indexOf("compteur"); //840
				//System.out.println(indexDebut);
				int indexFin = message.indexOf("temperature"); //869
				//System.out.println(indexFin);
				//fin = 883
				System.out.println("Compteur : " + message.charAt(850) + message.charAt(851) + message.charAt(852));
				System.out.println("Humidite : " + message.charAt(865) + message.charAt(866));
				System.out.println("Temperature : " + message.charAt(882) + message.charAt(883));
				
			}
			catch(Exception e){
				 e.printStackTrace();
			}
		}
	}
}
