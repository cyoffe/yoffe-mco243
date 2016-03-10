package yoffe.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompilerMain {

	public static void main(String[] args) {
		Compiler compiler = new Compiler();
		BufferedReader input;
		try {
			// input = new BufferedReader(new InputStreamReader(System.in));
			input = new BufferedReader(new FileReader(new File(
					"./assemblyLanguage.txt")));
			String line;
			while ((line = input.readLine()) != null) {
				compiler.setNextInstruction(line);
			}

			input.close();

			System.out.println(compiler.getInstruction());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
