import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.InetAddress;
import java.io.*;
import java.rmi.ConnectException;

public class Parle extends UnicastRemoteObject implements MyRemoteInterface {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static MyRemoteInterface handle;

	public Parle() throws RemoteException {}

	public void printMessage(String message) throws RemoteException {
		System.out.println(">>" + message);
	}
	
	public void k(){
		System.exit(0);
	}

	public static void main(String args[]) throws Exception{
		prepareListener();
		prepareSpeaker();
		talk();
	}

	private static void prepareListener() throws Exception {
		Parle server = new Parle();

		Registry reg = createRegistry();
		reg.bind("myrmiserver", server);	// Key, value tuple to be stored in registry
												//https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/Registry.html#bind(java.lang.String,%20java.rmi.Remote)

		System.out.println("Server established");
		System.out.println(InetAddress.getLocalHost());		// http://www.programmingsimplified.com/java/source-code/java-program-ip-address
	}

	private static Registry createRegistry() throws Exception{
		System.out.println("Enter port to listen on: ");
		int port = Integer.parseInt(in.readLine());
		return LocateRegistry.createRegistry(port);	// Store remote object in local registry, Bypasses process separation. Listen on port 1234
														//	https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html
	}

	private static void prepareSpeaker() throws Exception {
		Registry reg = locateRegistry();
		handle =
			(MyRemoteInterface) reg.lookup("myrmiserver");	// Return object linked to key. Cast to MyRemoteInterface.
																// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
	}

	private static Registry locateRegistry() throws Exception{
		System.out.println("Enter the IP address of the sever: ");
		String ip = in.readLine();
		System.out.println("Enter port of the server: ");
		int port = Integer.parseInt(in.readLine());
		return LocateRegistry.getRegistry(ip, port);
			// Uses socket: IP + port to retreive remote object reference from server
				// https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html#getRegistry(java.lang.String,%20int)
	}

	private static void talk(){
		try {
			System.out.println("##Connection Established: Partner Listening.");
			boolean more = true;
			while (more){
				String message = in.readLine();
				handle.printMessage(message);		// Execute remote method.
				if (message.equals("END"))
					more = false;
			}
			handle.k();
		}
		catch (Exception ex){
			System.err.println(ex);
			System.exit(0);
		}
	}
}
