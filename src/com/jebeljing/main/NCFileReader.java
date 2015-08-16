package com.jebeljing.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NCFileReader {

	public void searchFile() {
		
	}
	
	public static void main(String[] args) {
		List<String> minZs = new ArrayList<String>();
		List<String> maxFs = new ArrayList<String>();
		List<String> maxSs = new ArrayList<String>();
		int zIndex = -1;
		int fIndex = -1;
		int sIndex = -1;
		double minZ = 0;
		double maxF = 0;
		double maxS = 0;
		int lineNum = 0;
		int tempLine = 0;
		BufferedReader br = null;
		try {
//			br = new BufferedReader(new FileReader("C:\\Users\\lalala123\\Desktop\\test1.txt"));
			br = new BufferedReader(new FileReader("C:\\Users\\lalala123\\Desktop\\The Original_NC.NC"));
			String line;
			while((line = br.readLine()) != null) {
				if (line.startsWith("N") && line.contains("M6")) {
					minZ = 0;
					maxF = 0;
					maxS = 0;
					zIndex++;
					fIndex++;
					sIndex++;
//					System.out.println(tempLine + 1);
				} else if (line.startsWith("N") && (line.contains("Z") || line.contains("Z-"))) {
//					System.out.println(line);
					if (line.contains("Z-")) {
						String zString = line.substring(line.indexOf("Z-"));
						int lastSpaceIdx = zString.indexOf(" ");
						if (lastSpaceIdx > 0) {
							Double zVal = Double.valueOf(zString.substring(2, lastSpaceIdx));
							if (zVal > minZ) {
								minZ = zVal;
								if (zIndex == minZs.size()) {
									minZs.add(zString.substring(2, lastSpaceIdx));
								} else {
									minZs.set(zIndex, zString.substring(2, lastSpaceIdx));
								}
								lineNum = tempLine;
							}
						} else {
							Double zVal = Double.valueOf(zString.substring(2));
							if (zVal > minZ) {
								minZ = zVal;
								if (zIndex == minZs.size()) {
									minZs.add(zString.substring(2));
								} else {
									minZs.set(zIndex, zString.substring(2));
								}
								lineNum = tempLine;
							}
						}
					} else if (line.contains("Z")) {
						String zString = line.substring(line.indexOf("Z"));
						int lastSpaceIdx = zString.indexOf(" ");
						if (lastSpaceIdx > 0) {
							Double zVal = Double.valueOf(zString.substring(1, lastSpaceIdx));
							if (zVal > minZ) {
								minZ = zVal;
								if (zIndex == minZs.size()) {
									minZs.add(zString.substring(1, lastSpaceIdx));
								} else {
									minZs.set(zIndex, zString.substring(1, lastSpaceIdx));
								}
								lineNum = tempLine;
							}
						} else {
							Double zVal = Double.valueOf(zString.substring(1));
							if (zVal > minZ) {
								minZ = zVal;
								if (zIndex == minZs.size()) {
									minZs.add(zString.substring(1));
								} else {
									minZs.set(zIndex, zString.substring(1));
								}
								lineNum = tempLine;
							}
						}
					}
				} 
				if (line.startsWith("N") && line.contains("F")) {
					String fString = line.substring(line.indexOf("F"));
					int lastSpaceIdx = fString.indexOf(" ");
					if (lastSpaceIdx > 0) {
						Double fVal = Double.valueOf(fString.substring(1, lastSpaceIdx));
						if (fVal > maxF) {
							maxF = fVal;
							if (fIndex == maxFs.size()) {
								maxFs.add(fString.substring(1, lastSpaceIdx));
							} else {
								maxFs.set(fIndex, fString.substring(1, lastSpaceIdx));
							}
							lineNum = tempLine;
						}
					} else {
						Double fVal = Double.valueOf(fString.substring(1));
						if (fVal > maxF) {
							maxF = fVal;
							if (fIndex == maxFs.size()) {
								maxFs.add(fString.substring(1));
							} else {
								maxFs.set(fIndex, fString.substring(1));
							}
							lineNum = tempLine;
						}
					}
				}
				if (line.startsWith("N") && line.contains("S")) {
					String sString = line.substring(line.indexOf("S"));
					int lastSpaceIdx = sString.indexOf(" ");
					if (lastSpaceIdx > 0) {
						Double sVal = Double.valueOf(sString.substring(1, lastSpaceIdx));
						if (sVal > maxS) {
							maxS = sVal;
							if (sIndex == maxSs.size()) {
								maxSs.add(sString.substring(1, lastSpaceIdx));
							} else {
								maxSs.set(sIndex, sString.substring(1, lastSpaceIdx));
							}
							lineNum = tempLine;
						}
					} else {
						Double sVal = Double.valueOf(sString.substring(1));
						if (sVal > maxS) {
							maxS = sVal;
							if (sIndex == maxSs.size()) {
								maxSs.add(sString.substring(1));
							} else {
								maxSs.set(sIndex, sString.substring(1));
							}
							lineNum = tempLine;
						}
					}
				}
				tempLine++;
			}
			lineNum++;
//			System.out.println("Line = " + lineNum + ", maxZ = " + maxZ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < minZs.size(); i++) {
			System.out.println("Result: minZ=" + minZs.get(i) + ", maxF=" + maxFs.get(i) + ", maxS=" + maxSs.get(i));
		}
	}
}
