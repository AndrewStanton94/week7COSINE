import java.rmi.*;

public interface MyRemoteInterface extends Remote{
	void printMessage(String message) throws RemoteException;
}
