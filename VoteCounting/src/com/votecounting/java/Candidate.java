package com.votecounting.java;

public class Candidate {
	
	private char letter;
	private String name;
		
	public Candidate(char letter, String name) {
		this.letter = letter;
		this.name = name;
	}
	
	public char getLetter() {
		return letter;
	}
		
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return letter + ". " + name;
	}

}
