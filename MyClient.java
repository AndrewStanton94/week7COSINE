import java.rmi.registry.*;
import java.io.*;

public class MyClient{
	public static void main(String args []) throws Exception{
		Registry reg = LocateRegistry.getRegistry("148.197.53.207", 1234);	// Uses socket: IP + port to retreive remote object reference from server
																				// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
		MyRemoteInterface handle =
			(MyRemoteInterface) reg.lookup("myrmiserver");	// Return object linked to key. Cast to MyRemoteInterface.
																// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			boolean more = true;
			while (more){
				String message = in.readLine();
				handle.printMessage(message);		// Execute remote method.
				if (message.equals("END"))
					more = false;
			}
		}
		catch (Exception ex){
			System.err.println(ex);
		}
	}
}
