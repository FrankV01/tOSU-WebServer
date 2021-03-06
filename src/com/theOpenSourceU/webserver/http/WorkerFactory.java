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

package com.theOpenSourceU.webserver.http;

import java.net.Socket;

import com.theOpenSourceU.webserver.debugutil.*;


/**
 * Provides new instances of different worker threads
 * for the servers.
 * @author Frank
 *
 */
final public class WorkerFactory {

	/**
	 * Provides new server worker threads which handle
	 * admin related stuff. Such as the ability to change
	 * type of server or to shut down the server.
	 * @param s The socket to communicate over
	 * @return The instance.
	 * @see HttpWorker
	 */
	public static Thread newServerWorker(Socket s, String PathToServeFrom, DebugPrintable DebugPrinter) {
		return new HttpWorker(s, PathToServeFrom, DebugPrinter);
	}
	
	/**
	 * Provides a new listern thread which allows the server
	 * to output what the client sends to the server's console
	 * 
	 * @param s The socket to communicate over.
	 * @return the instance to use.
	 * @see MyListener
	 */
	public static Thread newListener(Socket s, DebugPrintable DebugPrinter) {
		return new Thread(new MyListener(s, DebugPrinter));
	}
}
