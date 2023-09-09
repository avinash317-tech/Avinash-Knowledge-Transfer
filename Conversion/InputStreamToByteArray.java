
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class StramParse {
	
	//convert InputStream to Byte[]
	public static byte[] streamToByteArray(InputStream stream) throws IOException {

	    byte[] buffer = new byte[1024];
	    ByteArrayOutputStream os = new ByteArrayOutputStream();

	    int line = 0;
	    // read bytes from stream, and store them in buffer
	    while ((line = stream.read(buffer)) != -1) {
	        // Writes bytes from byte array (buffer) into output stream.
	        os.write(buffer, 0, line);
	    }
	    stream.close();
	    os.flush();
	    os.close();
	    return os.toByteArray();
	}
	  
	  public static void main(String args[]) {
		  try {
			byte[] = streamToByteArray(InputStream stream);
		  }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	
}
