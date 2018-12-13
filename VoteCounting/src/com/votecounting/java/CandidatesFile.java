package com.votecounting.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class CandidatesFile {
	
	private static CandidatesFile instance;
	
	public static final String PATH = "files/CandidatesFiles.txt";
	private static List<Candidate> candidates;
	
	private CandidatesFile() {
	}

	public static synchronized CandidatesFile getInstance() {
		if (instance == null) {
			instance = new CandidatesFile();	
			candidates =  new ArrayList<>();
		}
		return instance;
	}

	private static void addCandidate(Candidate candidate) {
		candidates.add(candidate);
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public BufferedReader getFileReader(String path) throws IOException {
		Path source = Paths.get(path);
		Charset charset = Charset.forName("US-ASCII");

		return Files.newBufferedReader(source, charset);
	}

	public void readBuffer(BufferedReader reader) throws IOException {

		String line = null;
		int i = 1;
		candidates.clear();
		
		while ((line = reader.readLine()) != null) {
			//Add candidate if the line is not empty
			if (!line.isEmpty()) {
				addCandidate(new Candidate((char) (i + 64), line.trim()));
				i++;
			}
		}
	}

	public boolean readFile(String path) {

		try {			
			readBuffer(new BufferedReader(getFileReader(path)));	
			return true;
		} catch (IOException e) {
			System.out
					.println("File doesn't exist or cannot read it in this path: " + "\"files/CandidatesFiles.txt\".");
		}
		return false;
	}

}
