/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Cattaneo_alex
 */
public class SocketServer {

    /**
     * @param args the command line arguments
     */
    ServerSocket server	= null;
    Socket client = null; 
    String stringaRicevuta = null; 
    String stringaModificata = null; 
    BufferedReader inDalClient; 
    DataOutputStream outVersoClient; 
public SocketServer(){
    
}
public Socket attendi()
{
    try	// leggo una riga
    {
        System.out.println("Server partito in esecuzione"); 
        // creo un server sulla porta 6789
        server = new ServerSocket(6789);
        // rimane in attesa di un client	
        client = server.accept();
        // chiudo il server per inibire altri clienti
        server.close();
        // associo due oggetti al socket del client per effettuare la scrittura e la lettura
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Errore durante l'istanza del server !");
        System.exit(1);
    }
    return client;
}
    public void comunica()
    {
        try	
        {
            
            // rimango in attesa della riga trasmessa dal client
            System.out.println("benvenuto client, ora ti saranno inviati i dati. Attendo ...."); 
            
            stringaRicevuta = inDalClient.readLine();
            System.out.println("ricevuti i dati");

            // la modifico e la rimando al client
            System.out.println("Do la conferma al client ...."); 
            outVersoClient.writeBytes("Dati ricevuti");

            //termina elaborazione sul server : chiudo la connessione del client
            //System.out.println("SERVER: fine elaborazione ...." );
            //client.close();
        }
        catch (Exception e)	// 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il client");
            System.exit(1);
        }
    }
    
    public String getDati(){
        return stringaRicevuta;
    }
   
    
}
