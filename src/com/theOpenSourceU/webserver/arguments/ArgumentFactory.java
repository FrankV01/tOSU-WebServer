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

import java.util.ArrayList;
import java.util.List;

/**
 * Provides package-default instances of the Argument<T> interface. 
 * These instances should be suitable for general use and should be 
 * favored over custom implementations.
 */
public class ArgumentFactory {
	/**
	 * Returns a String-backed Argument instance. (Usage should be favored)
	 * 
	 * @see ArgumentImp<T>
	 */
	public static Argument<String> newArgument( String Key, String Default, String HelpMessage ) {
		return new ArgumentImp<String>( Key, Default, HelpMessage );
	}
	
	/*
	 * Returns a collection that implements the list interface to hold
	 * items of Argument<String> type.
	 * 
	 * @see Argument<T>
	 */
	public static List<Argument<String>> newArgumentList() {
		return new ArrayList< Argument<String> >();
	}
	
	/**
	 * Provides a Argument Processor instance which can parse arguments
	 * suitable for all package-default implementations.
	 * 
	 * @see ArgumentProcessor
	 * @see BasicArgumentProcessor
	 */
	public static ArgumentProcessor newArgumentProcessor( String[] args, 
			List<Argument<String>> PrefilledDefaults ) {
		return new BasicArgumentProcessor(args, PrefilledDefaults);
	}
}