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

import java.util.Arrays;


/**
 * Contains String Utilities
 * @author Frank
 *
 */
class StringUtils {
	
	/**
	 * Repeats a single character.
	 * @param Char
	 * @param count How many times. 
	 * @return The string of repeated chars. 
	 */
	public static String repeat(char Char, int count ) {
		if( count <= 0 )
			return "";
		
		char[] chars = new char[count];
		Arrays.fill(chars, Char);
		return new String(chars);
	}
}
