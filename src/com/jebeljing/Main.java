package com.jebeljing;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String fileName;
		System.out.println("Please enter the fileName:");
		Scanner reader = new Scanner(System.in);
		fileName = reader.nextLine();
//		if (args.length == 0) {
//			System.out.println("Please enter the fileName!");
//			return;
//		} else {
//			//"C:\\Users\\lalala123\\Desktop\\The Original_NC.NC"
//			fileName = args[0];
//		}
		NCFileReader nc = new NCFileReader(fileName);
		try {
			nc.beginWork();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
