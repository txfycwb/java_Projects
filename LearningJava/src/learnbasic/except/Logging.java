package learnbasic.except;
import java.io.IOException;
import java.util.logging.*;

public class Logging {
	private static final Logger myLogger = Logger.getLogger("mycompany.myapp");
	private static final Handler myConsolH = new ConsoleHandler();
	
	public static void main(String[] args) throws SecurityException, IOException {
		// TODO Auto-generated method stub
		myLogger.setLevel(Level.ALL);
		myLogger.setUseParentHandlers(false);
		myLogger.addHandler(myConsolH);
		//myLogger.addHandler(new FileHandler());
		myConsolH.setLevel(Level.ALL);
        myLogger.info("start process...");
        myLogger.warning("memory is running out...");
        myLogger.fine("ignored.");
        myLogger.severe("process will be terminated...");
	}

}
