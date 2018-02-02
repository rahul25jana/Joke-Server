/* Rahul Janardhanan
 Joke - Client
 */

import java.io.*; //Package containing standard Input and Output Library
import java.net.*; // Imports the Java networking libraries, source --> package
import java.util.ArrayList; // --> package assigend to sepcific class / method




public class JokeClient {		// main client class that runs the whole program


public static void getJokeorProverb (int port,String serverName,String JokeServer,String name,ArrayList<String> Proverbs,ArrayList<String> Jokes)	//have something of joke or prov, the following are passed as paramter
{      

   ArrayList<String> Username=new ArrayList<String>();			// username is assigned to new array of list
     
	 Socket sock;			// as mentioned type socket
	 BufferedReader fromServer;		// this function helps to read lines in particluar
	 ObjectOutputStream toServer;		// writes early stage character to server
	 String textFromServer=null;    	//will store messages                                       

	 try{                                                      //start of try-catch block
	 sock = new Socket(serverName, port);             // socket is created --> new one --> port is passed

	 fromServer =
	 new BufferedReader(new InputStreamReader(sock.getInputStream()));     // the i/p stream of the sock is connected -> o/p stream -> socket(server) at the other end.
	 toServer = new ObjectOutputStream(sock.getOutputStream());                   // the o/p stream of the sock -> connected to the i/p stream -> socket(server) at the other end.

	                                                                  
	Username.add(name);			// this function will add the username everytime abd sends jokes or proverbs
	    	if(Jokes.size()==4)		// the following or conditions
	     	{
	     		Jokes.clear();
	    	}
             
            if(Proverbs.size()==4)
	     	{
	     		Jokes.clear();
	    	}
	
    toServer.writeObject(Username);		// this helps to writes the desired part
	 toServer.writeObject(Jokes);		// desired jokes
	 toServer.writeObject(Proverbs);		// writes the desired part
	 
	 


    textFromServer=fromServer.readLine ();			// reads each line --> from sever and gets store
    System.out.println("given String is: "+textFromServer);		// the received / given string
	                             
    if(textFromServer.contains("JA") || textFromServer.contains("JB") || textFromServer.contains("JC") || textFromServer.contains("JD") )		// the following are the send message req
    {
    Jokes.add(textFromServer);			// jokes will added when invoked
    }
    else if(textFromServer.contains("PA") || textFromServer.contains("PB") || textFromServer.contains("PC") || textFromServer.contains("PD") )
    {
     Proverbs.add(textFromServer);			// proverbs will be invoked
    }   
    
    else {	 System.out.println ("Sample / try message");
         } 
	 sock.close();                                                        // only client connection will be stopped// not the server
	 } catch (IOException x) {
	 System.out.println ("Socket error.");
	
	 x.printStackTrace ();		// catches the problems or excerptions, protects the program
	 }

	}




	public static void main (String args[])		// main function, every function will be called here
	{
		ArrayList<String> ProverbsA=new ArrayList<String>();		// the following are array list of proverbs and jokes
        ArrayList<String> JokesA=new ArrayList<String>();
        ArrayList<String> ProverbsB=new ArrayList<String>();		// two types are presented A and B
        ArrayList<String> JokesB=new ArrayList<String>();
    
     String Username=null;		// setting out,, null will be initialized first
	 String serverName;
	 
	 if (args.length < 1) serverName = "127.0.0.1";     // the desired IP address is allocated as mentioned
	 else serverName = args[0];


	 System.out.println(" JokeClient started with bindings:");			// client_ started --> invokes this message
     System.out.println("Server A at 4545");			// main port // primary
     System.out.println("Server B at 4546");		// secondary // 2
	 
	 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  //assigning the variable --> reads the input and gets created a new one
	 System.out.print
	 ("Type User Name: ");			// enter a name//
	 try			// block == try - catch
	 {
	 Username=in.readLine();		// reads user name
	 }	
	 catch(IOException x){		// checks for exceptions
	 System.out.println ("Input error.");			// error message will be provided
	 
	 x.printStackTrace ();		// same old function, that protects the program, if exceptions found
	 }
	 try {                            //Start of try-catch Block
	 String input;
	 do {
    
	 System.out.print
	 ("Enter A to get a joke to proverb or quit to end: ");		// prints line // but when A entered, is not sure that you will get both together
     		// should change the state from needed

	 System.out.flush ();                            //destroys the buffered content in the o/p stream
	 input = in.readLine ();                     //    lines that are read are stored
    
     
	 if (input.indexOf("quit") < 0)
	 {                  // condition of index with name in it, if so, returns -1
	
     if(input.equals("A"))			// user input actions	--> A
     {
     	getJokeorProverb (4545,serverName,"A",Username,ProverbsA,JokesA);		// -> these arguments get passed
     }
     else if(input.equals("B"))			// --> b action --> user input
     {
	
    getJokeorProverb (4546,serverName,"B",Username,ProverbsB,JokesB);			// prints the following line --> based on input
    }

     
     
	 }
	 } while (input.indexOf("quit") < 0);   // looks for name --> checks index value of name
	 System.out.println ("dropped by user request.");			// normal print statemnet based on the condition
	 } catch (IOException x) {x.printStackTrace ();}       // try- catch end block, helps to run the program without crashes.
	 }

 
}
