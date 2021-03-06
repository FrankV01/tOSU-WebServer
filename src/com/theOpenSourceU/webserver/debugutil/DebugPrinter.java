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
 * A basic implementation of DebugPrintable. This 
 * instance prints to the console via std. output 
 * or error.
 */
class DebugPrinter implements DebugPrintable {
	
	public DebugPrinter() { }

	/**
	 * Prints the message to the std out.
	 * {@inheritDoc}
	 */
	@Override
	public void printMessage(String Message) {
		System.out.println( Message );
	}

	/**
	 * Prints the message to the std. error.
	 * {@inheritDoc}
	 */
	@Override
	public void printError(String Message) {
		System.err.print( " [**Error**] " );
		System.err.println( Message );
	}

}