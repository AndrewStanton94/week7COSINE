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

		Registry reg = LocateRegistry.createRegistry(1234);
		reg.bind("myrmiserver", server);
	}

	public MyServer() throws RemoteException {}
}
