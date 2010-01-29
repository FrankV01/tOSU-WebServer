import java.util.ArrayList;
import java.util.List;


public class ArgumentFactory {
	public static Argument<String> newArgument( String Key, String Default, String HelpMessage ) {
		return new ArgumentImp<String>( Key, Default, HelpMessage );
	}
	
	public static List<Argument<String>> newArgumentList() {
		return new ArrayList< Argument<String> >();
	}
	
	public static ArgumentProcessor newArgumentProcessor( String[] args, 
			List<Argument<String>> PrefilledDefaults ) {
		return new BasicArgumentProcessor(args, PrefilledDefaults);
	}
}