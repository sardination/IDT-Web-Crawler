import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Page {
	
	private String path;
	//private ArrayList<Page> route = new ArrayList<Page>();
	private int responseCode;
	public static ArrayList<Input> inputs = new ArrayList<Input>();
	
	public Page (String pathName) {
		this.path = pathName;
		
	      HttpURLConnection con;
		try {
			con = (HttpURLConnection) new URL(path).openConnection();
	 	      con.setRequestMethod("HEAD");
	 	     responseCode = con.getResponseCode();
		} catch (MalformedURLException e) {
			responseCode = -1;
			//e.printStackTrace();
		} catch (IOException e) {
			responseCode = -1;
			//e.printStackTrace();
		}
	}
	
	public String getPathName() {
		return path;
	}
	
	//public ArrayList<Page> getRoute() {
	//	return route;
	//}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public ArrayList<Input> getInputs() {
		return inputs;
	}
	
	//public void setRoute(ArrayList<Page> route) {
	//	this.route = route;
	//}
	
	public void parseInputs() {
		Document doc = null;
		
		boolean go = true;
		
		try {
			doc = Jsoup.connect(path).get(); //gets all the html information from the page
			System.out.println(doc);
		} catch (IOException e) {
			//print a notice saying that the link is unreachable 
			//e.printStackTrace();
			go = false;
		}
		
		if (go) {
			Elements inputTags = doc.select("input");
			for (Element e:inputTags) {
				inputs.add(new Input(e.attr("type"), e.attr("value"), e.attr("id")));
			}
		}
		
		//for (Input i:inputs) System.out.println(i);
	}
	
	public String toString() {
		return (responseCode+" "+path);
	}
	

}
