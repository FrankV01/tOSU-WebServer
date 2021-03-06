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

package com.theOpenSourceU.webserver.debugutil;

/**
 * Instances of classes that implement this interface
 * are used to provide debug messages. There is no promise that
 * a print request will actually go to the console. It is up to the 
 * implementation to handle the output
 * @author Frank
 *
 */
public interface DebugPrintable {
	/** Package's Default implementation to report errors only. Messages are ignored */
	public static DebugPrintable ERRORONLY = new NoDebugPrinter();
	
	/** Package's Default implementation. */
	public static DebugPrintable DEFAULTIMPL = new DebugPrinter();

	/**
	 * Print or record a informational message. 
	 * @param Message The message
	 */
	void printMessage(String Message);
	
	/**
	 * Print or record a error message.
	 * @param Message The message
	 */
	void printError(String Message);
}
