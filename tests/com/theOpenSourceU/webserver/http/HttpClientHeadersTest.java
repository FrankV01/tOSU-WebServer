package com.theOpenSourceU.webserver.http;

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
	 */
	@Test
	public void testToString() {
		//We are expecting a certain format back and we should check for that
		Assert.fail();
	}
	
}
