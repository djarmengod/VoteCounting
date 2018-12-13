package com.votecounting.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.votecounting.java.Candidate;
import com.votecounting.java.CandidatesDisplay;

public class CandidatesDisplayTest {

	CandidatesDisplay display;
	Candidate candidate;
	List<Candidate> candidates;

	@Before
	public void setUp() throws Exception {
		// candidates type stub
		candidates = new ArrayList<>();
		candidate = new Candidate('A', "Winery tour");
		candidates.add(candidate);
		candidate = new Candidate('B', "Ten pin bowling");
		candidates.add(candidate);
		candidate = new Candidate('C', "Movie night");
		candidates.add(candidate);
		candidate = new Candidate('D', "Museum visit");
		candidates.add(candidate);
	}

	// Test 1=>setCandidates
	@Test
	public void test_setCandidates() {
		display = new CandidatesDisplay();
		display.setCandidates(candidates);
		assertNotNull("Set Candidates List=>", candidates);
	}

	// Test 2=>CandidatesDisplay
	@Test
	public void test_DisplayCandidates() {
		display = new CandidatesDisplay(candidates);
		assertNotNull("Display Candidates=>", display);
	}

	// Test 3=>getCandidates
	@Test
	public void test_getCandidates() {
		display = new CandidatesDisplay();
		display.setCandidates(candidates);
		candidates = display.getCandidates();
		assertNotNull("Get Candidates List=>", candidates);
	}

	// Test 4=>show
	@Test
	public void test_show() {
		Exception exception = null;
		display = new CandidatesDisplay(candidates);

		try {
			display.show();
		} catch (NullPointerException e) {
			exception = e;
		}

		assertNull("CandidatesDisplay.show: Exception thrown=>", exception);
	}

}
