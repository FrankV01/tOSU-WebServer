import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class BasicArgumentProcessor implements ArgumentProcessor {

	List< Argument<String> > _values;
	
	String _title;
	String _description;
	
	public BasicArgumentProcessor( String[] args, List< Argument<String> > Defaults ) {
		setTitle("");
		setDescription("");
		
		_values = Defaults;
		Map<String, Argument<String>> _search = toMap();
		
		String[] sSplit;
		for( String s : args ) {
			
			sSplit = s.split("\\:");
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
		final String template = "  -%s:%s \t%s";
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

	@Override
	public String toString() {
		return getHelp();
	}


	@Override
	public String getDescription() {
		return _description;
	}


	@Override
	public String getTitle() {
		return _title;
	}


	@Override
	public void setDescription(String Description) {
		_description = Description;
	}


	@Override
	public void setTitle(String Title) {
		_title = Title;
	}
	
}
