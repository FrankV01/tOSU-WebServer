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
		exceptionIfNull(Key);
		exceptionIfNull(Default);
		exceptionIfNull(HelpMessage);
		
		_key = Key;
		_value = Default;
		_default = Default;
		_helpMsg = HelpMessage;
	}
	
	/** Throws an exception of objToCheck is null */
	private void exceptionIfNull( Object objToCheck ) {
		if( objToCheck == null ) throw new IllegalArgumentException();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_default == null) ? 0 : _default.hashCode());
		result = prime * result
				+ ((_helpMsg == null) ? 0 : _helpMsg.hashCode());
		result = prime * result + ((_key == null) ? 0 : _key.hashCode());
		result = prime * result + ((_value == null) ? 0 : _value.hashCode());
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		//Warning here is suppressed. Doesn't seem to be a way to get around it.
		ArgumentImp<T> other = (ArgumentImp<T>) obj;
		
		
		if (_default == null) {
			if (other._default != null)
				return false;
		} else if (!_default.equals(other._default))
			return false;
		
		if (_helpMsg == null) {
			if (other._helpMsg != null)
				return false;
		} else if (!_helpMsg.equals(other._helpMsg))
			return false;
		
		if (_key == null) {
			if (other._key != null)
				return false;
		} else if (!_key.equals(other._key))
			return false;
		
		if (_value == null) {
			if (other._value != null)
				return false;
		} else if (!_value.equals(other._value))
			return false;
		
		return true;
	}
}


