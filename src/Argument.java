
/**
 * Interface to an Argument to be used by argument Processors.
 * @author Frank
 *
 * @param <T> The type to expect.
 */
interface Argument<T> {
	
	/**
	 * Defaulted by the constructor.
	 * @return the value (i.e. setting)
	 */
	public T getValue();
	
	/**
	 * Set the value or setting.
	 * @param value 
	 */
	public void setValue( T value );
	
	/********************************************/
	
	/**
	 * The key as provided by the constructor.
	 * @return
	 */
	public String getKey();
	
	
	/**
	 * A message for the help screen.
	 * @return the message.
	 */
	public String getHelp();
	
}
