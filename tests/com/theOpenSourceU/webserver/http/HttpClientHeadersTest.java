package com.theOpenSourceU.webserver.http;

import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test the current implementation of the interface which is HttpClientHeadersImpl
 * <em>Unsure of how to structure this class</em>
 * @author Frank
 *
 */
public class HttpClientHeadersTest {

	/**
	 * When toString is called on the interface, we expect to get 
	 * the client headers back as:
	 *  
	 * HTTP/1.1 200 OK
 	 * Date: Mon, 23 May 2005 22:38:34 GMT
 	 * Server: xxxxxxxxxxxxxxxxxxxxxxx
  	 * Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
  	 * Content-Length: 438
  	 * Content-Type: text/html; charset=UTF-8
  	 * 
  	 * What we are not testing is necessarily the factory methods, just the correct 
  	 * structure with the correct line breaks.
  	 * 
  	 * <em>Note:</em> This test is actually redundant but for the time being I'm hesitant to remove it.
  	 * 
	 */
	@Test
	public void testToString() {
		
		// Note: Should be the correct implementation which in this case is HttpClientHeadersImpl.
		HttpClientHeaders i = HttpClientHeadersImpl.newSuccessHeaders(new InternalMockHttpPage());
		
		final String _rslt = i.toString();
		
		// Should end with
		_rslt.startsWith("HTTP/1.1");
		_rslt.contains("OK/r/n");
		_rslt.contains("Content-Length: 5/r/n");
		_rslt.contains( String.format("Content-Type: %s/r/n", new MockHttpPage().type()) );
		_rslt.endsWith("/r/n/r/n");
	}
	
	private class InternalMockHttpPage implements HttpContent {

		@Override
		public InputStream generate() { return null; }

		@Override
		public String render() throws UnsupportedOperationException { return ""; }

		@Override
		public int size() { return 5; }

		@Override
		public String type() { return "text/html"; }
		
	}
	
}
