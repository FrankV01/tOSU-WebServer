import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;



/**
 * Suppose to 'work' upon a connection for a std. client.
 * 
 */
class StdWorker extends Thread { 
	Socket _sock;	
	String _serveFromPath;
	
	/**
	 * Constructs a new JokeWorker Object.
	 * @param s The socket connection to work with. Msgs are sent over this.
	 */
	public StdWorker( Socket s ) {
		_sock = s;
		_serveFromPath = ".";
	}
	
	/**
	 * Perform the process. <strong>not done</strong>
	 */
	public void run() {
		PrintStream out = null;
		BufferedReader in = null;
		ArrayList<String> _clientCom = new ArrayList<String>(); 
		boolean _is404 = false;
		
		try {
			out = new PrintStream( _sock.getOutputStream() );
			in = new BufferedReader( new InputStreamReader(_sock.getInputStream()) );
			
			//Just echo what the client sends.
			while( in.ready() ) {
				_clientCom.add( in.readLine() );
			}
			
			System.out.println( String.format("Processing Request for: %s", _clientCom.get(0)) );
			
			//Find the file.
			String _fileName = _clientCom.get(0).split(" ")[1];
			System.out.println(_fileName);
			
			HttpPage _pg = null;
			
			try {
				_pg = new HtmlFileSystemPage(new StringBuilder(_serveFromPath).append(_fileName).toString());
				_is404 = false;
			} catch( IllegalArgumentException ex ) {
				//Terrible way to find out if the file exists
				// but this is just an example program.
				_is404 = true;
				_pg = new HtmlErrorPage();
			}
			
			if( !_is404 ) {
				//Start sending the output -- assuming we find the file.
				out.print( String.format("HTTP/1.1 200 OK\nContent-Length:%d\nContent-Type: text/html", _pg.pageSize()) );
				out.print("\r\n\r\n");
			} else {
				out.print( String.format("HTTP/1.1 404 Page Not Found\nContent-Length:%d\nContent-Type: text/html", _pg.pageSize()) );
				out.print("\r\n\r\n");
			}
			
			out.print(_pg.Generate());
			
			_sock.close();
			
		} catch( IOException ex ) {
			ex.printStackTrace();
		}
	
	}
}




class HtmlErrorPage implements HttpPage {

	@Override
	public String Generate() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Page not Found</title></head>");
		_sb.append("<body><h1>The page could not be found</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}

	@Override
	public int pageSize() {
		return Generate().getBytes().length;
	}
}

class HtmlGenericErrorPage implements HttpPage {
	
	@Override
	public String Generate() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Server Error Occured</title></head>");
		_sb.append("<body><h1>A server error has occured.</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}
	
	@Override
	public int pageSize() {
		return Generate().getBytes().length;
	}
}

class HtmlFileSystemPage implements HttpPage {
	String _pathToFile;
	HttpPage _404;
	HttpPage _genericError;
	
	HtmlFileSystemPage( String PathToFile ) {
		_pathToFile = PathToFile;
		
		if( !fileExist() )
			throw new IllegalArgumentException("PathToFile");
	
		_404 = new HtmlErrorPage();
		_genericError = new HtmlGenericErrorPage();
	}
	
	//This implementation will just open the file,
	// gather the contents and send them back in 
	// a string.
	public String Generate() {
		StringBuilder _sb = new StringBuilder();
		BufferedReader _bReader = null;
		
		try {
			_bReader = new BufferedReader(new FileReader(_pathToFile));
		}catch( FileNotFoundException ex ) {
			//Shouldn't be possible....
			System.err.println( "*** 404 from HtmlFileSystemPage *** ");
			ex.printStackTrace();
			return _404.Generate();
		}
		
		try {
			String _buf = null;
			
			while( (_buf = _bReader.readLine()) != null) {
				_sb.append(_buf);
			}
			
			return _sb.toString();
			
		} catch( IOException ex ) {
			System.err.println( "An error occured while reading the html file to serve." );
			ex.printStackTrace();
			return _genericError.Generate();
		}
		
	}
	
	@Override
	public int pageSize() {
		return Generate().getBytes().length;
	}
	
	/**
	 * Check if the file given in the constructor exists
	 * @return True if it does exist; false otherwise.
	 */
	private boolean fileExist() {
		return new File(_pathToFile).exists();
	}


}