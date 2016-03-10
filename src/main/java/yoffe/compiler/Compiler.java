package yoffe.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {
	private String instruction;

	public Compiler() {
		instruction = "";
	}

	public void setNextInstruction(String instruction) {
		String hex;
		String in = instruction.substring(0, 3).trim();
		switch (in) {
		case "LD": // LD
			this.instruction += "0";
			hex = decToHex(Integer.parseInt(instruction.substring(3).trim()));
			this.instruction += hex;
			break;
		case "ST":
			this.instruction += "1";
			hex = decToHex(Integer.parseInt(instruction.substring(3).trim()));
			this.instruction += hex;
			break;
		case "SWP":
			this.instruction += "2";
			break;
		case "ADD":
			this.instruction += "3";
			break;
		case "INC":
			this.instruction += "4";
			break;
		case "DEC": // DEC
			this.instruction += "5";
			break;
		case "BZ":
			this.instruction += "6";
			hex = decToHex(Integer.parseInt(instruction.substring(3).trim()));
			this.instruction += hex;
			break;
		case "BR":
			this.instruction += "7";
			hex = decToHex(Integer.parseInt(instruction.substring(3).trim()));
			this.instruction += hex;
			break;
		case "STP":
			this.instruction += "8";
			break;
		case "DAT":
			this.instruction += instruction.substring(5);
			break;
		}

	}

	public String getInstruction() {
		return this.instruction;

	}

	private String decToHex(int dec) {
		String s = Integer.toHexString(dec).toUpperCase();
		if (s.length() == 1) {
			return ("0" + s);
		} else {
			return s;
		}
	}

}
