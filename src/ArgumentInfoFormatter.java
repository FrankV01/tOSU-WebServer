
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
