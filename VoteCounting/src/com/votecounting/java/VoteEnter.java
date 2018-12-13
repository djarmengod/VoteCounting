package com.votecounting.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VoteEnter {

	private List<Vote> votes;

	public VoteEnter() {
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void readVote(String line, List<Candidate> candidates) {

		votes = new ArrayList<>();
		int orderPreference = 1;
		for (char c : line.toUpperCase().toCharArray()) {
			if (!Character.isWhitespace(c)) {
				Iterator<Candidate> iterator = candidates.iterator();

				while (iterator.hasNext()) {
					Candidate candidate = (Candidate) iterator.next();
					Character character = new Character(c);

					if (character.equals(candidate.getLetter())) {
						Vote vote = new Vote(candidate, orderPreference);

						if (!voteExists(candidate)) {
							votes.add(vote);
							orderPreference++;
						}
					}
				}

			}
		}

	}

	public boolean voteExists(Candidate candidate) {

		Vote vote;
		Iterator<Vote> iterator = votes.iterator();

		while (iterator.hasNext()) {
			vote = (Vote) iterator.next();
			if (candidate.equals(vote.getCandidate())) {
				return true;
			}
		}
		return false;
	}

}