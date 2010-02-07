
import java.util.Map;


public interface HttpFileRequest {
	String getName();
	
	/**
	 * Gets the arguments from the Http Request
	 * @return The map of arguments or empty map if no arguments.
	 */
	Map<String, String> getArguments();
}

