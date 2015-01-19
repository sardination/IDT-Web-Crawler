import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//getResponseCode for error
//making a graph with nodes and paths :3

public class RecursiveParser {
	public static ArrayList<ArrayList<Boolean>> graph = new ArrayList<ArrayList<Boolean>>(); 
		//graph.get(a).get(b) checks the path from nodes.get(a) to nodes.get(b)
	public static ArrayList<ArrayList<String>> linkTexts = new ArrayList<ArrayList<String>>();
	public static ArrayList<Page> nodes = new ArrayList<Page>();
	public static ArrayList<String> nodeNames = new ArrayList<String>();
	

	public static void main(String[] args) {
		
		parseATags("http://doodlygreetings.com/"); //default test value for now
		
		for (Page p:nodes) {
			System.out.println(p);
		}
		
		for (int i=0; i<graph.size(); i++) {
			for (int j=0; j<graph.get(i).size(); j++) {
				System.out.print(linkTexts.get(i).get(j)+": "+graph.get(i).get(j)+"     ");
			}
			System.out.println();
			System.out.println();
		}

	}
	
	public static void parseATags(String url) {
		
		if (url.length()>=1 && !url.substring(0,url.length()-1).equals("/")) {
			HttpURLConnection con;
			try {
				con = (HttpURLConnection) new URL(url+"/index.html").openConnection();
		 	      con.setRequestMethod("HEAD");
		 	      if (con.getResponseCode() != 404) {
		 	    	  url = url+"/index.html";
		 	      }
			} catch (MalformedURLException e) {
				System.out.println("m");
				//System.out.println("m");
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("e");
				//System.out.println("i");
				//e.printStackTrace();
			}
		}
		
		Page currentLocation = new Page(url); //the page the parser is currently on
		//somehow check if they're in the root and don't force them to input index.html
		currentLocation.setRoute(new ArrayList<Page>()); //sets original route to nothing (root page)
				
		Document doc = null;
		
		boolean go = true;
		
		try {
			doc = Jsoup.connect(currentLocation.getPathName()).get(); //gets all the html information from the page
		} catch (IOException e) {
			//print a notice saying that the link is unreachable 
			//e.printStackTrace();
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
					}
					graph.add(new ArrayList<Boolean>());
					linkTexts.add(new ArrayList<String>());
					for (int i=0; i<graph.size(); i++) {
						graph.get(graph.size()-1).add(false);
						linkTexts.get(graph.size()-1).add("");
					}
				}
				int node1loc = nodeNames.indexOf(currentLocation.getPathName());
				int node2loc = nodeNames.indexOf(newRPPath);
				if (!graph.get(node1loc).get(node2loc)) {
					graph.get(node1loc).set(node2loc, true);
					linkTexts.get(node1loc).set(node2loc, e.text());
					System.out.println(node1loc+": "+currentLocation.getPathName()+"  -"+e.text()+"->  "+node2loc+": "+newRPPath);
					System.out.println();
					if (!contains) {
						parseATags(newRPPath);
					}
				}
				
			}
		
		}
	}
	
	public static void parseUserInputs(String url) {
		
	}
 

}
