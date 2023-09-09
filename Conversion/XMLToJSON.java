

import org.json.JSONObject;
import org.json.XML;


public class XMLToJSON {
	
	//xml to json object conversion
	public JSONObject xml2json(String xml) throws Exception {
		if(xml.equals(null)|| xml == null || xml == "" ||xml.equals("")) {
			throw new Exception("XML is Null or Blank");
		}
		return XML.toJSONObject(xml);
	}
	  
	  public static void main(String args[]) {
		  try {
			  
			  String xml = "<xml text>";
			JSONObject json = xml2json(String xml);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
