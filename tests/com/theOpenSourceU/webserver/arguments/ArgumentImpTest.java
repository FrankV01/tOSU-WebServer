package com.theOpenSourceU.webserver.arguments;


import junit.framework.Assert;

import org.junit.*;

public class ArgumentImpTest {
	
	ArgumentImp<String> _argStr;
	ArgumentImp<Integer> _argInt;
	
	@Before
	public void setUp() {
		_argStr = new ArgumentImp<String>("test","defultTest", "helpMessage");
		_argInt = new ArgumentImp<Integer>("test2", new Integer(500), "helpMessage2");
	}

	@After
	public void tearDown() {
		_argStr = null;
		_argInt = null;
	}

	@Test
	public void testConstructor() {
		try {
			new ArgumentImp<String>(null,null, null);
			Assert.fail();
		} catch( IllegalArgumentException e ) { }
		
		try {
			new ArgumentImp<String>(null,"default", "help");
			Assert.fail();
		} catch( IllegalArgumentException e ) { }
		
		try {
			new ArgumentImp<String>("k",null, "help");
			Assert.fail();
		} catch( IllegalArgumentException e ) { }
		
		try {
			new ArgumentImp<String>("k","default", null);
			Assert.fail();
		} catch( IllegalArgumentException e ) { }
	}
	
	@Test
	public void testEquals1() {
		Assert.fail("todo");
	}
	
	@Test
	public void testEquals2() {
		Assert.fail("todo");
	}
	
	@Test
	public void testHashCode() {
		Assert.fail("todo");
	}
	
	@Test
	public void testGetHelp() {
		Assert.assertEquals(_argStr._helpMsg, _argStr.getHelp());
		Assert.assertEquals(_argInt._helpMsg, _argInt.getHelp());
	}
	
	@Test
	public void testGetKey() {
		Assert.assertEquals(_argStr._key, _argStr.getKey());
		Assert.assertEquals(_argInt._key, _argInt.getKey());
	}
	
	@Test
	public void testGetValue() {
		Assert.fail("todo");
	}
	@Test
	public void testSetValue() {
		Assert.fail("todo");
	}
	
	@Test
	public void testToString() {
		Assert.fail("todo");
	}
}
