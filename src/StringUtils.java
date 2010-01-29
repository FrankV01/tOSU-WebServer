import java.util.Arrays;


/**
 * Contains String Utilities
 * @author Frank
 *
 */
class StringUtils {
	
	/**
	 * Repeats a single character.
	 * @param Char
	 * @param count How many times. 
	 * @return The string of repeated chars. 
	 */
	public static String repeat(char Char, int count ) {
		if( count <= 0 )
			return "";
		
		char[] chars = new char[count];
		Arrays.fill(chars, Char);
		return new String(chars);
	}
}
