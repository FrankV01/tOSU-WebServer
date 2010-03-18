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

class ArgumentInfoFormatter {
	public static String getArgumentInfo(ArgumentProcessor toFormat) {
		StringBuilder _sb = new StringBuilder();
		
		_sb.append( "\nDebug mode is enabled. Loaded settings: \n" );
		_sb.append( "  Opt.\tCurr. Setting\tInfo\n");
		_sb.append( "----------------------------------------------\n" );
		for( Argument<String> a : toFormat.toMap().values() ) {
			_sb.append( String.format(" -%s\t%s\t\t%s\n", a.getKey(), a.getValue(), a.getHelp()) );
		}
		
		_sb.append( "\n" );
		
		return _sb.toString();
	}
}
