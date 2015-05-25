import java.io.*;
import java.net.*;

public class Serveur{
    public static final int PORTSERVER= 7777;

    public static void main(String [] args){
	Data data =new Data();
	ServerSocket serveur = null;
	try{
	    serveur = new ServerSocket(PORTSERVER);
	    System.out.println("Server listening...\n");
	}catch(Exception e){
	    e.printStackTrace();
	}
	while(true){
	    try{
		Socket socket = serveur.accept();
		ThreadServeur t = new ThreadServeur(socket,data);
		t.start();
	    }catch(Exception e){
		e.printStackTrace();
	    }
     

	}
	
    }


}