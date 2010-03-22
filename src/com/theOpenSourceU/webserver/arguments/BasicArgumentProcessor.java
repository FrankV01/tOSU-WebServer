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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This is a basic implementation of ArgumentProcessor, suitable
 * for general use. 
 */
class BasicArgumentProcessor implements ArgumentProcessor {

	/** The argument list */
	List< Argument<String> > _values;
	
	/** The processor (program) title */
	String _title;
	
	/** The processor (program) description */
	String _description;
	
	/** 
	 * In addition to constructing a new Argument Processor, this 
	 * parse the <code>String[]</code> apart and converts to the 
	 * <code>List&gt;Argument&gt;String&lt;&lt;</code>
	 */
	public BasicArgumentProcessor( String[] args, List< Argument<String> > Defaults ) {
		setTitle("");
		setDescription("");
		
		_values = Defaults;
		Map<String, Argument<String>> _search = toMap();
		
		String[] sSplit;
		for( String s : args ) {
			
			sSplit = s.split("\\=");
			sSplit[0] = sSplit[0].replace( new StringBuffer("-"), new StringBuffer("") );
			
			if( _search.containsKey(sSplit[0]) ) //Use the map to search for and replace items.
				if( sSplit.length == 2 )
					_search.get(sSplit[0]).setValue(sSplit[1]);
				else {
					String sVal = _search.get(sSplit[0]).getValue();
					Boolean b = Boolean.parseBoolean(sVal);
					_search.get(sSplit[0]).setValue( Boolean.toString(!b.booleanValue()) );
				}
		}
		
		//Convert back to array list because _values should be an array list.
		_values = new ArrayList< Argument<String> >( _search.values() );
	}
	
	
	/* (non-Javadoc)
	 * @see ArgumentProcessor#getHelp()
	 */
	public String getHelp() {
		StringBuilder _sb = new StringBuilder("\n");
		final String template = "  -%s=%s \t%s";
		_sb.append( _title ).append(" - ");
		_sb.append( _description ).append("\n\nSwitches:\n");
		for( Argument<String> a : _values ) {
			_sb.append( String.format(template, a.getKey(), a.getValue(), a.getHelp()) );
			_sb.append("\n"); 
		}
		
		
		return _sb.toString();
	}
	
	
	/* (non-Javadoc)
	 * @see ArgumentProcessor#getValue(java.lang.String)
	 */
	public String getValue( String Key ) {
		String _s = "";
		for( Argument<String> s : _values ) {
			if( Key == s.getKey() ) {
				_s = s.getValue();
				break;
			}
		}
		return _s;
	}

	/* (non-Javadoc)
	 * @see ArgumentProcessor#toMap()
	 */
	public Map< String, Argument<String> > toMap() {
		Map<String, Argument<String>> _map = new HashMap<String, Argument<String>>();
		
		for( Argument<String> a : _values )
			_map.put(a.getKey(), a);
			
		return _map;
	}

	/**
	 * Provides the help meessage
	 * see getHelp
	 */	 
	@Override
	public String toString() {
		return getHelp();
	}

	/** 
	 * Provides the description for the processor. Generally represents
	 * The details a user might need to understand what the parameters 
	 * are for.
	 */ 
	@Override
	public String getDescription() {
		return _description;
	}


	/** 
	 * Returns the title of the processor, which is often the 
	 * sames as the title of the program. Used for formatted 
	 * output and such.
	 */
	@Override
	public String getTitle() {
		return _title;
	}

	
	/** Sets the processor's description */
	@Override
	public void setDescription(String Description) {
		_description = Description;
	}

	/** Sets the processor's title */
	@Override
	public void setTitle(String Title) {
		_title = Title;
	}
	
}
