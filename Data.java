import java.util.*;

class Data{
    
    public static Map<Integer, String> clientConnected;
    public static int idClient;
    int saveIdClient;
    public Data(){
	clientConnected = new HashMap<Integer, String>();
	idClient = 0;
	// idClient++;
    }

    public void recordClient(int idClient, String adAndPort){
	clientConnected.put(idClient, adAndPort);
    }
    
    public String display2(){
	String str = "";
    	for(Map.Entry<Integer, String> entry : clientConnected.entrySet()){
	    if(saveIdClient != entry.getKey())   /*Pour empêcher le client courant de se mettre dans la map  */ 	    
		str = str+ entry.getValue()+",";
	   
	}
	
	return str;
    }

    public String display(){
	String str = "";
	System.out.println("List of connected clients :");
    	for(Map.Entry<Integer, String> entry : clientConnected.entrySet()){
    	    str = str+"Client[" + entry.getKey() +  "] =>" + entry.getValue()+"\n";
	   
	}
	
	return str;
    }
 

}


