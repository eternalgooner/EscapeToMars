package com.escapetomars.util;

import java.io.Serializable;

public class PlayerScore implements Comparable<PlayerScore>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5150815880811305807L;
	private int score;
	private String name;
	
	public PlayerScore(int score, String name) {
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(PlayerScore o) {
		if(this.score < o.getScore()){
			return 1;
		}else if(this.score > o.getScore()){
			return -1;
		}
		else{
			return 0;
		}
	}
}
