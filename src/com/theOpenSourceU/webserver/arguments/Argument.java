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

/**
 * Interface to an Argument to be used by argument Processors.
 * @author Frank
 *
 * @param <T> The type to expect.
 */
public interface Argument<T> {
	
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
