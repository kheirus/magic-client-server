import java.io.*;
    import java.net.*;
import java.util.*;
public class ClientTCP{
    String ENCODING = "US-ASCII";
    Socket service;
    PrintWriter pw;
    BufferedReader bf;
    DatagramSocket ds;
    String sing,address,request,entry;
    final int PORTSERVER= 7777;
    
    public static ArrayList music = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);
    
    public ClientTCP(String address){
	this.address = address;
    }
   
    public void go(){
	try{

	    /*Envoie de la premiere requete vers le serveur*/
	    System.out.println("Enter the request to connect to server");
	    entry = sc.nextLine();
	    
	    while (!entry.equals("MAGIC")){
		System.out.println("Please enter the correct request");
		entry = sc.nextLine();
	    }
	    if (entry.equals("MAGIC")){
		request = new String(entry.getBytes(),ENCODING);
 		service = new Socket(address,PORTSERVER);
		pw = new PrintWriter (new OutputStreamWriter (service.getOutputStream()));
		pw.println(request);
		pw.flush();
		InputStreamReader isr = new InputStreamReader (service.getInputStream());
		bf = new BufferedReader (isr);
		String reply = new String (bf.readLine().getBytes(),ENCODING);
		
		if (reply.equals("OK")){
		    


		    System.out.println("Please enter your song : ");
		    sing = sc.nextLine();
		    
		    pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
		    InetAddress myAddress = service.getInetAddress();
		    String address = myAddress.getHostAddress();
		    int myPort = service.getLocalPort();
		    
		    pw.println(address);
		    pw.flush();
		    pw.println(myPort);
		    pw.flush();
		    
		    ds = new DatagramSocket(myPort);
		    
		    ListenServerTCP l = new ListenServerTCP(service,ds,sing);
		    l.start();
		    ReceiveUDP r = new ReceiveUDP(ds);
		    r.start();
		}//if reply 
	    }//LIST
		
	    }catch(Exception e){
		e.printStackTrace();
	    }
	
	
    }

}