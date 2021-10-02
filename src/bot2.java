import org.jibble.pircbot.*;
import java.io.BufferedReader;
import com.google.gson.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
//headers
public class bot2 extends PircBot{
	//constructor 
		public bot2()
		{
	       		this.setName("akarshbot200"); //this is the name the bot will use to join the IRC server
	   	}
		  
		public void onMessage(String channel, String sender, String login, String hostname, String message)
		    {
			if(message.equals("quote")){
			    String request = makeRequest();
			    sendMessage(channel, request);
			}  
		   }
		private String makeRequest() {
			String kanyeUrl = "https://api.kanye.rest";
			StringBuilder Result = new StringBuilder();
			try
			{
			URL url = new URL(kanyeUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String Line;
			while((Line=rd.readLine()) != null) 
			{
				Result.append(Line);
			}
			rd.close();
			return(Result.toString());
			}catch (Exception e) {
				return "There is an Error! Exception: " + e;}
		}
		static String StartWebRequest() 
		 {
			String kanyeUrl = "https://api.kanye.rest";
			StringBuilder Result = new StringBuilder();
			try
			{
			URL url = new URL(kanyeUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String Line;
			while((Line=rd.readLine()) != null) 
			{
				Result.append(Line);
			}
			rd.close();
			return(Result.toString());
		}catch (Exception e) {
			return "There is an Error! Exception: " + e;}
		}

		public String parse(String stri) {
			JsonObject object = new JsonParser().parse(stri).getAsJsonObject();
			String quote = object.get("quote").getAsString();
			return (quote);
		}
}

