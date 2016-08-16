package java8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.*;

@FunctionalInterface
interface BufferedReaderProcessor<T> {

	// Define a function which can be used to get a line of file
	String process(BufferedReader b) throws IOException;
}

public class FunctionalInterfaceImpl {

	public static String processFile(BufferedReaderProcessor<BufferedReader> p, String type)
			throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("C:\\Users\\allenwong\\workspace\\TestJava8\\src\\data.txt")))) {
			if (!"ALL".equals(type))
				return p.process(br);
			else
				return p.process(br);
		}
	}

	public static void main(String[] args) {
		try {

			// Define the behaviour of the interface by Lambda Exp.
			String oneline = processFile((BufferedReader br) -> br.readLine(), "ALL");
			out.println(oneline);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
