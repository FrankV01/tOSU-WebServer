package com.theOpenSourceU.webserver.debugutil;

import org.junit.Test;

import junit.framework.Assert;

public class DebugPrinterTests {
	
	
	
	@Test
	public void fail() {
		Assert.fail();
	}
	
	@Test 
	public void success() {
		Assert.assertEquals(1,1);
	}
}
