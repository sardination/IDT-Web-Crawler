import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//getResponseCode for error
//making a graph with nodes and paths :3

public class RecursiveParser {
	public ArrayList<ArrayList<Boolean>> graph = new ArrayList<ArrayList<Boolean>>(); 
		//graph.get(a).get(b) checks the path from nodes.get(a) to nodes.get(b)
	public ArrayList<ArrayList<String>> linkTexts = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<Object>> fullTags = new ArrayList<ArrayList<Object>>();
	public ArrayList<Page> nodes = new ArrayList<Page>();
	public ArrayList<String> nodeNames = new ArrayList<String>();
	public String path = "";

	public RecursiveParser(String path) {
		this.path = path;
	}
	
	public void parseNow() {

		parseATags(path); //default test value for now
	}
	
	public void parseATags(String url) {
		
		if (url.length()>=1) {
			HttpURLConnection con;
			try {
				if (!url.substring(url.length()-1,url.length()).equals("/")) {
					con = (HttpURLConnection) new URL(url+"/index.html").openConnection();
			 	      con.setRequestMethod("HEAD");
			 	      if (con.getResponseCode() != 404) {
			 	    	  url = url+"/index.html";
			 	      }
				} else {
					con = (HttpURLConnection) new URL(url+"index.html").openConnection();
			 	      con.setRequestMethod("HEAD");
			 	      if (con.getResponseCode() != 404) {
			 	    	  url = url+"index.html";
			 	      }
				}
			} catch (MalformedURLException e) {
				//System.out.println("m");
				//System.out.println("m");
				//e.printStackTrace();
			} catch (IOException e) {
				//System.out.println("e");
				//System.out.println("i");
				//e.printStackTrace();
			}
		}
		
		Page currentLocation = new Page(url); //the page the parser is currently on
		//somehow check if they're in the root and don't force them to input index.html
		//currentLocation.setRoute(new ArrayList<Page>()); //sets original route to nothing (root page)
				
		Document doc = null;
		
		boolean go = true;
		
		try {
			doc = Jsoup.connect(currentLocation.getPathName()).get(); //gets all the html information from the page
		} catch (IOException e) {
			//print a notice saying that the link is unreachable 
			//e.printStackTrace();
			go = false;
		} catch (IllegalArgumentException i) {
			go = false;
		}
		
		if (go) {
			if (nodes.size()==0) {
				nodes.add(currentLocation);
				nodeNames.add(currentLocation.getPathName());
				graph.add(new ArrayList<Boolean>());
				graph.get(0).add(false);
				linkTexts.add(new ArrayList<String>());
				linkTexts.get(0).add("");
				fullTags.add(new ArrayList<Object>());
				fullTags.get(0).add(new Object());
			}
			
			Elements hrefs = doc.select("a[href]"); //gets all a tags with href
			for (Element e:hrefs) {
				String appendToPath = e.attr("href");  //gets the href value from each a tag
				String newRPPath = currentLocation.getPathName();
				//boolean found = false;
				
				if (appendToPath.length()>=7 && appendToPath.substring(0,7).equals("http://")) {
					newRPPath = appendToPath;
				} else if (appendToPath.length()>=3 && appendToPath.substring(0,3).equals("../")){
					newRPPath = newRPPath.substring(0,newRPPath.lastIndexOf("/"));
					while (appendToPath.length()>=3 && appendToPath.substring(0,3).equals("../")) {
						appendToPath = appendToPath.substring(3,appendToPath.length());
						newRPPath = newRPPath.substring(0,newRPPath.lastIndexOf("/"));
					}
					newRPPath += "/"+appendToPath;
				} else {
					String appendTo = "/"+appendToPath;
					newRPPath = newRPPath.substring(0,newRPPath.lastIndexOf("/"))+appendTo;
				}
				
				boolean contains = true;
				
				if (!nodeNames.contains(newRPPath)) {
					contains = false;
					Page newRPPage = new Page(newRPPath);
					nodeNames.add(newRPPath);
					nodes.add(newRPPage);
					for (int i=0; i<graph.size(); i++) {
						graph.get(i).add(false);
						linkTexts.get(i).add("");
						fullTags.get(i).add(new Object());
					}
					graph.add(new ArrayList<Boolean>());
					linkTexts.add(new ArrayList<String>());
					fullTags.add(new ArrayList<Object>());
					for (int i=0; i<graph.size(); i++) {
						graph.get(graph.size()-1).add(false);
						linkTexts.get(graph.size()-1).add("");
						fullTags.get(graph.size()-1).add(new Object());
					}
				}
				int node1loc = nodeNames.indexOf(currentLocation.getPathName());
				int node2loc = nodeNames.indexOf(newRPPath);
				if (!graph.get(node1loc).get(node2loc)) {
					graph.get(node1loc).set(node2loc, true);
					linkTexts.get(node1loc).set(node2loc, e.text());
					fullTags.get(node1loc).set(node2loc, e);
					//System.out.println(node1loc+": "+currentLocation.getPathName()+"  -"+e.text()+"->  "+node2loc+": "+newRPPath);
					//System.out.println();
					boolean sameDomain = true;
					try {
						if (!getDomainName(currentLocation.getPathName()).equals(getDomainName(newRPPath)));
					} catch (URISyntaxException e1) {
					}
					if (!contains && sameDomain) {
						parseATags(newRPPath);
					}
				}
				
			}
		
		}
	}

	
	public String getDomainName(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain.startsWith("www.") ? domain.substring(4) : domain;
	}
 

}
