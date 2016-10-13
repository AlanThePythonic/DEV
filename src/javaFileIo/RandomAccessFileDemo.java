package javaFileIo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	/**
	 * @param skip
	 *            Skipped how many bytes
	 * @param str
	 *            Insert String
	 * @param fileName
	 *            File name with directory
	 */
	public static void skipAndInsert(long skip, String str, String fileName) {

		try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {

			if (skip < 0 || skip > raf.length()) {
				System.out.println("跳过字节数无效");

			} else {
				byte[] b = str.getBytes();
				raf.setLength(raf.length() + b.length);
				for (long i = raf.length() - 1; i > b.length + skip - 1; i--) {
					raf.seek(i - b.length);
					byte temp = raf.readByte();
					raf.seek(i);
					raf.writeByte(temp);
				}
				raf.seek(skip);
				raf.write(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadFileByMultiThread() throws IOException {

		// Setting the size of hard drive space ，The specified size file will be
		// created on the Hard Drive
		RandomAccessFile raf = new RandomAccessFile("D://abc.txt", "rw");
		raf.setLength(1024 * 1024); // 1M size space
		raf.close();

		// Written content
		String s1 = "First String";
		String s2 = "Second String";
		String s3 = "Third String";
		String s4 = "Fouth String";
		String s5 = "Fifth String";

		// Use multi threading to write to a file

		// Write the data to the file from 1024 bytes of the file
		new FileWriteThread(1024 * 1, s1.getBytes()).start();

		// Write the data to the file from 2048 bytes of the file
		new FileWriteThread(1024 * 2, s2.getBytes()).start();

		// Write the data to the file from 3072 bytes of the file
		new FileWriteThread(1024 * 3, s3.getBytes()).start();

		// Write the data to the file from 4096 bytes of the file
		new FileWriteThread(1024 * 4, s4.getBytes()).start();

		// Write the data to the file from 5120 bytes of the file
		new FileWriteThread(1024 * 5, s5.getBytes()).start();
	}

	// Use the thread to write the data to the file on the specified place
	static class FileWriteThread extends Thread {

		private int skip;
		private byte[] content;

		public FileWriteThread(int skip, byte[] content) {
			this.skip = skip;
			this.content = content;
		}

		public void run() {
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile("D://abc.txt", "rw");
				raf.seek(skip);
				raf.write(content);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					raf.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void generalCase(String[] args) throws IOException {

		RandomAccessFile rf = new RandomAccessFile("rtest.dat", "rw");

		for (int i = 0; i < 10; i++) {

			// Write a double variable
			rf.writeDouble(i * 1.414);
		}
		rf.close();
		rf = new RandomAccessFile("rtest.dat", "rw");

		// Move the pointer to the after fifth double variable
		rf.seek(5 * 8);

		// Re-write the sixth double variable
		rf.writeDouble(47.0001);
		rf.close();
		rf = new RandomAccessFile("rtest.dat", "r");

		for (int i = 0; i < 10; i++) {
			System.out.println("Value " + i + ": " + rf.readDouble());
		}

		rf.close();
	}

	public static void readPractice(String[] args) throws Exception {

		try (RandomAccessFile file = new RandomAccessFile("file", "rw")) {

			// Write the data to the file
			file.writeInt(20);// 4 bytes
			file.writeDouble(8.236598);// 8 bytes
			file.writeUTF("Here is a UTF String 1");// Will be written to the 2
													// bytes before the pointer
													// ，can be read by
													// readShort()
			file.writeBoolean(true);// 1 byte
			file.writeShort(395);// 2 bytes
			file.writeLong(2325451l);// 8 bytes
			file.writeUTF("Here is a UTF String 2");
			file.writeFloat(35.5f);// 4 bytes
			file.writeChar('a');// 2 bytes

			file.seek(0);// Set the pointer to the beginning of the file

			// Read the data from file. (Pay attention to the pointer location)
			System.out.println("----- Read the data from the specified pointer -----");
			System.out.println(file.readInt());
			System.out.println(file.readDouble());
			System.out.println(file.readUTF());

			file.skipBytes(3);// Skipped 3 bytes of the file，Skipped a boolean
								// and a short value at this case
			System.out.println(file.readLong());

			file.skipBytes(file.readShort()); // Skipped the UTF String
												// size，noted readShort() will
												// move the pointer，so do not
												// add 2 。
			System.out.println(file.readFloat());

			// File Copy
			System.out.println("----- File Copy (From file to file copy) -----");
			file.seek(0);

			try (RandomAccessFile fileCopy = new RandomAccessFile("fileCopy", "rw")) {
				int len = (int) file.length();// Get the length of the file (the
												// num of bytes)
				byte[] b = new byte[len];
				file.readFully(b);
				fileCopy.write(b);
				System.out.println("Copy completed!");
			}
		}
	}
}
