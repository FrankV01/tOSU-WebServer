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

/**
 * A default 'Argument' implementation. The class should be 
 * suitable for general purpose usage. The class implements
 * all required interface peices as described the interface. 
 * All implementations have been kept as simple as possible.
 * 
 * TODO: http://bitbucket.org/frankv01/tosu-webserver/issue/2/argumentimp-should-implement-hashcode-and
 */ 
public class ArgumentImp<T> implements Argument<T> {
	/** Holds the key which is the reference value. */
	final String _key;
	
	/** Holds the value to be stored for the argument */
	T _value;
	
	/** Holds the default value for the argument. This should change */
	final T _default;
	
	/** Holds the help message for the argument. */
	final String _helpMsg;
	
	/** 
	 * Default constructor accepts the key, default and help message.
	 * The default value is used for the default field and value field. 
	 */
	ArgumentImp( String Key, T Default, String HelpMessage ) {
		_key = Key;
		_value = Default;
		_default = Default;
		_helpMsg = HelpMessage;
	}
	
	/** Returns the help message */
	@Override
	public String getHelp() {
		return _helpMsg;
	}

	/** Returns the argument key */
	@Override
	public String getKey() {
		return _key;
	}

	/** Returns the current value */
	@Override
	public T getValue() {
		return _value;
	}


	/** Sets the current value. */
	@Override
	public void setValue(T value) {
		_value = value;
		
	}
	
	/** Provides a string representation suitable for simple console output */
	@Override
	public String toString() {
		StringBuilder _sb = new StringBuilder("-");
		
		_sb.append( getKey() );
		_sb.append( "\t" );
		_sb.append( getHelp() );
		_sb.append( " - Default: " ).append( _default );
		
		return _sb.toString();
	}

}


