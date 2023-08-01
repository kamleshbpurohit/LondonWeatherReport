import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestScript 
{
	public static JSONObject obj;
	public static void main(String[] args) throws IOException, ParseException 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Select any one of the parameters mentioned below:");
		System.out.println("1.Get Weather"+"\n"+"2.Get Wind Speed"+"\n"+"3.Get Pressure"+"\n"+"0.Exit");
		int option=sc.nextInt();
		
		JSONParser jsonParser=new JSONParser();
		FileReader reader = new FileReader("./src/test/resources/report.json");
		Object object=jsonParser.parse(reader);
		obj=(JSONObject)object;
		String dummy,date;
		switch (option)
		{
		case 1:System.out.println("Enter the date in YYYY-MM-DD HH:MM:SS format");
			 dummy=sc.nextLine();
			 date = sc.nextLine();
			Double temp=getTemp("temp",date);
			System.out.println("The temperature on "+date+" is : "+temp);
			break;
			
		case 2:System.out.println("Enter the date in YYYY-MM-DD HH:MM:SS format");
		 dummy=sc.nextLine();
		 date = sc.nextLine();
		Double wind=getWind("wind",date);
		System.out.println("The Speed of the wind on "+date+" is : "+wind);
		break;
		
		case 3:System.out.println("Enter the date in YYYY-MM-DD HH:MM:SS format");
		 dummy=sc.nextLine();
		 date = sc.nextLine();
		Double pressure=getPressure("pressure",date);
		System.out.println("The Pressure on "+date+" is : "+pressure);
		break;
		
		case 0:System.out.println("Exiting");
		break;
		default:System.out.println("Enter valid input");
		}
		
		
	}

	/**
	 * This method is used to get the pressure on the date provided
	 * @param parameter
	 * @param date
	 * @return
	 */
	private static Double getPressure(String parameter, String date)
	{
		JSONArray arr=(JSONArray)obj.get("list");
		Double pressure=0.0;
		int n=0;
		for(int i=0;i<arr.size();i++)
		{
			JSONObject x=(JSONObject)arr.get(i);
			String obtDate=(String) x.get("dt_txt");
			if(date.equals(obtDate))
			{
				JSONObject y=(JSONObject) x.get("main");
				pressure=(Double)y.get(parameter);
				n++;
			}
			
		}
		if(n==0)
		{
			System.out.println("Invalid date");
		}
		return pressure;
	}

	/**
	 * This method is used to get the speed of the wind 
	 * @param parameter
	 * @param date
	 * @return
	 */
	private static Double getWind(String parameter, String date)
	{
		JSONArray arr=(JSONArray)obj.get("list");
		Double wind=0.0;
		int n=0;
		for(int i=0;i<arr.size();i++)
		{
			JSONObject x=(JSONObject)arr.get(i);
			String obtDate=(String) x.get("dt_txt");
			if(date.equals(obtDate))
			{
				JSONObject y=(JSONObject) x.get(parameter);
				wind=(Double)y.get("speed");
				n++;
			}
			
		}
		if(n==0)
		{
			System.out.println("Invalid date");
		}
		return wind;
	}

	/**
	 * This method is used to get the temperature on the date given by user
	 * @param parameter
	 * @param date
	 * @return
	 */
	private static Double getTemp(String parameter,String date) 
	{
		JSONArray arr=(JSONArray)obj.get("list");
		Double temp=0.0;
		int n=0;
		for(int i=0;i<arr.size();i++)
		{
			JSONObject x=(JSONObject)arr.get(i);
			String obtDate=(String) x.get("dt_txt");
			if(date.equals(obtDate))
			{
				JSONObject y=(JSONObject) x.get("main");
				temp=(Double)y.get(parameter);
				n++;
			}
			
		}
		if(n==0)
		{
			System.out.println("Invalid date");
		}
		return temp;
		
	}

}
