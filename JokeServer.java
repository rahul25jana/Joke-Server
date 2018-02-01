/* Rahul Janardhanan
	CSC_435
 Joke - Server
 */
 
// Header Files

	import java.io.*; // Package containing standard Input and Output Library
	import java.net.*; // Package containing standard Java_Networking Source, Library
    import java.util.ArrayList;  // This helps to access only one class / method from the package
	import java.util.*;  // Packages that helps to access different classes


 class SecondaryServer implements Runnable			// This class will implements or uses Runnable interfaces method
 {
 	public void run()						// run() with no return type
 	{
 		int q_len = 6; 							// Does not need to change
        int port = 4546;  						// primary port used in the connection
        Socket sock;							// type sock

    try{										//Start of try block --
            ServerSocket servsock = new ServerSocket(port, q_len);		//  adding new label to  actual class instance, port number & no.of req passed
System.out.println					// Printing
	 ("Updated Rahul'Joke serverA 1.8 going up, attention at port 4546.\n");				
	
            while (true) {				// Checking the loop if true
              	// The following will wait for admin_client conn
               sock = servsock.accept();		// accepting connection from type sock
               new ServerWorker(sock).start();		// new server_worker thread and pass that to handle
               
            }
        }
catch (IOException ioe) {System.out.println(ioe);}		// catching exception, if available
        
 	}
 } 



    class AdminWorker extends Thread				// adminworker using the properties of thread
{
            
     Socket sock; //  // type socket, class member, local
     AdminWorker (Socket s)			// adminworker passes the argument socket
     {
         sock = s;
     }	
    public void run()		// run function() , with no return type
    {
         PrintStream out = null;			// starts to prints from the first, setting out
     BufferedReader in = null;				//		and reading in null

     try				// try block start
     {
     	in=new BufferedReader(new InputStreamReader(sock.getInputStream()));     //invoking new i/p stream and reads from socket i/p stream 
     out= new PrintStream(sock.getOutputStream());								//invoking new o/p stream and reads from socket o/p stream 
     
     in.readLine();			// this will help to read each line when given input
     changemode();			// this function will actively change from one mode to other
     }catch(IOException x)		// catch section, protects the program
	 {
	 x.printStackTrace ();				// the following function will help to find exceptions in the program
	 }


    
   }
  public void changemode()				// another function of changing state

