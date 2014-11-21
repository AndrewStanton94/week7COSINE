import java.rmi.registry.*;
import java.rmi.ConnectException;
import java.io.*;

public class MyClient{
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args []) throws Exception{
		Registry reg = locateRegistry();
		MyRemoteInterface handle =
			(MyRemoteInterface) reg.lookup("myrmiserver");	// Return object linked to key. Cast to MyRemoteInterface.
																// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
		try {
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

	private static Registry locateRegistry() throws Exception{
		System.out.println("Enter the IP address of the sever: ");
		String ip = in.readLine();
		System.out.println("Enter port: ");
		int port = Integer.parseInt(in.readLine());
		return LocateRegistry.getRegistry(ip, port);
			// Uses socket: IP + port to retreive remote object reference from server
				// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
	}
}
