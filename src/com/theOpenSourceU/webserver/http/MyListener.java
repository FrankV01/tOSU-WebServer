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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.theOpenSourceU.webserver.debugutil.*;

/**
 * Copy your InetServer.java source (or your jokeserver) to a file 
 * called MyListener.java. Modify, and simplify, the code so that 
 * it simply displays everything sent to it on the server console.
 * 
 * <strong>You can start this class by invoking:</strong> <code>java MyWebServer -l</code>
 * 
 * @author FrankV
 * @see <a href="http://condor.depaul.edu/~elliott/435/hw/programs/">http://condor.depaul.edu/~elliott/435/hw/programs/</a>
 */
class MyListener implements Runnable {
	
	Socket _sock;
	DebugPrintable _dPrinter;
	
	/**
	 * Constructor: Accepts the socket and DebugPrintable to 
	 * work with.
	 * 
	 * @param sock The socket to communicate over.
	 * @param DebugPrinter The debug printer to use to output messages.
	 */
	MyListener( Socket sock, DebugPrintable DebugPrinter ) {
		_sock = sock;
		_dPrinter = DebugPrinter;
	}
	
	/** 
	 * Outputs what the client sends to the server's console
	 * and sends a "HTTP 200 OK" message to the client browser
	 * so that it does not hang
	 */
	@Override
	public void run() {
		PrintStream out = null;
		BufferedReader in = null;
		
		try {
			out = new PrintStream( _sock.getOutputStream() );
			in = new BufferedReader( new InputStreamReader(_sock.getInputStream()) );
			
			//Just echo what the client sends.
			while( in.ready() ) {
				System.out.println( in.readLine() );
			}
			
			_dPrinter.printMessage("-> No more data");
			
			out.print( "HTTP/1.1 200 OK\nContent-Length:500\nContent-Type: text/html" );
			out.print("\r\n\r\n");
			out.print("<html><title>My Web Server</title><body><p>Request received.</p></body></html>");
			
			_sock.close();
		} catch( IOException ex ) {
			ex.printStackTrace();
		}
	}

}
