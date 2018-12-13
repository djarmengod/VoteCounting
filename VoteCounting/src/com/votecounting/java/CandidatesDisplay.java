package com.votecounting.java;

import java.util.ArrayList;
import java.util.List;

public class CandidatesDisplay {

	private List<Candidate> candidates;

	public CandidatesDisplay() {

	}

	public CandidatesDisplay(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public List<Candidate> getCandidates() {
		candidates = new ArrayList<>();
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public void show() {

		for (Candidate o : candidates) {
			System.out.println(o.toString());
		}

	}

}
