import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class RecursiveParser {
	public static Page currentLocation;
	
	public RecursiveParser (Page currentPage) {
		this.currentLocation = currentPage;
	}

	public static void main(String[] args) {
		
		currentLocation = new Page("http://localhost:8080/version1"); //the page the parser is currently on
		currentLocation.setRoute(new ArrayList<Page>()); //sets original route to nothing (root page)
		
		parse();

	}
	
	public static void parse() {
		Document doc = null;
		
		try {
			doc = Jsoup.connect(currentLocation.getPathName()).get(); //gets all the html information from the page
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Elements hrefs = doc.select("a[href]"); //gets all a tags with href
		for (Element e:hrefs) {
			String appendToPath = e.attr("href");  //gets the href value from each a tag
			ArrayList<Page> newRPRoute = currentLocation.getRoute();
			newRPRoute.add(currentLocation);
			Page newRPPage = new Page(currentLocation.getPathName()+"/"+appendToPath); //incorrect way to go to path
			newRPPage.setRoute(newRPRoute);
			RecursiveParser rp = new RecursiveParser(newRPPage); //creates a new RecursiveParser for each href encountered
			rp.parse();
		}
	}

}
