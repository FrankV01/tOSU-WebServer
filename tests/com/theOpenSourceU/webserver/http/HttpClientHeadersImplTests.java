package com.theOpenSourceU.webserver.http;

import java.util.List;

import junit.framework.Assert;

import org.junit.*;

/**
 * Unit tests for HttpClientHeadersImpl
 * 
 * <em>Note:</em>  We're going to omit tests related to the List<String> interface.
 * 	The reason is because another class "backs" implementation, and testing
 *  it is not productive. It'd be writing tests to write tests for the time 
 *  being.
 *  
 * @author Frank
 *
 */
public class HttpClientHeadersImplTests {
	HttpClientHeadersImpl i;
	
	
	@Before
	public void setUp() { }
	@After
	public void tearDown() { i = null; }
	
	/**
	 * Expect that the list will have an instance that is empty. Nothing more.
	 */
	@Test
	public void testConstructor() {
		i = new HttpClientHeadersImpl();
		Assert.assertNotNull(i);
		
		Assert.assertNotNull(i._h);
		
		Assert.assertTrue( i._h instanceof List<?> );
		Assert.assertTrue( i._h.size() == 0 );
	}
	
	@Test 
	public void testNew404ErrorHeaders() {
		i = (HttpClientHeadersImpl)HttpClientHeadersImpl.new404ErrorHeaders(new MockHttpPage());
		
		Assert.assertTrue( i._h.size() > 0 );
		
		final String _statusLine = i._h.get(0); //Status line.
		
		Assert.assertTrue( _statusLine.contains("HTTP/1.1") );
		Assert.assertTrue( _statusLine.contains("404") );
		Assert.assertTrue( _statusLine.contains("Page Not Found") );
	}
	
	@Test
	public void testNew500ErrorHeaders() {
		i = (HttpClientHeadersImpl)HttpClientHeadersImpl.new500ErrorHeaders(new MockHttpPage());
		
		Assert.assertTrue( i._h.size() > 0 );
		
		final String _statusLine = i._h.get(0); //Status line.
		
		Assert.assertTrue( _statusLine.contains("HTTP/1.1") );
		Assert.assertTrue( _statusLine.contains("500") );
		Assert.assertTrue( _statusLine.contains("Internal Server Error") );
	}
	
	@Test
	public void testNewSuccessHeaders() {
		// What do we expect out of success headers. Probably the first
		// row is the most important, so we expect to have a status 200 OK in there.
		
		i = (HttpClientHeadersImpl)HttpClientHeadersImpl.newSuccessHeaders(new MockHttpPage());
		
		Assert.assertTrue( i._h.size() > 0 );
		
		final String _statusLine = i._h.get(0); //Status line.
		
		Assert.assertTrue( _statusLine.contains("HTTP/1.1") );
		Assert.assertTrue( _statusLine.contains("200") );
		Assert.assertTrue( _statusLine.contains("OK") );
	}
	
	//
	//Need to ensure it is the proper format for the client browser. This includes the
	// proper line breaks between each item and the double line breaks at the end.
	@Test
	public void testToString() {
		
		i = (HttpClientHeadersImpl)HttpClientHeadersImpl.newSuccessHeaders(new MockHttpPage());
		
		final String _rslt = i.toString();
		
		// Should end with
		_rslt.startsWith("HTTP/1.1");
		_rslt.contains("OK/r/n");
		_rslt.contains("Content-Length: 0/r/n");
		_rslt.contains( String.format("Content-Type: %s/r/n", new MockHttpPage().type()) );
		_rslt.endsWith("/r/n/r/n");
	}
}
