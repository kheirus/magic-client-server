class LauncherClient{
    public static void main(String [] args){
	ClientTCP c = new ClientTCP(args[0]);
	c.go();
    }

}