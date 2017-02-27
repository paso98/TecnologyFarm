package temperatura;

import java.io.*;
import java.net.*;


public class ClientStr {
	String nomeServer;//ip notebook che fa da server
	int portaServer ;
	Socket miosocket;
	BufferedReader tastiera;
	String stringaUtente;
	String stringaRicevutaDalServer;
	DataOutputStream outVersoServer;
	BufferedReader inDalServer;

        public ClientStr(){
             nomeServer = "192.168.0.9";//ip notebook che fa da server
             portaServer = 6789;
        }
	public void comunica(String s) {
		
		try // leggo una riga
		{
                    // la spedisco al server
                    outVersoServer.writeBytes(s + '\n');
                    // leggo la risposta del server
                    stringaRicevutaDalServer = inDalServer.readLine();
                    System.out.println("Server: " + stringaRicevutaDalServer);
                    // chiudo la connessione
				
                    miosocket.close();

		} catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Errore durante la comunicazione con il server");
                    System.exit(1);
		}
	}

	public Socket connetti() {
		System.out.println("In connessione");
		try {
			// per l'input da tastiera
			tastiera = new BufferedReader(new InputStreamReader(System.in));
			// creo un socket
			miosocket = new Socket(nomeServer, portaServer);
			System.out.println("-Connessione stailita \n-Benvenuto!");
			// miosocket = new Socket(InetAddress.GetLocalHost(),6789);
			// associo due oggetti al socket per effettuare la scrittura e la
			// lettura
			outVersoServer = new DataOutputStream(miosocket.getOutputStream());
			inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Host  sconosciuto");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la connessione!");
			System.exit(1);
		}
		return miosocket;
	}
}