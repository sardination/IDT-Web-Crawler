import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			//System.out.println(doc);
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
	
		/**
	 * tests Website by comparing the output on correct input and the output on test input
	 * @param correct an array of correct inputs (order must be identical)
	 * @param test an array of test inputs
	 * @return <CODE>true</CODE> if the pages are identical, <CODE>false</CODE> if otherwise
	 */
	public boolean testInput(String[] correct, String[] test) {
		HashMap<String,String> cormap = new HashMap<String,String>();
		boolean[] pass = new boolean[inputs.size()];
		for (int x=0; x<inputs.size(); x++) {
			cormap.put(inputs.get(x).getID(), correct[x]);
		}
		try {
			Document cordoc = Jsoup.connect(path).data(cormap).userAgent("Mozilla").post();
			HashMap<String,String> testmap = new HashMap<String,String>();
			for (int x=0; x<inputs.size(); x++) {
				testmap.put(inputs.get(x).getID(), test[x]);
			}
			Document testdoc = Jsoup.connect(path).data(testmap).userAgent("Mozilla").post();
			return cordoc.equals(testdoc);
		}
		catch (IOException e) {
			return false;
		}
	}

	/**
	 * tests Website by comparing the output on correct input and the output on test input
	 * @param correct an array of correct inputs 
	 * @param test an array of test inputs
	 * @return <CODE>true</CODE> if the pages are identical, <CODE>false</CODE> if otherwise
	 */
	public boolean testInput(Map<String,String> correct, Map<String,String> test) {
		try {
			Document cordoc = Jsoup.connect(path).data(correct).userAgent("Mozilla").post();
			Document testdoc = Jsoup.connect(path).data(test).userAgent("Mozilla").post();
			return cordoc.equals(testdoc);
		}
		catch (IOException e) {
			return false;
		}
	}
	
	public String toString() {
		return (responseCode+" "+path);
	}
	

}
