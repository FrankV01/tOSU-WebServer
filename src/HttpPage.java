
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
}