  {
     if(JokeServer.mode=="Joke") 				// giving the conditions of mode changes
     {
      JokeServer.mode="Proverb";  		// same here as above
     }

     else if(JokeServer.mode=="Proverb")			// else part with change states
     {
       JokeServer.mode="Joke";  
     }
  }

}


 class AdminLooper implements Runnable {					// one class using the interface of Runnable, class defintion
    public static boolean adminControlSwitch = true;		// assigning the bool value to true
		

    public void run() 		// run()
          {  

        int q_len = 6; 			//  default length
        int port = 5050; 		// Admin looper port
        Socket sock;			// default type sock
			
        try {			// start of another try block
            ServerSocket servsock = new ServerSocket(port, q_len);	//  adding new label to  actual class instance, port number & no.of req passed 
    System.out.println
	 ("Updated Rahul's AdminClient serverB 1.8 starting up, listening at port 5050.\n");			// normal print ... server name with port
	
            while (adminControlSwitch) {		// condition of admin switch
             
               sock = servsock.accept();		// these functions checks or waits for the new request..
               
                new AdminWorker(sock).start();			// invokes start of new admin and sock is passed as argument

            }

        } catch (IOException ioe) {			// same catch block, protects the program
            System.out.println(ioe);
        }
    }
}


    class ServerWorker extends Thread			// one class using the other properties, or class definition
	{
		 PrintStream out = null;			// starts to prints from the first, setting out
	 ObjectInputStream in = null;			// i/p to null, for new start
		public ArrayList<String> Proverbs=new ArrayList<String>();			//passing array-list of jokes and proverbs
		public ArrayList<String> Jokes=new ArrayList<String>();		// this includes the top
		// Class definition
	 Socket sock; // member of the class
	 ServerWorker (Socket s)		// constructor invoke with class member as parameter
	 {
		 sock = s;							
		Proverbs.add("PA : Video games are bad for you ? That's what they said about rock and roll");			/// The below are jokes and proverbs ,, they might be similar
		Proverbs.add("PB : Hope is the only thing stronger than fear");			/// used google as resource
		Proverbs.add("PC : Just Play have fun enjoy the game");
		Proverbs.add("PD : After the game, the king and the pawn go into the same box");			// I am poor in jokes and proverbs
		Jokes.add("JA : My jokes are boring");
		Jokes.add("JB : At night I can't sleep, in the morning I can't wake up");
		Jokes.add("JC : I love my six packs so much, I protect it with a layer of fat");			//  All internet help ////
		Jokes.add("JD : Knock ! Knock ! there is no joke");

	 } 
	 public void run()			// same function
	 {

     ArrayList<String> SentJokes=null; 		// the below are, setting out for the first time - > null will start from first
     ArrayList<String> SentProverbs=null;		// same here
	 ArrayList<String> Username=null; 
	     String msg=null;		// user and
       String User=null;		// message will be assigned to null, for no confusion

	 try {                                       //start of outer try-catch block.
	 in =new ObjectInputStream(sock.getInputStream());       // the i/p stream of the sock is connected to the o/p stream of the client.
	 out = new PrintStream(sock.getOutputStream());       // the o/p stream of the sock is connected to the i/p stream of the client.

	 try {
         Username=new ArrayList<String>((ArrayList<String>)in.readObject ());			// reads username in the array list
	     SentJokes=new ArrayList<String>((ArrayList<String>)in.readObject ());				// reads jokes in the array list
	 	 SentProverbs=new ArrayList<String>((ArrayList<String>)in.readObject ());    			// reads proverbs in the array list
	 	 User=Username.get(0);		// passes the first user or --> gets the user
	 
     if(JokeServer.mode.equals("Joke"))			// the jokeserver condition
	 {
	 	msg=Sendmessage("Joke",User,SentJokes);		// sends the appropriate message
	 }
	 else
	 {
	 	msg=Sendmessage("Proverb",User,SentProverbs);	 		// or the proverb section,, sends the right text, related to the type
	 }
	 System.out.println("comeback msg is : "+msg);			// the return message will come through
     String temp = null;		// assigning random temp variables to null, to use later
     String temp1 = null;
     
     temp = msg.substring(0,3);		// using temp,, subset of another main string
     temp1 = msg.substring(msg.indexOf(':'));   	// substring with index variable, assigned to one temporary variable
     msg=temp+User+temp1;		// the type message is calculated with the following expression

     out.println(msg);
	 } catch (IOException x)
	 {                           //End of inner try-catch block.
	 System.out.println("Server read error");            // shows the following message, if applicable
	 x.printStackTrace ();
	 }
	 catch(ClassNotFoundException x)
	 {
	 x.printStackTrace ();		//  default () --> helps to find any problems or exceptions
	 }
	 sock.close();                                      //client connection will only be closed, not the server.
	 } catch (IOException ioe) {System.out.println(ioe);}  // end block of try- catch

}


 public String Sendmessage (String mode,String name,ArrayList<String> Sentlist)			// this function-- > sends the message and get --> passes the array list as argument
	 {

        ArrayList<String> temp=null;		// arraylist -> are assigned to temp for now
        ArrayList<String> temp1=null;  
	 	
	 	if(mode.equals("Joke"))		// checks for the  joke mode 
           {
	 		temp=new ArrayList<String>(Jokes);
	        temp1=new ArrayList<String>(Jokes); 
	       }
	       else{
	       temp=new ArrayList<String>(Proverbs);
	        temp1=new ArrayList<String>(Proverbs); 
	       }

	 	if(Sentlist.size()>0)		//checks for the list that was sent and allocates
	 	{
	 		for(int i=0;i<Sentlist.size();i++)		// inner for loop, which checks for the list[]
	 		{
	 			String s=null;		//
	 			s=Sentlist.get(i);		// gets assigned to s
	 			s=s.replace(name,"");		//substitution will be used, name is passed
	 			System.out.println("Innn funcccccc: "+s);
	 			temp.remove(s);		// deleting or destroying 
	 		}
	 	}

	 
System.out.println(" The entire list is : "+Sentlist);			// prints  the list that was sent
System.out.println("the left out is : "+temp);		// prints the remaining part

	 	int tempSize=temp.size();		// calls the size function
	 	if(tempSize==0)		//this will check the temporary variable size
	 	{
	 		temp=temp1;		// if so, assings ti next temp variable
	 		tempSize=temp.size();		// calls the respective function
	 	}
	 
       Random randomno = new Random();		// new random function is invoked
       int rand=randomno.nextInt(tempSize);		//this random function will check next whole number of size
       String f=null; 		// f is created to store the value in later part
       f=temp.get(rand); //   // calls rand () of temp variable
       
       System.out.println("String is: "+f);
          return f;
      
	}
	
}

public class JokeServer {		// main joke server class, that runs the whole program

	public static String mode="Joke";			//

 public static void main(String a[]) throws IOException {




    int q_len = 6; /* Not intersting. Number of requests for OpSys to queue */
    int port1 = 4545;		// primary port as mentioned
    int port2=4546;             // secondary port that was assigned
    Socket sock;


            AdminLooper AL = new AdminLooper(); // invokes the admin thread
            Thread t = new Thread(AL);		//thread () will call new thread
            t.start();

 ServerSocket servsock = new ServerSocket(port1, q_len);  // socket that uses or limits to port and q length
 System.out.println
	 ("Updated Rahul's Joke serverA 1.8 starting up, listening at port 4545.\n");
	

   SecondaryServer server=new SecondaryServer();		// calling the new server, secondary one
  Thread t2 = new Thread(server);
   t2.start();  // invoke when admin input


	 while (true)
	 {
	 sock = servsock.accept(); // at fix port, looks for client
	 new ServerWorker(sock).start(); // client requests are maintained here
	 }                         // close of loop
                         //	close of while loop
 }
}



