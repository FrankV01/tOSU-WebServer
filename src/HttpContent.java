import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


/**
 * Represents a server-built in page
 * that can be generated.
 * @author Frank
 *
 */
public interface HttpContent {
	
	/**
	 * Returns the generated page as a string.
	 * No actual file is produced or saved.
	 * @return The page's HTML. 
	 */
	String render() throws UnsupportedOperationException; //allow this to throw an exception...
	
	/**
	 * Provides an InputStream of the content.
	 * @return An input stream used to output this method.
	 * @see http://www.brics.dk/~amoeller/WWW/javaweb/server.html
	 */
	InputStream generate();
	
	/**
	 * returns the page size for the content-size attribute.
	 * @return the size as an integer.
	 */
	int size();
	
	/**
	 * Represents the content type -- text/html, text/xml, etc.
	 * @return the type as a string.
	 */
	String type();
	
}

/**
 * Factory to generate HttpPage objects.
 * @author FrankV
 *
 */
class HttpPageFactory {
	
	public static HttpContent byFileExtention( File theFile, DebugPrintable debugPrinter ) {
		
		if( theFile.isDirectory() )
			return newDirectoryListingPage(theFile, debugPrinter);
		
		if( theFile.isFile() ) {
			String[] _t  = theFile.getName().split(".");
			String _ext = _t[_t.length-1];
			
			if( _ext == "html" || _ext == "htm" )
				return newFileSystemPage(theFile, debugPrinter);
			if( _ext == "css" )
				return null; //TODO
			else
				return newImageFile( theFile, debugPrinter );
		} else
			return null;
	}
	
	/**
	 * Provides a new 404 Page
	 * @return the object.
	 */
	public static HttpContent new404Error() {
		return new Html404ErrorPage();
	}
	
	/**
	 * Provides a new 'generic' error page.
	 * @return the object.
	 */
	public static HttpContent newGenericError() {
		return new HtmlGenericErrorPage();
	}
	
	/**
	 * Provides a new Directory listing object
	 * @param Path The file object to list
	 * @param DebugPrinter An debug printable object for debug printing
	 * @return The object.
	 */
	public static HttpContent newDirectoryListingPage( File Path, DebugPrintable DebugPrinter ) {
		return new HtmlDirectoryListingPage(Path, DebugPrinter);
	}
	
	/**
	 * Provides a new FileSystem page which is
	 * a single [text] file to be sent to a client.
	 * @param file The file object to send.
	 * @param DebugPrinter A debug printable object for debug printing
	 * @return the object
	 */
	public static HttpContent newFileSystemPage( File file, DebugPrintable DebugPrinter ) {
		return new HtmlFileSystemPage2( file, DebugPrinter );
	}
	
	public static HttpContent newImageFile( File file, DebugPrintable debugPrinter ) {
		return new HttpImageFile( file, debugPrinter );
	}
}


abstract class StringHtmlOnlyPage implements HttpContent {
	
	/**
	 * If 'render' isn't implemented in the child class 
	 * this returns null. 
	 */
	@Override
	public InputStream generate() {
		try {
			InputStream file = new ByteArrayInputStream( this.render().getBytes() );
			return file;
		} catch( UnsupportedOperationException ex ) {
			return null;
		}
	}
	

	@Override
	public int size() {
		try {
			return render().getBytes().length;
		} catch( UnsupportedOperationException ex ) {
			return 0;
		}
	}

	@Override
	public String type() {
		return "text/html";
	}
}

/**
 * Represents a plain, standard 404 Error page
 * @author FrankV
 *
 */
class Html404ErrorPage extends StringHtmlOnlyPage implements HttpContent {

	@Override
	public String render() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Page not Found</title></head>");
		_sb.append("<body><h1>The page could not be found</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}

}

/**
 * Represents a plain, generic error page
 * @author FrankV
 *
 */
class HtmlGenericErrorPage extends StringHtmlOnlyPage implements HttpContent {
	
	@Override
	public String render() {
		StringBuilder _sb = new StringBuilder();
		_sb.append("<html>");
		_sb.append("<head><title>Server Error Occured</title></head>");
		_sb.append("<body><h1>A server error has occured.</h1>");
		_sb.append("</html>");
		
		return _sb.toString();
	}
}


/**
 * Generates an HTML page for the given directory path.
 * @author FrankV
 *
 */
class HtmlDirectoryListingPage extends StringHtmlOnlyPage implements HttpContent {
	String _buf; //To sort of cache the results.
	DebugPrintable _dPrinter;
	File _dir;
	static File _rootDir;
	
	HtmlDirectoryListingPage( File DirPath, DebugPrintable DebugPrinter ) {
		if( DirPath == null )
			throw new IllegalArgumentException("DirPath");
		
		if( !DirPath.isDirectory() )
			throw new IllegalArgumentException( "DirPath must be a directory." );
		
		if( _rootDir == null ) {
			_rootDir = DirPath;
		}
		
		_dir = DirPath;
		_buf = null;
		_dPrinter = DebugPrinter;
		
		_dPrinter.printMessage("Successfully loaded HtmlDirectoryListPage");
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
			
			if( !_rootDir.equals(_dir) ) //Show the up.
				_sb.append( "<li><a href=\"..\">[Up one directory]</li>" );
			
			for( String s : Arrays.asList(_dir.list()) )  {
				String _buf = new StringBuilder(_dir.getPath()).append("/").append(s).toString();
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
}

class CssOnFileSystem implements HttpContent {
	File _file;
	DebugPrintable _dPrinter;
	
	CssOnFileSystem( File theFile, DebugPrintable debugPrinter ) {
		_file = theFile;
		_dPrinter = debugPrinter;
	}
	
	@Override
	public InputStream generate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String render() throws UnsupportedOperationException {
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String type() {
		return "text/css";
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
class HtmlFileSystemPage implements HttpContent {
	String _pathToFile;
	HttpContent _404;
	HttpContent _genericError;
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
	
		_404 = HttpPageFactory.new404Error(); 
		_genericError = HttpPageFactory.newGenericError();
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
	
	
	/**
	 * Check if the file given in the constructor exists
	 * @return True if it does exist; false otherwise.
	 */
	private boolean fileExist() {
		return new File(_pathToFile).exists();
	}

	@Override
	public InputStream generate() {
		try { //TODO: Figure how to implement this given the dumb complexity. Perhaps refactor this class out.
			_input = new FileInputStream(_file);
		} catch( IOException ioex ) {
			_dPrinter.printError(ioex.toString());
		}
		return _input;
	}

	@Override
	public int size() {
		return render().getBytes().length; 
	}

	@Override
	public String type() {
		return "text/html";
	}
}

class HttpImageFile implements HttpContent {
	File _file;
	DebugPrintable _dPrinter;
	
	InputStream _input;
	
	HttpImageFile( File pFile, DebugPrintable debugPrinter ) {
		_file = pFile;
		_dPrinter = debugPrinter;
		
		if( (!_file.exists()) || (!_file.isFile()) )
			throw new IllegalArgumentException("_file");
	}
	
	@Override
	public String render() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return (int) _file.length();
	}

	@Override
	public String type() {
		if( _file.getName().endsWith(".gif") )
			return "image/gif"; 
		else if( _file.getName().endsWith(".jpg") || _file.getName().endsWith(".jpeg") )
			return "image/jpeg";
		else //add if?
			return "image/png";
	}

	@Override
	public InputStream generate() {
		try {
			_input = new FileInputStream(_file);
		} catch( IOException ioex ) {
			_dPrinter.printError(ioex.toString());
		}
		return _input;
	}
}