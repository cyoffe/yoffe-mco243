package yoffe.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Microprocessor {
	private char accumA, accumB;
	private Memory memory;
	private StringBuilder builder;
	private int count;

	public Microprocessor(char[] instruction) {
		accumA = accumB = '0';
		memory = new Memory();
		memory.setInstruction(instruction);
		/*
		 * int instrucNum = 0; while (instrucNum < memory.getInstructionsSize())
		 * { String s = memory.getInstruction(); readInstruction(instrucArray =
		 * s.toCharArray());
		 * 
		 * s = new String(instrucArray); System.out.println(s);
		 * 
		 * instrucNum++; }
		 */
		readInstruction();

	}

	public void readInstruction() {
		char[] in = memory.getInstruction();
		String s;
		count = 0;
		char instruction, hex;
		while (count < in.length && (in[count + 1]) != '8') {
			instruction = in[count];

			switch (instruction) {
			case '0':
				// LD: (3) load A with the contents of memory at the specified
				// argument
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();

				int i = hexToDec(s);
				accumA = in[i];
				count += 3;

				break;
			case '1':
				// ST: (3) write the contents of A to the memory location
				// specified
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();

				int loc = hexToDec(s);
				in[loc] = accumA;
				count += 3;

				break;
			case '2':
				// SWP: (1) swap the contents of A and B
				char temp = accumA;
				accumA = accumB;
				accumB = temp;

				count++;
				break;
			case '3':
				// ADD: (1) add the contents of A and B
				// low word of sum is stored in A and the high word in B
				int a = hexToDec(String.valueOf(accumA));
				int b = hexToDec(String.valueOf(accumB));

				int sum = a + b;

				String h = decToHex(sum);
				if (h.length() < 2) {
					accumA = h.charAt(0);
					accumB = '0';
				} else {
					accumB = h.charAt(0);
					accumA = h.charAt(1);
				}
				count++;
				break;
			case '4':
				// INC: (1) increment A - overflow is allowed F -> 0
				if (accumA == 'F') {
					accumA = '0';
				} else {
					int dec = hexToDec(String.valueOf(accumA));
					dec++;
					hex = decToHex(dec).charAt(0);
					accumA = hex;
				}

				count++;
				break;
			case '5':
				// DEC: (1) decrement A - underflow is allowed 0 -> F
				if (accumA == '0') {
					accumA = 'F';
				} else {
					int dec = hexToDec(String.valueOf(accumA));
					dec--;
					hex = decToHex(dec).charAt(0);
					accumA = hex;
				}

				count++;
				break;
			case '6':
				// BZ: (3) if A is 0 -> command is at the location specified
				// if A is not 0 -> the argument is ignored
				if (accumA == '0') {
					builder = new StringBuilder();
					builder.append(in[count + 1]);
					builder.append(in[count + 2]);
					s = builder.toString();
					int location = hexToDec(s);
					count = location;

				} else {
					count += 3;
				}
				break;
			case '7':
				// BR: (3) the next command to execute is at the location
				// specified by argument
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();

				int location = hexToDec(s);
				count = location;
				break;
			case '8':
				memory.setInstruction(in);
				return;

			}
		}

	}

	public Memory getMemory() {
		return memory;
	}

	private String decToHex(int dec) {
		return Integer.toHexString(dec).toUpperCase();
	}

	private int hexToDec(String hex) {
		String hexString = String.valueOf(hex);
		return Integer.parseInt(hexString, 16);
	}

	public static void main(String[] args) {
		Microprocessor processor;
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(
				System.in));

		String line;
		System.out.println("Output:");
		try {
			while ((line = keyboard.readLine()) != null) {
				processor = new Microprocessor(line.toCharArray());
				System.out.println(processor.getMemory().getInstruction());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
