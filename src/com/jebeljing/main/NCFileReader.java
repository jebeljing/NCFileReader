package com.jebeljing.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NCFileReader {
	
	private String fileName;
	private BufferedReader br = null;
	
	private List<String> minZs = new ArrayList<String>();
	private List<String> maxFs = new ArrayList<String>();
	private List<String> maxSs = new ArrayList<String>();
	private int zIndex = -1;
	private int fIndex = -1;
	private int sIndex = -1;
	private double minZ = 0;
	private double maxF = 0;
	private double maxS = 0;

	public NCFileReader(String fileName) {
		this.fileName = fileName;
	}
	
	public void beginWork() throws IOException {
		readFile();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("N")) {
				if (line.contains("M6")) {
					initializeVal();
				}
				scanForZ(line);
				scan(line, "F", maxF, maxFs, fIndex);
				scan(line, "S", maxS, maxSs, sIndex);
			}
		}
		printVal();
	}

	private void scan(String line, String searchStr, double maxVal, List<String> vals, int index) {
		if (line.contains(searchStr)) {
			String sString = line.substring(line.indexOf(searchStr));
			int lastSpaceIdx = sString.indexOf(" ");
			if (lastSpaceIdx > 0) {
				Double sVal = Double.valueOf(sString.substring(1, lastSpaceIdx));
				if (sVal > maxVal) {
					maxVal = sVal;
					if (index == vals.size()) {
						vals.add(sString.substring(1, lastSpaceIdx));
					} else {
						vals.set(index, sString.substring(1, lastSpaceIdx));
					}
				}
			} else {
				Double sVal = Double.valueOf(sString.substring(1));
				if (sVal > maxVal) {
					maxVal = sVal;
					if (index == vals.size()) {
						vals.add(sString.substring(1));
					} else {
						vals.set(index, sString.substring(1));
					}
				}
			}
		}
	}

	private void scanForZ(String line) {
		if (line.contains("Z") || line.contains("Z-")) {
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
							minZs.set(zIndex,
									zString.substring(2, lastSpaceIdx));
						}
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
					}
				}
			}
		}
	}

	private void printVal() {
		if (minZs.size() == maxFs.size() && maxFs.size() == maxSs.size()) {
			for (int i = 0; i < minZs.size(); i++) {
				System.out.println("Z=" + minZs.get(i) + ", F=" + maxFs.get(i) + ", S=" + maxSs.get(i));
			}
		} else {
			System.out.println("Error. The number of S, F, Z are different.");
		}
	}

	private void initializeVal() {
		minZ = 0;
		maxF = 0;
		maxS = 0;
		zIndex++;
		fIndex++;
		sIndex++;
	}
	
	private void readFile() {
		try {
			br = new BufferedReader(new FileReader(fileName)); //"C:\\Users\\lalala123\\Desktop\\The Original_NC.NC"
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
