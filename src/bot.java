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
public class bot extends PircBot
{
	final Pattern regex = Pattern.compile("(\\d{5})");
	String temp;
	String defaultLocation = "75080";
//constructor 
	public bot()
	{
       		this.setName("akarshbot2000"); //this is the name the bot will use to join the IRC server
   	}
	//every time a message is sent, this method will be called and this information will be passed on
	//this is how you read a message from the channel 
	public void onMessage(String channel, String sender, String login, String hostname, String message)
    	{
		// Use this function to read the message that comes in 
		//For example, you can have an if statement that says:
		message = message.toLowerCase();
		if (message.contains("weather"))
		{
			//the user wants weather, so call the weather API you created in part 1
			String findlocation = defaultLocation;
			String[] words = message.split(" ");
			if(words.length==2) {
				if(words[0].equals("weather"))
				{
					findlocation=words[1];
				}
				else 
				{
					findlocation=words[0];
				}
			}
			else {
				Matcher match = regex.matcher(message);
				if(match.find())
				{
					findlocation = match.group(1);
				}
				else 
				{
					sendMessage(channel, "Cant determine location");
				}
			}
			//the user wants weather, so call the weather API you created in part 1
			//this is how you send a message back to the channel
			temp = StartWebRequest(findlocation);
			sendMessage(channel, "Hey " + sender + "! " + temp);
		}
		
		} 
	 static String StartWebRequest(String zipcode) 
	 {
		//url plus the key for api
		String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + "&appid=ea938b50bc85c1c840db0827b91adac9";
		StringBuilder Result = new StringBuilder();
		try
		{
		URL url = new URL(weatherUrl);//creates url
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String Line;
		while((Line=rd.readLine()) != null) 
		{
			Result.append(Line);
		}
		return parseJson(Result.toString());	
	}catch (Exception e) {
		return "There is an Error! Exception: " + e;//prints error
	}
	
}//end class
	 //parse function
	 static String parseJson(String json) {
		 JsonObject Object = new JsonParser().parse(json).getAsJsonObject();
		 String CityName = Object.get("name").getAsString();//parse city
		 JsonObject main = Object.getAsJsonObject("main");
		 double temperature = main.get("temp").getAsDouble();//parse temp
		 temperature = (temperature-273.15) * 1.8 + 32;
		 double temperatureMin = main.get("temp").getAsDouble();//parse min temp
		 temperatureMin = (temperatureMin-273.15) * 1.8 + 32;
		 double temperatureMax = main.get("temp").getAsDouble();//parse max temp
		 temperatureMax = (temperatureMax-273.15) * 1.8 + 32;
		 DecimalFormat format = new DecimalFormat("0.00");
		 return "The temperature in " + CityName + "is" + format.format(temperature) + "With a high of " + format.format(temperatureMax) + 
				 "With a low of " + format.format(temperatureMin);
	 }
}
