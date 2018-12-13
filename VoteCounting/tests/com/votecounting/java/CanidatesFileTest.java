package com.votecounting.java;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.Candidate;
import com.votecounting.java.CandidatesFile;

public class CanidatesFileTest {
	
	CandidatesFile candidatesFile;
	List<Candidate> candidates;
	BufferedReader bufferedReader = null;;
	Reader reader = null;

	// Common
	@Before
	public void setUp() throws Exception {
		candidatesFile = CandidatesFile.getInstance();
		// candidates type stub
		candidates = new ArrayList<>();
		candidates.add(new Candidate('A', "Winery tour"));
		candidates.add(new Candidate('B', "Ten pin bowling"));
		candidates.add(new Candidate('C', "Movie night"));
		candidates.add(new Candidate('D', "Museum visit"));
		
		// Reader stub
		StringBuilder sb = new StringBuilder();
		sb.append(" Winery tour\n");
		sb.append("\n");
		sb.append("  Ten pin bowling\n");
		sb.append("Movie night  \n");
		sb.append(" Museum visit \n");
		String content = sb.toString();
		byte[] data = content.getBytes();
		InputStream input = new ByteArrayInputStream(data);
		reader = new InputStreamReader(input);
	}

	// Test 1=>readFile valid path
	@Test
	public void test_readFile_Valid_Path() {
		candidatesFile.readFile(CandidatesFile.PATH);
		assertFalse("read file with a valid path=>",candidatesFile.getCandidates().isEmpty());
	}

	// Test 2=>readFile invalid path
	@Test
	public void test_readfile_Invalid_Path() {
		candidatesFile.readFile("files/CandidatesFiles.tx");
		assertTrue("read file with a valid path=>",candidatesFile.getCandidates().isEmpty());
	}
	
	
	// Test 3=>getFileReader_IOException
	@Test(expected = IOException.class)
	public void test_getFileReader_IOException() throws IOException {

		bufferedReader=candidatesFile.getFileReader("");
	}

	// Test 4=>getFileReader
	@Test
	public void test_getFileReader()  {
		Exception exception = null;
		
		try {
			bufferedReader=candidatesFile.getFileReader(CandidatesFile.PATH);
		} catch (IOException e) {
			exception = e;
		}
		
		assertNull("CandidatesFile.getFileReader: Exception thrown=>", exception);
	}
	
	// Test 5=>readBuffer_FromString
	@Test
	public void test_readBufferFromString() {
		Exception exception = null;
		bufferedReader = new BufferedReader(reader);

		try {
			candidatesFile.readBuffer(bufferedReader);
		} catch (IOException e) {
			exception = e;
		}

		assertNull("CandidatesFile.readBuffer: Exception thrown=>", exception);
		assertEquals("Compare candidates with String input=>", candidates.toString(), candidatesFile.getCandidates().toString());
	}

	// Test 6=>readBuffer_FromFile
	@Test
	public void test_readBufferFromFile() {
		Exception exception = null;

		try {
			reader = candidatesFile.getFileReader(CandidatesFile.PATH);
			bufferedReader = new BufferedReader(reader);
		} catch (IOException e) {
			exception = e;
		}
		assertNull("CandidatesFile.getFileReader: Exception thrown=>", exception);

		exception = null;
		try {
			candidatesFile.readBuffer(bufferedReader);
		} catch (IOException e) {
			exception = e;
		}
		assertNull("CandidatesFile.readBuffer: Exception thrown=>", exception);
		assertEquals("Compare candidates type with file input=>", candidates.toString(),
				candidatesFile.getCandidates().toString());
	}

}
