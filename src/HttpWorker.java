import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Suppose to 'work' upon a connection for a std. client.
 * 
 */
class HttpWorker extends Thread { 
	Socket _sock;	
	String _serveFromPath;
	DebugPrintable _dPrinter;
	
	/**
	 * Constructs a new JokeWorker Object.
	 * @param s The socket connection to work with. Msgs are sent over this.
	 */
	public HttpWorker( Socket s, String PathToServeFrom, DebugPrintable DebugPrinter ) {
		_sock = s;
		_serveFromPath = PathToServeFrom;
		_dPrinter = DebugPrinter;
	}
	
	/**
	 * Perform the process. <strong>not done</strong>
	 */
	public void run() {
		OutputStream out = null;
		PrintStream pOut = null;
		BufferedReader in = null;
		ArrayList<String> _clientCom = new ArrayList<String>(); 
		
		try {
			in = new BufferedReader( new InputStreamReader(_sock.getInputStream()) );
			out = new BufferedOutputStream( _sock.getOutputStream() );
			pOut = new PrintStream( out );
			
			//Just echo what the client sends - we should get at least one line...
			while( in.ready() || (_clientCom.size() == 0) ) {
				_clientCom.add( in.readLine() );
			}
			
			HttpContent _pg = null;
			HttpClientHeaders _header = null;
			
			boolean _valid = (_clientCom.size() != 0 && (_clientCom.get(0).endsWith("HTTP/1.1") || _clientCom.get(0).endsWith("HTTP/1.0")) ); 
			if( _valid ) {
				
				// This is an "always" message.
				System.out.println( String.format("Processing Request for: %s", _clientCom.get(0)) );
				
				
				//Find the file.
				String _fileName = _clientCom.get(0).split(" ")[1];
				_dPrinter.printMessage(_fileName);
				
				
				try {
					String _path = new StringBuilder(_serveFromPath).append(_fileName).toString();
					HttpFile _file = new HttpFile(_path);
					
					_pg = HttpContentFactory.byFileExtention(_file, _dPrinter);
					_header = HttpClientHeadersImpl.newSuccessHeaders(_pg);
				} catch( IllegalArgumentException ex ) {
					// Not likely to occur but can. This handles the error
					//  and prints an error on the server's console.
					_pg = HttpContentFactory.new404Error();
					_header = HttpClientHeadersImpl.new404ErrorHeaders(_pg);
					_dPrinter.printError(ex.toString()); 
				}
			} else {
				//No info received from the client? Server probably screwed up.
				_dPrinter.printError("No text buffered from client");
				
				_pg = HttpContentFactory.newGenericError();
				_header = HttpClientHeadersImpl.new500ErrorHeaders(_pg);
			}
			
			pOut.print( _header.toString() );
			
			//out.print(_pg.render());
			sendFile( _pg.generate(), out );
			
			out.flush();
			
			_sock.close();
			
		} catch( IOException ex ) {
			ex.printStackTrace();
			_dPrinter.printError( "IOException occured in StdWorker.run()" );
		}
	
	}
	
	/**
	 * Send the 'InputSteam' to the 'OutputStream'. Code 
	 * adapted from "A Web Server in 150 Lines"
	 * @param file The input file
	 * @param out the stream to output too.
	 * @see <a href="http://www.brics.dk/~amoeller/WWW/javaweb/server.html">http://www.brics.dk/~amoeller/WWW/javaweb/server.html</a>
	 */
    private void sendFile(InputStream file, OutputStream out)
    {
        try {
            byte[] buffer = new byte[1000];
            while (file.available() > 0) {
                out.write(buffer, 0, file.read(buffer));
            }
        } catch (IOException e) { 
        	_dPrinter.printError(e.toString()); 
        }
    }

}


