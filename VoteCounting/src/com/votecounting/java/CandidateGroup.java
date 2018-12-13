package com.votecounting.java;

import java.util.ArrayList;
import java.util.List;

public class CandidateGroup {

	private Candidate candidate;
	private List<BallotPaper> ballotPapers;
	private boolean eliminated;

	public CandidateGroup(Candidate candidate) {
		this.candidate = candidate;
		this.ballotPapers = new ArrayList<>();
		//this.eliminated = true;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public List<BallotPaper> getBallotPapers() {
		return ballotPapers;
	}

	public void addBallotPaper(BallotPaper ballotPaper) {
		ballotPapers.add(ballotPaper);
		setEliminated(false);
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	public int getNonExhaustedBallots() {

		int nonExhaustedBallot = 0;
		for (BallotPaper ballotPaper : getBallotPapers()) {
			if (!ballotPaper.isExhaustedBallot()) {
				nonExhaustedBallot += 1;
			}
		}
		return nonExhaustedBallot;
	}

	public int getExhaustedBallots() {

		int exhaustedBallots = 0;
		for (BallotPaper ballotPaper : getBallotPapers()) {
			if (ballotPaper.isExhaustedBallot()) {
				exhaustedBallots += 1;
			}
		}
		return exhaustedBallots;
	}
	

}
