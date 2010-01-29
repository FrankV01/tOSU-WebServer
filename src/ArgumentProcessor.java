import java.util.Map;

interface ArgumentProcessor {

	/**
	 * Returns the value as a string.
	 * @param Key The key to find.
	 * @return The setting value as a string.
	 */
	public String getValue(String Key);

	/**
	 * Returns the help message for the programs' argument processor.
	 * This generally would provide the help message for something like "-?"
	 * @return the message ready for display.
	 */
	public String getHelp();

	/**
	 * Provide the settings in a map structure. The settings key 
	 * should be the string and the Argument object (as whole) should
	 * be put in to the value area. 
	 * @return The argument settings as in current state.
	 */
	public Map<String, Argument<String>> toMap();

	
	/**
	 * <i>We may want to refactor this out but for now this is how it is</i>
	 * Use to set the program title.
	 */
	public void setTitle(String Title);
	
	/**
	 * <i>We may want to refactor this out but for now this is how it is</i>
	 * The title of the application we are processing arguments for.
	 * @return The application title.
	 */
	public String getTitle();
	
	/**
	 * <i>We may want to refactor this out but for now this is how it is</i>
	 * Things like what it does, copy right or pre-switch information.
	 * @return The text.
	 */
	public String getDescription();
	
	/**
	 * <i>We may want to refactor this out but for now this is how it is</i>
	 * Sets the description. Things like what it does, copy right or pre-switch information.
	 * @param Description
	 */
	public void setDescription(String Description);
	
}