import java.io.*;
import java.net.*;
import java.util.*;
class ReceiveUDP extends Thread{
    DatagramSocket ds;
    DatagramPacket dp;
    ArrayList <String>musicReceive = new ArrayList<String>();
    Iterator it;
    byte [] donnee =new byte[1024];
    String sing, reponse;
    int n = 0,nbMusic=0;
    int fin = 5;
    Scanner sc = new Scanner(System.in);
    public ReceiveUDP(DatagramSocket ds){
	this.ds = ds;
    }
    

   
    
    public void run(){
	dp = new DatagramPacket(donnee,donnee.length);
	try{

	    while(n < fin){
		
		ds.receive(dp);
		sing = new String(dp.getData(),0,dp.getLength());
		if (!musicReceive.contains(sing)){
		    musicReceive.add(sing);
		    nbMusic++;
		    System.out.println("received song :"+sing);
		    n++;    
		    if(n  == fin){
			System.out.println("You have received five songs");
			System.out.println("You want to continue? (y/n)");
			String reponse = sc.nextLine();
			if(reponse.equals("n"))
			    ds.close();
			else
			    n=0;
		    }
		    
		    
		} else {
		    System.out.println("You have already this song");
		    Thread.sleep(2000);
		    System.out.println("Not added song");
		}	
		
	    }
	    System.out.println("I received" +nbMusic+" songs !!!  goodbye !!!!");
	    
	    
	       
	
	}catch(Exception e){
	    e.printStackTrace();
	}






    }
}