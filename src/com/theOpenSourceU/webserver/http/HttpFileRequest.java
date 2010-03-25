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

import java.util.Map;

/**
 * An interface to deal with HTTP file requests.
 * Generally, this interface is implemented on a sub
 * class of File (but this doesn't have to be the case)
 * @author Frank
 *
 */
public interface HttpFileRequest {
	/** The name of the file being requested. No Arguments included */
	String getName();
	
	/**
	 * Gets the arguments from the Http Request
	 * @return The map of arguments or empty map if no arguments.
	 */
	Map<String, String> getArguments();
}

