package yoffe.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Memory {
	/*
	 * read file for instructions getInstruction method to read next instruction
	 */

	private BufferedReader reader;
	private char[] instruction;
	private ArrayList<String> instructions;
	private int numInstruc;

	public Memory(String filename) throws IOException {

		reader = new BufferedReader(new FileReader(filename));
		String line;
		instructions = new ArrayList<String>();
		int count = 1;
		while ((line = reader.readLine()) != null) {
			instructions.add(line);
			//System.out.println(count + "---" +line);
			//count++;
		}
		numInstruc = 0;
	}

	public String getInstruction() throws Exception {
		if (numInstruc < instructions.size()) {
			return instructions.get(numInstruc++);
		}else{
			throw new Exception();
		}
	}

	public void setInstruction(char[] instruction) {
		this.instruction = instruction;
	}
	
	public int getInstructionsSize(){
		return instructions.size();
	}
}
