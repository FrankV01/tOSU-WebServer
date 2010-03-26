package com.theOpenSourceU.webserver.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public final class HttpFileTests {
	
	@Test
	public void testConstructor() {
		try {
			new HttpFile(null);
			Assert.fail();
		} 
		catch( IllegalArgumentException e ) { }
		catch( NullPointerException e ) { } //Also acceptable.
		
		try {
			new HttpFile("");
			Assert.fail();
		} catch( IllegalArgumentException e ) { } 
		
		//Any file should be acceptable, as long as there is "content".
		new HttpFile("myfile.html");
		new HttpFile("myfile.css");
		new HttpFile("myfile.png");
		new HttpFile("myfile");
		new HttpFile("myfile.bin");
		new HttpFile("myfile.exe");
	}
	
	@Test
	public void testGetName() {
		Assert.assertEquals("myfile.html", new HttpFile("myfile.html").getName());
		Assert.assertEquals("myfile.html", new HttpFile("myfile.html?parm=true").getName());
		Assert.assertEquals("myfile.html", new HttpFile("myfile.html?parm=true&foo=bar&url=theopensourceu.com").getName());
	}
	
	@Test
	public void testGetArguments() {
		
		Assert.assertEquals( 0, new HttpFile("myfile.html").getArguments().size() );
		
		final Map<String, String> _expected = new HashMap<String, String>();
		_expected.put("parm", "true");
		Assert.assertEquals( _expected, new HttpFile("myfile.html?parm=true").getArguments() );
		
		//Add new items.
		_expected.put("foo", "bar");
		_expected.put("url", "theopensourceu.com");
		Assert.assertEquals( _expected, new HttpFile("myfile.html?parm=true&foo=bar&url=theopensourceu.com").getArguments() );
	}
}
