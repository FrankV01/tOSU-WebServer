import java.net.Socket;


/**
 * Performs the core work of the HTTP server.
 * This is to actually serve the page. 
 * @author FrankV
 *
 */
class HttpServer implements WorkPerformer {
	
	Socket _sock;
	
	HttpServer(Socket Sock) {
		_sock = Sock;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * This should send the page to the client.
	 */
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
