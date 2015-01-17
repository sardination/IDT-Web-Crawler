import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class jsouptest {

	public static void main(String[] args) {
		
		/*
		String html = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("gu.txt"));
			String nextline = br.readLine();
			while (nextline != null) {
				html += nextline;
				nextline = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect("http://localhost:8080/version1").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(doc);
		
	}

}
