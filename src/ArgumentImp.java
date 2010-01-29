
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
		_sb.append( "	" );
		_sb.append( getHelp() );
		_sb.append( " - Default: " ).append( _default );
		
		return _sb.toString();
	}

}


