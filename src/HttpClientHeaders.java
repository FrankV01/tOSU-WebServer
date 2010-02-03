import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Represents the HTTP Headers that must be sent back to
 * the client prior to sending the payload.
 * 
 * <em>This is mostly a marker interface. Most of
 * the work is implemented via the List&lt;String&gt; methods</em>
 * @author Frank
 *
 */
public interface HttpClientHeaders extends List<String> {
	
	/**
	 * Should be implemented to produce the required headers.
	 * @return
	 */
	String toString();
}

/**
 * A basic implementation of the HttpClientHeaders
 * interface.
 * @author Frank
 *
 */
class HttpClientHeadersImpl implements HttpClientHeaders {

	List<String> _h;
	
	HttpClientHeadersImpl() {
		_h = new ArrayList<String>();
	}
	
	/**
	 * A factory method to generate a basic 404 Client Header
	 * @param page - The page we are generating the header for.
	 * @return the constructed, ready to <code>toString()</code> HttpClientHeaders object.
	 * @see newSuccessHeaders
	 */
	public static HttpClientHeaders new404ErrorHeaders(HttpContent page) {
		HttpClientHeadersImpl _head = new HttpClientHeadersImpl();
		_head.checkHttpPage( page );
		
		_head.add("HTTP/1.1 404 Page Not Found");
		_head.add( String.format("Content-Length:%d", page.size()) );
		_head.add( String.format("Content-Type: %s", page.type()) );

		return _head;
	}
	
	/**
	 * A factory method to return a basic 500 (internal server error) header.
	 * @param page The page which will inform the end client of the error. 
	 * @return The header, ready to use.
	 * @see new404ErrorHeaders
	 */
	public static HttpClientHeaders new500ErrorHeaders(HttpContent page) {
		HttpClientHeadersImpl _head = new HttpClientHeadersImpl();
		
		_head.checkHttpPage( page );
		
		_head.add("HTTP/1.1 500 Internal Server Error");
		_head.add( String.format("Content-Length:%d", page.size()) );
		_head.add( String.format("Content-Type: %s", page.type()) );
		
		return _head;
	}
	
	/**
	 * A factory method to generate a basic success client header
	 * @param page the Page we are generating the header for. Required for page size.
	 * @return The constructed, ready to <code>toString()</code> HttpClientHeaders object.
	 * @see new404ErrorHeaders
	 */
	public static HttpClientHeaders newSuccessHeaders(HttpContent page) {
		HttpClientHeadersImpl _head = new HttpClientHeadersImpl();
		_head.checkHttpPage( page );
		
		_head.add("HTTP/1.1 200 OK");
		_head.add( String.format("Content-Length:%d", page.size()) );
		_head.add( String.format("Content-Type: %s", page.type()) );
		
		return _head;
	}
	
	private void checkHttpPage( HttpContent page ) {
		if( page == null ) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public boolean add(String arg0) {
		return _h.add(arg0);
	}

	@Override
	public void add(int arg0, String arg1) {
		_h.add(arg0, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends String> arg0) {
		return _h.addAll(arg0);
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends String> arg1) {
		return _h.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		_h.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return _h.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return _h.containsAll(arg0);
	}

	@Override
	public String get(int arg0) {
		return _h.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return _h.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return _h.isEmpty();
	}

	@Override
	public Iterator<String> iterator() {
		return _h.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return _h.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<String> listIterator() {
		return _h.listIterator();
	}

	@Override
	public ListIterator<String> listIterator(int arg0) {
		return _h.listIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return _h.remove(arg0);
	}

	@Override
	public String remove(int arg0) {
		return _h.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return _h.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return _h.retainAll(arg0);
	}

	@Override
	public String set(int arg0, String arg1) {
		return _h.set(arg0, arg1);
	}

	@Override
	public int size() {
		return _h.size();
	}

	@Override
	public List<String> subList(int arg0, int arg1) {
		return _h.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return _h.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return _h.toArray(arg0);
	}
	
	@Override
	public String toString() {
		StringBuilder _sb = new StringBuilder();
		for( String s : this ) {
			_sb.append(s);
			_sb.append("\n");
		}
		
		_sb.append(Terminator());
		return _sb.toString();
	}
	
	private String Terminator() {
		return "\r\n\r\n";
	}
}