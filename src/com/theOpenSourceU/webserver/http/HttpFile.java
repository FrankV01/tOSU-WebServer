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

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.theOpenSourceU.webserver.io.*;

//Should use factory but... meh.

/**
 * A basic implementation of HttpFileRequest. Simple
 * provides some basic handling to parse a request
 * with a query string such as getting the arguments
 * or getting the filename without the arguments
 */
class HttpFile extends File implements HttpFileRequest {

	/**
	 * Recommended serial id.
	 */
	private static final long serialVersionUID = 1713316465309825030L;

	public HttpFile(String arg0) {
		super(arg0);
	}

	@Override
	public String getName() {
		if( super.getName().contains("?") )
			return super.getName().split("\\?")[0];
		else
			return super.getName();
	}
	
	@Override
	public Map<String, String> getArguments() {
		Map<String, String> _map = new HashMap<String, String>();
		
		if( super.getName().contains("?") ) {
			String _argsOnly = super.getName().split("\\?")[1];
			String[] _argsArr = _argsOnly.split("\\&");
			
			for( String itm : Arrays.asList(_argsArr) ) {
				String[] itm2 = itm.split("\\=");
				_map.put(itm2[0], itm2[1]);
			}
		}
		
		return _map;
	}
	
}