import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


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
	public static Thread newServerWorker(Socket s, WorkPerformer performer) {
		return new StdWorker(s, performer);
	}
}
