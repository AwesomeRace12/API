import org.jibble.pircbot.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.net.*;

//This is the class that “sets up” your chatbot, 
public class ChatBot

{
   public static void main(String[] args) throws Exception
   {
	   Scanner input = new Scanner(System.in);
	   int x=0;
	   System.out.println("Enter 1 0r 2: Weather or Kanye ");
	   x = input.nextInt();
	   while(x !=1 || x !=2 ) {
		   if(x <=0 || x>=3) {
			   System.out.println("Input again");
			   x = input.nextInt();
		   }
		   if(x==1) {
       bot ChatBot = new bot();
       ChatBot.setVerbose(true);
       System.out.println("Please enter the zip code for the city that you would like to get the weather for");
       ChatBot.connect("irc.freenode.net"); //tells it where to connect to - this is the same as the web interface I linked in the last slide
       ChatBot.joinChannel("#akarshbot2000"); // Name of channel is you want to connect to - in this case it’s called “#testChannel” 
//this is the default message it will send when your pircbot first goes live 
       ChatBot.sendMessage("#akarshbot2000", "it runs fine") ;
	//That’s it for setting up you bot! After this, you can implemented custom logic that will look similar to the next slide
		   }
	if(x==2) {
		bot2 kanye= new bot2();
       kanye.setVerbose(true);
       kanye.connect("irc.freenode.net"); //tells it where to connect to - this is the same as the web interface I linked in the last slide
       kanye.joinChannel("#akarshbot200"); // Name of channel is you want to connect to - in this case it’s called “#testChannel” 
//this is the default message it will send when your pircbot first goes live 
       kanye.sendMessage("#akarshbot200", "it run") ;
	//That’s it for setting up you bot! After this, you can implemented custom logic that will look similar to the next slide
	}
   }
   } 
}

