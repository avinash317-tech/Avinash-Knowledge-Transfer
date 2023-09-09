
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.JSONObject;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


public class Sftp {
	
	private static String REMOTE_HOST = "<SFTP Host Address" ;
    private static String USERNAME = "<SFTP user Name>" ;
    private static String PASSWORD = "<SFTP user password>";
    private static int REMOTE_PORT = 22;//<SFTP port> default port is 22
    private static String SFTP_REMOTE_DIR = "<SFTP file upload path>";
	
	  static Session jschSession = null;
	  
	  public static void main(String args[]) {
		  try {
			uploadFile();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	//create connection
	private static ChannelSftp setupJsch() throws JSchException {
		try {
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			
		    JSch jsch = new JSch();
		    //jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
		    jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
		    jschSession.setPassword(PASSWORD);
		    jschSession.setConfig(config);
		    System.out.println(jschSession.getUserName());
		    jschSession.connect();
		    System.out.println("connection :- " + jschSession.isConnected());
		    return (ChannelSftp) jschSession.openChannel("sftp");
		    }catch (Exception e) {
				// TODO: handle exception
		    	e.printStackTrace();
		    	System.out.println(jschSession.isConnected());
		    	System.out.println("exception in connection :- "+ e.getMessage());
		    	return null;
			}
	}
	
	//reading data
	public InputStream whenReadFileUsingJsch_thenSuccess(String sftpRemotePath, String fileName) throws Exception{
		try {
			
			//creating connection
		    ChannelSftp channel = setupJsch();
		    channel.connect();
	        System.out.println(sftpRemotePath+fileName);
			InputStream stream = channel.get(sftpRemotePath+fileName);
			
			
			
	        }
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("reading error : "+e.getMessage());
			e.printStackTrace();
		}
       
        return stream;
	}
	
	private static void uploadFile() throws JSchException, SftpException , Exception {

		whenUploadFileUsingJsch_thenSuccess(<file-path+file-name>);
		
	}

}
