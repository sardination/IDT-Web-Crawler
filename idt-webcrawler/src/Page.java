import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Page {
	
	private String path;
	//private ArrayList<Page> route = new ArrayList<Page>();
	private boolean live;
	private String errorType;
	private int responseCode;
	
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
	
	//public void setRoute(ArrayList<Page> route) {
	//	this.route = route;
	//}
	
	public String toString() {
		return (responseCode+" "+path);
	}

}
