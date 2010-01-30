import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class WebServer {
	private static boolean _debugMode;
	private static int _port;
	private static int q_len = 6;
	private static final String _serverName = "Web Server";
	private static final String _description = "A basic web server implementation.";
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		processArguments( args );
		
		try {
			ServerSocket servsock = new ServerSocket( _port, q_len );
			System.out.println( "Server Starting" );
			
			while( true ) {
				Socket sock = servsock.accept();
				WorkerFactory.newServerWorker(sock).start();
				
				
			}
		} catch( IOException ie ) {
			System.out.println( "Error starting JokeClientServer!" );
		}
	}

	private static void processArguments(String[] args) {
		List<Argument<String>> _defaults = ArgumentFactory.newArgumentList();
		_defaults.add( ArgumentFactory.newArgument( "?", "false", "Show Help and exit (Alt. -?).") );
		_defaults.add( ArgumentFactory.newArgument( "p", "8001", "The port to serve the Client peice on.") );
		_defaults.add( ArgumentFactory.newArgument( "d", "false", "Enable Debug Mode (Alt. -d)") );
		
		ArgumentProcessor _args = ArgumentFactory.newArgumentProcessor(args, _defaults);
		
		_args.setTitle( _serverName );
		_args.setDescription( _description );
		
		if( Boolean.parseBoolean(_args.getValue("?")) ) {
			System.out.println( _args.getHelp() );
			System.exit(0);
		}
		
		_debugMode = Boolean.parseBoolean(_args.getValue("d") );
		String tmpPort = _args.getValue("p");
		_port = Integer.parseInt( tmpPort );
		
		// If in debug mode, lets output the loaded settings. 
		if( _debugMode ) {
			System.out.println( ArgumentInfoFormatter.getArgumentInfo(_args) );
		}
		
	}
}


