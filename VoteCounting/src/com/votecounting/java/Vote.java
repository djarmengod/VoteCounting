package com.votecounting.java;

public class Vote {
	
	private Candidate candidate;
	private int orderPreference;	
	
	public Vote(Candidate candidate, int orderPreference) {
		this.candidate = candidate;
		this.orderPreference = orderPreference;		
	}

	public int getOrderPreference() {
		return orderPreference;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	@Override
	public String toString() {
		return candidate + "-"+ orderPreference;
	}


}
