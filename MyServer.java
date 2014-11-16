import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class MyServer extends UnicastRemoteObject
	implements MyRemoteInterface {

	public void printMessage(String message) throws RemoteException {
		System.out.println(message);
	}

	public static void main(String args []) throws Exception {
		MyServer server = new MyServer();

		Registry reg = LocateRegistry.createRegistry(1234);	// Store remote object in local registry, Bypasses process separation.
															//	Listen on port 1234
																//	https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/LocateRegistry.html
		reg.bind("myrmiserver", server);	// Key, value tuple to be stored in registry
												//https://docs.oracle.com/javase/7/docs/api/java/rmi/registry/Registry.html#bind(java.lang.String,%20java.rmi.Remote)
	}

	public MyServer() throws RemoteException {}
}
