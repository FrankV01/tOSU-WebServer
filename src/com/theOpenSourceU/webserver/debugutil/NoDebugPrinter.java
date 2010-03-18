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
 * An instance of a DebugPrintable that ignore
 * informational messages. Generally used when
 * in non-debug mode.
 * @author Frank
 *
 */
final class NoDebugPrinter extends DebugPrinter implements DebugPrintable  {
	
	/**
	 * Ignore the message; don't print anything
	 */
 	@Override //Intentionally does nothing.
	public void printMessage(String Message) { }
}