import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {
	PrintWriter receipt;
	
	public Printer() throws FileNotFoundException {
		receipt = new PrintWriter(new File("./receipt.txt"));
	}
	
	public void print(String time, String transaction, int amount) throws IOException {
		
		receipt.println(time + " " + transaction + " " + amount);
		receipt.close();
	}
}