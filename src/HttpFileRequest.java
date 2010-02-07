
import java.util.Map;

/**
 * An interface to deal with HTTP file requests.
 * Generally, this interface is implemented on a sub
 * class of File (but this doesn't have to be the case)
 * @author Frank
 *
 */
public interface HttpFileRequest {
	String getName();
	
	/**
	 * Gets the arguments from the Http Request
	 * @return The map of arguments or empty map if no arguments.
	 */
	Map<String, String> getArguments();
}

