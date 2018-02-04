/* Rahul Janardhanan

Joke - AdminClient
 */

import java.io.*; //It gets the libraries for Input Output stream to be used by the socket
import java.net.*; // this gets the java network libraries which are used to run our servers
import java.util.*;  // This package helps us to access different classes

public class AdminClient{				// Class Definition of the admin_client side
 
 public static void main (String args[]) 
 
 {			// main function and passing type string as parameter
 String serverName;					// the name of the server is used for string
 Socket sock;				// same type socket
 if (args.length < 1) 			// setting if condition and assigning server name to
 {
 serverName = "127.0.0.1";      // assigning IP address to the server name if the list is empty []

 System.out.println("Rahul's AdminClient, 1.8.\n");		// printing the name of admin
 System.out.println("Utilizing the server: " + serverName + ", Port: 5050");		//again printing the server with name and designated port number
 }
 else 
 {
 serverName = args[0];
 }
 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  // Assigning in(variable) to the System.in(i/p stram) so that the User input will be read by using BufferedReader functions.  // invoking new i/p stream to system i/p and gets helps to read text from input
 try {                            //try- catch Block start
 String name;                        		// name is passed as string                                       
 do {
 System.out.print
 ("Press Enter to change the mode or quit to end: ");		/// default function to change mode or state from jokes to proverbs
 System.out.flush ();                            // this function helps us to flush the o/p stream and gets to written out
 name = in.readLine ();                         // helps to read the desired character or input given 
 if (name.indexOf("quit") < 0)		// checking the index or position of the name, if zero will index()
{
 sock = new Socket(serverName, 5050);                      //socket is created at the client with the IP address and port number as we passed those as parameters.

 PrintStream toServer = new PrintStream(sock.getOutputStream());                   // the o/p stream of the sock is connected to the i/p stream of the socket(server) at the other end.
                  //Checking the name using indexof() , -->  the name will be passed as a parameter to the function getremoteAdress.
 toServer.println(name);
}
 } while (name.indexOf("quit") < 0);   // checking the index or position of the name, if zero will index()
 System.out.println ("Cancelled by user request.");		// normal print statement based on the while condition
 } catch (IOException x) {x.printStackTrace ();}       // try - catch block gets over. The condition helps to catch if any exceptions found. helps the program to work properly/
}
}
