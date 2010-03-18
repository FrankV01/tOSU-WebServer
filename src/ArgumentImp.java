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

public class ArgumentImp<T> implements Argument<T> {

	String _key;
	T _value;
	T _default;
	String _helpMsg;
	
	ArgumentImp( String Key, T Default, String HelpMessage ) {
		_key = Key;
		_value = Default;
		_default = Default;
		_helpMsg = HelpMessage;
	}
	
	@Override
	public String getHelp() {
		return _helpMsg;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public T getValue() {
		return _value;
	}


	@Override
	public void setValue(T value) {
		_value = value;
		
	}
	
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


