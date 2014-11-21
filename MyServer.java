import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.InetAddress;
import java.io.*;

public class MyServer extends UnicastRemoteObject
	implements MyRemoteInterface {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public MyServer() throws RemoteException {}

	public void printMessage(String message) throws RemoteException {
		System.out.println(message);
	}

	public static void main(String args []) throws Exception {
		MyServer server = new MyServer();

		Registry reg = createRegistry();
		reg.bind("myrmiserver", server);	// Key, value tuple to be stored in registry
												//https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/Registry.html#bind(java.lang.String,%20java.rmi.Remote)

		System.out.println("Server established");
		System.out.println(InetAddress.getLocalHost());		// http://www.programmingsimplified.com/java/source-code/java-program-ip-address
	}

	private static Registry createRegistry() throws Exception{
		System.out.println("Enter port: ");
		int port = Integer.parseInt(in.readLine());
		return LocateRegistry.createRegistry(port);	// Store remote object in local registry, Bypasses process separation. Listen on port 1234
														//	https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html
		
	}
}
