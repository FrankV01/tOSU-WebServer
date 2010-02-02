import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * Represents a server-built in page
 * that can be generated.
 * @author Frank
 *
 */
public interface HttpPage {
	
	/**
	 * Returns the generated page.
	 * No actual file is produced or saved.
	 * @return The page's HTML. 
	 */
	String render();
	
	/**
	 * returns the page size for the content-size attribute.
	 * @return the size as an integer.
	 */
	int size();
	
	/**
	 * Represents the content type -- text/html, text/xml, etc.
	 * @return the type as a string.
	 */
	String contentType();
	
}

/**
 * Factory to generate HttpPage objects.
 * @author FrankV
 *
 */
class HttpPageFactory {
	
	/**
	 * Provides a new 404 Page
	 * @return the object.
	 */
	public static HttpPage new404Error() {
		return new Html404ErrorPage();
	}
	
	/**
	 * Provides a new 'generic' error page.
	 * @return the object.
	 */
	public static HttpPage newGenericError() {
		return new HtmlGenericErrorPage();
	}
	
	/**
	 * Provides a new Directory listing object
	 * @param Path The file object to list
	 * @param DebugPrinter An debug printable object for debug printing
	 * @return The object.
	 */
	public static HttpPage newDirectoryListingPage( File Path, DebugPrintable DebugPrinter ) {
		return new HtmlDirectoryListingPage(Path, DebugPrinter);
	}
	
	/**
	 * Provides a new FileSystem page which is
	 * a single [text] file to be sent to a client.
	 * @param file The file object to send.
	 * @param DebugPrinter A debug printable object for debug printing
	 * @return the object
	 */
	public static HttpPage newFileSystemPage( File file, DebugPrintable DebugPrinter ) {
		return new HtmlFileSystemPage2( file, DebugPrinter );
	}
}


/**
 * Represents a plain, standard 404 Error page
 * @author FrankV
 *
 */
class Html404ErrorPage implements HttpPage {

	@Override
	public String render() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Page not Found</title></head>");
		_sb.append("<body><h1>The page could not be found</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}

	@Override
	public int size() {
		return render().getBytes().length;
	}

	@Override
	public String contentType() {
		return "text/html";
	}
}

/**
 * Represents a plain, generic error page
 * @author FrankV
 *
 */
class HtmlGenericErrorPage implements HttpPage {
	
	@Override
	public String render() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Server Error Occured</title></head>");
		_sb.append("<body><h1>A server error has occured.</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}
	
	@Override
	public int size() {
		return render().getBytes().length;
	}

	@Override
	public String contentType() {
		return "text/html";
	}
}


/**
 * Generates an HTML page for the given directory path.
 * @author FrankV
 *
 */
class HtmlDirectoryListingPage implements HttpPage {
	String _buf; //To sort of cache the results.
	File _dir;
	DebugPrintable _dPrinter;
	
	HtmlDirectoryListingPage( File DirPath, DebugPrintable DebugPrinter ) {
		if( DirPath == null )
			throw new IllegalArgumentException("DirPath");
		
		if( !DirPath.isDirectory() )
			throw new IllegalArgumentException( "DirPath must be a directory." );
		
		_dir = DirPath;
		_buf = null;
		_dPrinter = DebugPrinter;
		
		_dPrinter.printMessage("Successfully loaded HtmlDirectoryListPage");
	}

	@Override
	public String contentType() {
		return "text/html";
	}

	@Override
	public String render() {
		if( _buf == null ) {
			_dPrinter.printMessage("Building Buffer - HtmlDirectoryListingPage");
			
			StringBuilder _sb = new StringBuilder();
			_sb.append( "<html>" );
			_sb.append( String.format("<head><title>Directory list for %s</title></head>", _dir.getPath()) );
			_sb.append( "<body>" );
			
			_sb.append("<h1>Directory Listing for ");
			_sb.append( _dir.getPath() ); 
			_sb.append("</h1>");
			
			_sb.append("<ul>");
			for( String s : Arrays.asList(_dir.list()) )  {
				String _buf = new StringBuilder(_dir.getPath()).append("/").append(s).toString();
				_dPrinter.printMessage(_buf);
				String t = (new File(_buf).isDirectory()) ? "folder" : "file";
				_sb.append( String.format("<li><a href=\"%s\">%s</a> - [<em>%s</em>]</li>", s, s, t) );
			}
			_sb.append("</ul>");
			
			_sb.append( "</body>" );
			_sb.append( "</html>" );
			_buf = _sb.toString();
		}
		
		return _buf;
	}

	@Override
	public int size() {
		return render().getBytes().length;
	}
}

/**
 * A revised version of <code>HtmlFileSystemPage</code>.
 * Which operates with a <code>File</code> object.
 * @author FrankV
 */
class HtmlFileSystemPage2 extends HtmlFileSystemPage {
	File _file;
	
	HtmlFileSystemPage2( File file, DebugPrintable DebugPrinter ) {
		super( file.getPath(), DebugPrinter );
		
		if( !file.isFile() ) 
			throw new IllegalArgumentException("file must be file");
		
		_file = file;
	}
}

/**
 * Loads the given file and allows a medium 
 * to send the file to a Http client.
 * @author FrankV
 *
 */
class HtmlFileSystemPage implements HttpPage {
	String _pathToFile;
	HttpPage _404;
	HttpPage _genericError;
	DebugPrintable _dPrinter;
	
	/**
	 * Constructor.
	 * @param PathToFile - The path of the file to send up render
	 * @param DebugPrinter - DebugPrintable to provide debug out too
	 */
	HtmlFileSystemPage( String PathToFile, DebugPrintable DebugPrinter ) {
		_pathToFile = PathToFile;
		_dPrinter = DebugPrinter;
		
		
		_dPrinter.printMessage( String.format("Looking for file at: %s", _pathToFile) );
		
		if( !fileExist() )
			throw new IllegalArgumentException("PathToFile");
	
		_404 = new Html404ErrorPage();
		_genericError = new HtmlGenericErrorPage();
	}
	
	/**
	 * This implementation will just open the file,
	 * gather the contents and send them back in
	 * a string.
	 * 
	 * {@inheritDoc}
	 */
	public String render() {
		StringBuilder _sb = new StringBuilder();
		BufferedReader _bReader = null;
		
		try {
			_bReader = new BufferedReader(new FileReader(_pathToFile));
		}catch( FileNotFoundException ex ) {
			//Shouldn't be possible....
			_dPrinter.printError("*** 404 from HtmlFileSystemPage *** ");
			ex.printStackTrace();
			return _404.render();
		}
		
		try {
			String _buf = null;
			
			while( (_buf = _bReader.readLine()) != null) {
				_sb.append(_buf);
			}
			
			return _sb.toString();
			
		} catch( IOException ex ) {
			_dPrinter.printError( "An error occured while reading the html file to serve." );
			ex.printStackTrace();
			return _genericError.render();
		}	
	}
	
	@Override
	public int size() {
		return render().getBytes().length;
	}
	
	/**
	 * Check if the file given in the constructor exists
	 * @return True if it does exist; false otherwise.
	 */
	private boolean fileExist() {
		return new File(_pathToFile).exists();
	}

	@Override
	public String contentType() {
		return "text/html";
	}
}