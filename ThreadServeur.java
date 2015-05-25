import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadServeur extends Thread{
    
    String ENCODING = "US-ASCII";
    Data data;
    Socket s;    
    public ThreadServeur(Socket s, Data data){
	this.s = s;
	this.data = data;
	
    }
    public void run(){
	try{
	    BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
	    String request = bf.readLine ();
	    if (request.equals("MAGIC")){
		System.out.println("[A new client just connected]\n");
		String reply = new String("OK".getBytes(),ENCODING);
		pw.println(reply);
		pw.flush();
		String adresse = bf.readLine();
		String port = bf.readLine(); 
		String word = port+"-"+adresse;
		data.idClient++;
		data.saveIdClient = data.idClient;
		data.recordClient(data.idClient,word);
		System.out.println(data.display());
		pw.println(data.display2());
		pw.flush();
		bf.close();
	    }//end if
	    else System.out.println("Try again");
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
	
    }
    
    
}