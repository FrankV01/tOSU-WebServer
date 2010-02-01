import java.net.Socket;

/**
 * Provides new instances of different worker threads
 * for the servers.
 * @author Frank
 *
 */
final class WorkerFactory {

	/**
	 * Provides new server worker threads which handle
	 * admin related stuff. Such as the ability to change
	 * type of server or to shut down the server.
	 * @param s The socket to communicate over
	 * @return The instance.
	 */
	public static Thread newServerWorker(Socket s) {
		return new StdWorker(s);
	}
	
	public static Thread newListener(Socket s) {
		return new Thread(new MyListener(s));
	}
}
