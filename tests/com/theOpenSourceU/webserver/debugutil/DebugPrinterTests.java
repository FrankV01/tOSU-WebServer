package com.theOpenSourceU.webserver.debugutil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class DebugPrinterTests {
	
	ByteArrayOutputStream _out = null;
	ByteArrayOutputStream _err = null;
	DebugPrintable _p = null;
	
	@Before 
	public void setUp() {
		_out = new ByteArrayOutputStream();
		_err = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(_out));
		System.setErr(new PrintStream(_err));
		_p = new DebugPrinter();
	}
	
	@After 
	public void tearDown() {
		_out = null;
		_err = null;
		_p = null;
	}
	
	@Test
	public void printMsg() {
		_p.printMessage("test");
		
		final String output = _out.toString().trim();
		final String errOutput = _err.toString().trim();
		
		Assert.assertTrue( output.contains("test") );
		Assert.assertEquals("", errOutput);
	}
	
	@Test
	public void printErr() {
		_p.printError("test-error");
		
		final String output = _out.toString();
		final String errOutput = _err.toString();
		
		Assert.assertEquals("", output);
		Assert.assertTrue( errOutput.contains("test-error") );
	}
	
}
