import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class WebServer {
	private enum ServerMode {
		Listener,
		WebServer
	};
	
	private static ServerMode _mode;
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
				if( _mode == ServerMode.WebServer )
					WorkerFactory.newServerWorker(sock).start();
				else
					WorkerFactory.newListener(sock).start();
				
				
			}
		} catch( IOException ie ) {
			System.out.println( "Error starting Web Server!" );
		}
	}

	private static void processArguments(String[] args) {
		List<Argument<String>> _defaults = ArgumentFactory.newArgumentList();
		_defaults.add( ArgumentFactory.newArgument( "?", "false", "Show Help and exit (Alt. -?).") );
		_defaults.add( ArgumentFactory.newArgument( "p", "2540", "The port to serve the Client peice on.") );
		_defaults.add( ArgumentFactory.newArgument( "d", "false", "Enable Debug Mode (Alt. -d)") );
		_defaults.add( ArgumentFactory.newArgument("l", "false", "Use 'MyListner' which echo's requests to the console."));
		
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
		
		if( Boolean.parseBoolean(_args.getValue("l")) )
			_mode = ServerMode.Listener;
		else
			_mode = ServerMode.WebServer;
		
		// If in debug mode, lets output the loaded settings. 
		if( _debugMode ) {
			System.out.println( ArgumentInfoFormatter.getArgumentInfo(_args) );
		}
		
	}
}


