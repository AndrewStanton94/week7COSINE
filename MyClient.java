import java.rmi.registry.*;

public class MyClient{
	public static void main(String args []) throws Exception{
		Registry reg = LocateRegistry.getRegistry("148.197.144.182", 1234);
		MyRemoteInterface handle =
			(MyRemoteInterface) reg.lookup("myrmiserver");
		handle.printMessage("Wassup!");
	}
}
