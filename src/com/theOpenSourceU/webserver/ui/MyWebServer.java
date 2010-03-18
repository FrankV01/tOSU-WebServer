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

package com.theOpenSourceU.webserver.ui;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.theOpenSourceU.webserver.arguments.*;
import com.theOpenSourceU.webserver.io.*;
import com.theOpenSourceU.webserver.http.*;

/**
 * The main entry point for the program.
 * Please see comments.html for information 
 * on how to start this program. 
 * 
 * You can also use: <code>java MyWebServer -?</code>
 * @author Frank
 *
 */
public class MyWebServer {
	
	/**
	 * Different modes of the server.
	 * @author Frank
	 *
	 */
	private enum ServerMode {
		Listener,
		WebServer
	};
	
	private static ServerMode _mode;
	
	private static int _port;
	private static int q_len = 6;
	private static final String _serverName = "Web Server";
	private static final String _description = "A basic web server implementation.";
	private static String _pathToServeFrom;
	private static DebugPrintable _dPrinter;
	private static ArgumentProcessor _args;
	
	
	/**
	 * Main program entry point. It starts the web server with
	 * the given arguments.
	 * @param args The arguments to start with.
	 */
	public static void main(String[] args) {
		
		processArguments( args );
		
		try {
			ServerSocket servsock = new ServerSocket( _port, q_len );
			_dPrinter.printMessage( "Server Starting" );
			
			while( true ) {
				Socket sock = servsock.accept();
				if( _mode == ServerMode.WebServer )
					WorkerFactory.newServerWorker(sock, _pathToServeFrom, _dPrinter).start();
				else
					WorkerFactory.newListener(sock, _dPrinter).start();
				
				
			}
		} catch( IOException ie ) {
			System.out.println( "Error starting Web Server!" );
		}
	}

	private static void processArguments(String[] args) {
		List<Argument<String>> _defaults = ArgumentFactory.newArgumentList();
		_defaults.add( ArgumentFactory.newArgument( "?", "false", "Show Help and exit (Alt. -?).") );
		_defaults.add( ArgumentFactory.newArgument( "p", "80", "The port to serve the Client peice on.") );
		_defaults.add( ArgumentFactory.newArgument( "d", "false", "Enable Debug Mode (Alt. -d)") );
		_defaults.add( ArgumentFactory.newArgument( "l", "false", "Use 'MyListner' which echo's requests to the console. (Alt. -l)"));
		_defaults.add( ArgumentFactory.newArgument( "f", ".", "The directory to serve files from." ));
		
		_args = ArgumentFactory.newArgumentProcessor(args, _defaults);
		
		_args.setTitle( _serverName );
		_args.setDescription( _description );
		
		if( Boolean.parseBoolean(_args.getValue("?")) ) {
			System.out.println( _args.getHelp() );
			System.exit(0);
		}
		
		if( Boolean.parseBoolean(_args.getValue("d")) )
			_dPrinter = DebugPrintable.DEFAULTIMPL;
		else
			_dPrinter = DebugPrintable.ERRORONLY;
		
		
		String tmpPort = _args.getValue("p");
		_port = Integer.parseInt( tmpPort );
		
		if( Boolean.parseBoolean(_args.getValue("l")) )
			_mode = ServerMode.Listener;
		else
			_mode = ServerMode.WebServer;
		
		_pathToServeFrom = _args.getValue("f");
		validatePathToServeFrom();
		
		// If in debug mode, lets output the loaded settings. 
		_dPrinter.printMessage( ArgumentInfoFormatter.getArgumentInfo(_args) );
	}
	
	private static void validatePathToServeFrom() {
		File _f = new File(_pathToServeFrom);
		if( (!_f.isDirectory()) || (!_f.exists()) )
			throw new IllegalArgumentException( "Given directory is invalid" );
	}
}


