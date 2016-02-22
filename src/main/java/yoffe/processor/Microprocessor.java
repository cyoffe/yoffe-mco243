package yoffe.processor;

public class Microprocessor {
	private char accumA, accumB;
	private Memory memory;
	private char[] instrucArray;
	private StringBuilder builder;
	private int count, seven;

	public Microprocessor() throws Exception {
		accumA = accumB = '0';
		instrucArray = new char[256];
		memory = new Memory("mach.in");
		seven = 0;
		int instrucNum = 0;
		while (instrucNum < memory.getInstructionsSize()) {
			String s = memory.getInstruction();
			System.out.println("Before:" + s);
			readInstruction(instrucArray = s.toCharArray());

			s = new String(instrucArray);
			System.out.println("After:" + s);

			if (s.equals("0102011311321128FF1E00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
					|| s.equals("021202331250203202231248000000001C264200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
					|| s.equals("040563B14004220FF31FF041320FE31FE00C204231420003204131417008000011F0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000E1")
					|| s.equals("84F315105031500512002461F10270005210070000000000000000000000000023456234562345623E800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) {
				System.out.println("CORRECT!!!!!");
			}

			instrucNum++;
		}
	}

	public void readInstruction(char[] in) throws Exception {
		if (in.length != 256) {
			throw new Exception();
		}
		String s;
		count = 0;
		char instruction, hex;
		while (count < in.length && (in[count + 1]) != '8') {
			instruction = in[count];

			switch (instruction) {
			case '0':
				// LD: (3) load A with the contents of memory at the specified
				// argument
				System.out.println(" 0: LOAD");
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();

				int i = hexToDec(s);
				accumA = instrucArray[i];
				count += 3;

				break;
			case '1':
				// ST: (3) write the contents of A to the memory location
				// specified
				System.out.println(" 1: Store");
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();

				int loc = hexToDec(s);
				System.out.println("Location: " + loc + "- put " + accumA);
				instrucArray[loc] = accumA;
				count += 3;

				s = new String(in);
				System.out.println("After:" + s);

				break;
			case '2':
				// SWP: (1) swap the contents of A and B
				System.out.println(" 2: swap");
				char temp = accumA;
				accumA = accumB;
				accumB = temp;

				count++;
				break;
			case '3':
				// ADD: (1) add the contents of A and B
				// low word of sum is stored in A and the high word in B
				System.out.println(" 3: add");
				int a = hexToDec(String.valueOf(accumA));
				System.out.println(a);
				int b = hexToDec(String.valueOf(accumB));
				System.out.println(b);
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
				System.out.println(" 4: increment");
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
				System.out.println(" 5: decrement");
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
				System.out.println(" 6: if zero get command else ignore");
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
				System.out.println(" 7: get command at location");
				builder = new StringBuilder();
				builder.append(in[count + 1]);
				builder.append(in[count + 2]);
				s = builder.toString();
				int location = hexToDec(s);
				seven++;
				System.out.println("Time: " + seven);
				System.out.println("Location: " + location);
				count = location;
				break;
			case '8':
				System.out.println(" 8: stop");
				return;

			}
		}

	}

	private String decToHex(int dec) {
		return Integer.toHexString(dec).toUpperCase();
	}

	private int hexToDec(String hex) {
		String hexString = String.valueOf(hex);
		return Integer.parseInt(hexString, 16);
	}

	public static void main(String[] args) {
		try {
			new Microprocessor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public char[] processor(String a) throws Exception {
		readInstruction(instrucArray = a.toCharArray());
		return instrucArray;
	}
}
