import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Should use factory but... meh.

/**
 * A basic implementation of HttpFileRequest. Simple
 * provides some basic handling to parse a request
 * with a query string such as getting the arguments
 * or getting the filename without the arguments
 */
class HttpFile extends File implements HttpFileRequest {

	/**
	 * Recommended serial id.
	 */
	private static final long serialVersionUID = 1713316465309825030L;

	public HttpFile(String arg0) {
		super(arg0);
	}

	@Override
	public String getName() {
		if( super.getName().contains("?") )
			return super.getName().split("\\?")[0];
		else
			return super.getName();
	}
	
	@Override
	public Map<String, String> getArguments() {
		Map<String, String> _map = new HashMap<String, String>();
		
		if( super.getName().contains("?") ) {
			String _argsOnly = super.getName().split("\\?")[1];
			String[] _argsArr = _argsOnly.split("\\&");
			
			for( String itm : Arrays.asList(_argsArr) ) {
				String[] itm2 = itm.split("\\=");
				_map.put(itm2[0], itm2[1]);
			}
		}
		
		return _map;
	}
	
}