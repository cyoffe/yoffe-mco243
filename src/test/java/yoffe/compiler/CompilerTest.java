package yoffe.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class CompilerTest {

	@Test
	public void compilerTest() {
		Compiler c = new Compiler();
		BufferedReader input;
		try {
			// input = new BufferedReader(new InputStreamReader(System.in));
			input = new BufferedReader(new FileReader(new File(
					"./assemblyLanguage.txt")));
			String line;
			while ((line = input.readLine()) != null) {
				c.setNextInstruction(line);
			}

			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(
				"040563B14004220FF31FF041320FE31FE00C2042314200032041314170080000F03000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001",
				c.getInstruction());
	}
}
