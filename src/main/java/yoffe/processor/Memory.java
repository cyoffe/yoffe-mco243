package yoffe.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Memory {
	private BufferedReader reader;
	private ArrayList<String> instructions;
	private int numInstruc;

	public Memory(String filename) throws IOException {

		reader = new BufferedReader(new FileReader(filename));
		String line;
		instructions = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			instructions.add(line);
		}
		numInstruc = 0;
	}

	public String getInstruction() throws Exception {
		if (numInstruc < instructions.size()) {
			return instructions.get(numInstruc++);
		} else {
			throw new Exception();
		}
	}

	public void setInstruction(char[] instruction) {
	}

	public int getInstructionsSize() {
		return instructions.size();
	}
}
