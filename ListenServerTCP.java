import java.io.*;
import java.net.*;
import java.util.*;

class ListenServerTCP extends Thread{
    Socket s;
    DatagramSocket ds;
    DatagramPacket sendPacket;
    Data data = new Data();
    String portAndAddress;
    String zik;
    String [] splitAddressAndPort;
    BufferedReader bf;
    String [] splitClient;

    byte [] singSend = new byte[1024];
    public static ArrayList <String>music = new ArrayList<String>();
    public static ArrayList <String>listPortAddress = new ArrayList<String>();


    public ListenServerTCP(Socket s,DatagramSocket ds,String chanson){
	music.add(chanson);
	this.s = s;
	this.ds = ds;

	try{
	    bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    public void sendMsg(){
	String address;
	int port;
	Iterator it = listPortAddress.iterator();
	while(it.hasNext()){
	    
	    port = Integer.parseInt((String)it.next());

	    it.hasNext();
	    address= (String)it.next();
	    zik =music.get(0);
	    singSend = zik.getBytes();
	    try{
		sendPacket = new DatagramPacket(singSend,singSend.length,new InetSocketAddress(address,port));
		ds.send(sendPacket);
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}	
   
    }
    public void displayClient(){
	Iterator it = listPortAddress.iterator();
	while(it.hasNext()){
	    System.out.print(it.next());
	    it.hasNext();
	    System.out.println(" / "+it.next());
	    it.hasNext();
	    
	}
	
    }

    public void run(){
	try{
	    while((portAndAddress = bf.readLine()) != null){
		
		splitClient = portAndAddress.split(",");
		
		for(int i = 0; i < splitClient.length; i++){
		    splitAddressAndPort = splitClient[i].split("-"); 
		    
		    listPortAddress.add(splitAddressAndPort[0]);//port
		    
		    listPortAddress.add(splitAddressAndPort[1]);//adresse
		    
		    //displayClient();
		}
		
		sendMsg();
	    }
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
	
    }
    



}