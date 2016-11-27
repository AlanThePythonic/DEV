package Big5Extension;

import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

class Big5_Extend extends Charset {

	private static final String BASE_CHARSET = "Big5";
	private static final String NAME = "X-Big5-Extend";
	private static final String[] ALIASES = { "X-Big5_Extend" };
	private Charset baseCharset;

	public Big5_Extend() {
		this(NAME, ALIASES);
	}

	public Big5_Extend(String canonical, String[] aliases) {
		super(canonical, aliases);
		baseCharset = Charset.forName(BASE_CHARSET);
	}

	public boolean contains(Charset cs) {
		return getClass().isInstance(cs) || baseCharset.getClass().isInstance(cs);
	}

	public CharsetDecoder newDecoder() {
		return new Decoder(this, baseCharset.newDecoder());
	}

	public CharsetEncoder newEncoder() {
		return new Encoder(this, baseCharset.newEncoder());
	}

	class Decoder extends CharsetDecoder {

		private final CharsetDecoder base;

		Decoder(Charset cs, CharsetDecoder base) {
			super(cs, base.averageCharsPerByte(), base.maxCharsPerByte());
			this.base = base;
		}

		@Override
		protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
			base.reset();

			CoderResult result = base.decode(in, out, true);
			if (!result.isUnmappable() || in.remaining() < 2) {
				return result;
			}

			int pos = in.position();
			char big5Char = (char) ((in.get(pos) << 8) | in.get(pos + 1));
			char outChar;

			switch (big5Char) {
			case '\uFA40':
				outChar = '\u5803';
				break; /* å  */
			case '\uFA41':
				outChar = '\u83D3';
				break; /* è */
			case '\uFA42':
				outChar = '\u854B';
				break; /* è */
			case '\uFA43':
				outChar = '\u4F8A';
				break; /* ä¾ */
			default:
				return result;
			}

			out.put(outChar);

			in.position(pos + 2);
			return decodeLoop(in, out);
		}
	}

	class Encoder extends CharsetEncoder {
		private final CharsetEncoder base;

		Encoder(Charset cs, CharsetEncoder base) {
			super(cs, base.averageBytesPerChar(), base.maxBytesPerChar());
			this.base = base;
		}

		@Override
		protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
			base.reset();

			CoderResult result = base.encode(in, out, true);
			if (!result.isUnmappable() || out.remaining() < 2) {
				return result;
			}
			int pos = in.position();
			char uniChar = in.get(pos);
			char outChar;

			switch (uniChar) {
			case '\u5803':
				outChar = '\uFA40';
				break; /* å  */
			case '\u83D3':
				outChar = '\uFA41';
				break; /* è */
			case '\u854B':
				outChar = '\uFA42';
				break; /* è */
			case '\u4F8A':
				outChar = '\uFA43';
				break; /* ä¾ */
			default:
				return result;
			}

			out.put((byte) (outChar >> 8));
			out.put((byte) outChar);

			in.position(pos + 1);
			return encodeLoop(in, out);
		}
	}
}

class CharsetProvider extends java.nio.charset.spi.CharsetProvider {

	static Map<String, Charset> name2charset;
	static Collection<Charset> charsets;

	public Charset charsetForName(String charsetName) {
		if (charsets == null) {
			init();
		}
		return name2charset.get(charsetName.toLowerCase());
	}

	public Iterator<Charset> charsets() {
		if (charsets == null) {
			init();
		}
		return charsets.iterator();
	}

	void init() {
		name2charset = new HashMap<String, Charset>();

		charsets = new HashSet<Charset>();
		charsets.add(new Big5_Extend());

		for (Charset charset : charsets) {
			name2charset.put(charset.name().toLowerCase(), charset);
			for (String aliase : charset.aliases()) {
				name2charset.put(aliase.toLowerCase(), charset);
			}
		}
	}
}

public class Big5ExtendAction {
	private static void doEncode(Charset cs, String input) {
		ByteBuffer bb = cs.encode(input);
		System.out.println("Charset: " + cs.name());
		System.out.println("  Input: " + input);
		System.out.println("Encoded: ");
		for (int i = 0; bb.hasRemaining(); i++) {
			int b = bb.get();
			int ival = (int) b & 0xff;
			char c = (char) ival;
			// Keep tabular alignment pretty
			if (i < 10) {
				System.out.print(" ");
			}
			// Print index number
			System.out.print("  " + i + ": ");
			// Better formatted output is coming someday...
			if (ival < 16) {
				System.out.print("0");
			}
			// Print the hex value of the byte
			System.out.print(Integer.toHexString(ival));
			// If the byte seems to be the value of a
			// printable character, print it. No guarantee
			// it will be.
			if (Character.isWhitespace(c) || Character.isISOControl(c)) {
				System.out.println("");
			} else {
				System.out.println(" (" + c + ")");
			}
		}
		System.out.println("");
	}

	public static void main(String[] args) throws Throwable {
		String charset = "X-Big5-Extend";
		String source = "堃菓蕋侊";

		doEncode(new Big5_Extend(), source);

		/*
		 * byte[] bytes = source.getBytes(charset); for (byte b : bytes) {
		 * System.out.printf("%x ", b); } System.out.println("\n");
		 * 
		 * String result = new String(bytes, charset);
		 * System.out.println(result);
		 */
		// 堃菓蕋侊
	}
}
