
public interface DebugPrintable {
	void printMessage(String Message);
	void printError(String Message);
	
	//boolean getDebugMode();
}


// ***********************************************************
// Package Private implementation of the above.

class debugPrinter implements DebugPrintable {
	
	debugPrinter() { }

	@Override
	public void printMessage(String Message) {
		System.out.println( Message );
	}

	@Override
	public void printError(String Message) {
		System.err.print( " [**Error**] " );
		System.err.println( Message );
	}

}

class noDebugPrinter extends debugPrinter implements DebugPrintable  {
 	@Override //Intentionally does nothing.
	public void printMessage(String Message) { }
	
}