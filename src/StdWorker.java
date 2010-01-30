import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.UUID;



/**
 * Suppose to 'work' upon a connection for a std. client.
 * 
 */
class StdWorker extends Thread { 
	Socket sock;	
	
	WorkPerformer _performer;
	
	
	/**
	 * Constructs a new JokeWorker Object.
	 * @param s The socket connection to work with. Msgs are sent over this.
	 * @param ServiceMap The server maintained list of ClientIDs and 
	 * 	each client's list of jokes. Jokes are deleted as they are used.
	 */
	public StdWorker( Socket s ) {
		sock = s;
		_performer = PerformerFactory.HttpServer(sock);
	}
	
	/**
	 * Perform the process.
	 */
	public void run() {
		try {
			
			_performer.execute();
			
			sock.close();
		} catch( IOException io ) {
			System.out.println( "error" );
			io.printStackTrace();
		}	
	}
}