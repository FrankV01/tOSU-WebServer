import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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


class HtmlDirectoryListingPage implements HttpPage {
	
	HtmlDirectoryListingPage( File DirPath ) {
		if( DirPath == null )
			throw new IllegalArgumentException("DirPath");
		
		if( !DirPath.isDirectory() )
			throw new IllegalArgumentException( "DirPath must be a directory." );
		
		
	}

	@Override
	public String contentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String render() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}


class HtmlFileSystemPage implements HttpPage {
	String _pathToFile;
	HttpPage _404;
	HttpPage _genericError;
	DebugPrintable _dPrinter;
	
	HtmlFileSystemPage( String PathToFile, DebugPrintable DebugPrinter ) {
		_pathToFile = PathToFile;
		_dPrinter = DebugPrinter;
		
		
		_dPrinter.printMessage( String.format("Looking for file at: %s", _pathToFile) );
		
		if( !fileExist() )
			throw new IllegalArgumentException("PathToFile");
	
		_404 = new Html404ErrorPage();
		_genericError = new HtmlGenericErrorPage();
	}
	
	//This implementation will just open the file,
	// gather the contents and send them back in 
	// a string.
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