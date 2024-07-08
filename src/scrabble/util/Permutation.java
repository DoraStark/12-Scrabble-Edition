package scrabble.util;

import java.util.Arrays;

public class Permutation {
	private final String word;

	public Permutation(String word) {
		this.word = word;
	}

	public String getWord() {
		return this.word;
	}


	public String getNormalized() {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}


	public int length() {
		return word.length();
	}


	@Override
	public int hashCode() {
		return getNormalized().hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Permutation other = (Permutation) obj;
		return getNormalized().equals(other.getNormalized());
	}


	@Override
	public String toString() {
		return getWord() + " / " + getNormalized();
	}
}
