import java.net.Socket;


public class PerformerFactory {
	public static WorkPerformer HttpServer(Socket Sock) {
		return new HttpServer(Sock);
	}
}
