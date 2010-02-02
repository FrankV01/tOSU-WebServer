import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Suppose to 'work' upon a connection for a std. client.
 * 
 */
class StdWorker extends Thread { 
	Socket _sock;	
	String _serveFromPath;
	DebugPrintable _dPrinter;
	
	/**
	 * Constructs a new JokeWorker Object.
	 * @param s The socket connection to work with. Msgs are sent over this.
	 */
	public StdWorker( Socket s, String PathToServeFrom, DebugPrintable DebugPrinter ) {
		_sock = s;
		_serveFromPath = PathToServeFrom;
		_dPrinter = DebugPrinter;
	}
	
	/**
	 * Perform the process. <strong>not done</strong>
	 */
	public void run() {
		PrintStream out = null;
		BufferedReader in = null;
		ArrayList<String> _clientCom = new ArrayList<String>(); 
		
		try {
			out = new PrintStream( _sock.getOutputStream() );
			in = new BufferedReader( new InputStreamReader(_sock.getInputStream()) );
			
			
			//Just echo what the client sends - we should get at least one line...
			while( in.ready() || (_clientCom.size() == 0) ) {
				_clientCom.add( in.readLine() );
			}
			
			HttpPage _pg = null;
			HttpClientHeaders _header = null;
			
			if( _clientCom.size() != 0 ) {
				
				// This is an "always" message.
				System.out.println( String.format("Processing Request for: %s", _clientCom.get(0)) );
				
				
				//Find the file.
				String _fileName = _clientCom.get(0).split(" ")[1];
				_dPrinter.printMessage(_fileName);
				
				
				try {
					_pg = new HtmlFileSystemPage(new StringBuilder(_serveFromPath).append(_fileName).toString(), _dPrinter);
					_header = HttpClientHeadersImpl.newSuccessHeaders(_pg);
				} catch( IllegalArgumentException ex ) {
					//Terrible way to find out if the file exists
					// but this is just an example program.
					_pg = new Html404ErrorPage();
					_header = HttpClientHeadersImpl.new404ErrorHeaders(_pg);
				}
			} else {
				//No info received from the client? Server probably screwed up.
				_dPrinter.printError("No text buffered from client");
				
				_pg = new HtmlGenericErrorPage();
				_header = HttpClientHeadersImpl.new500ErrorHeaders(_pg);
			}
			
			out.print( _header.toString() );
			out.print(_pg.render());
			
			_sock.close();
			
		} catch( IOException ex ) {
			ex.printStackTrace();
			_dPrinter.printError( "IOException occured in StdWorker.run()" );
		}
	
	}
}


