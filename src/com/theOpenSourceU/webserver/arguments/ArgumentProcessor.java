/**
 * tOSU-WebServer | An educational HTTP Server
 * Copyright (C) 2010  Frank Villasenor <Frank@TheOpenSourceU.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.theOpenSourceU.webserver.arguments;

import java.util.Map;

public interface ArgumentProcessor {

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