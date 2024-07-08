package scrabble.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleWordList implements WordList {
	private Set<String> words = new HashSet<>();

	public static void main(String[] args) {
		SimpleWordList wordList = new SimpleWordList();
		wordList.initFromFile("C:\\Users\\Admin\\Desktop\\Lab\\wordlists\\sowpods.txt");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Scrabble Cheater!");
		System.out.println("Type 'exit' to quit.");

		while (true) {
			System.out.print("Enter your tiles: ");
			String tiles = scanner.nextLine().trim();

			if ("exit".equalsIgnoreCase(tiles)) {
				break;
			}

			Set<String> validWords = wordList.validWordsUsingAllTiles(tiles);
			if (validWords.isEmpty()) {
				System.out.println("No valid words can be formed.");
			} else {
				System.out.println("Possible words: " + validWords);
			}
		}

		scanner.close();
		System.out.println("Thank you for using the Scrabble Cheater!");
	}

	@Override
	public boolean add(String word) {
		if (word != null && word.length() >= MIN_WORD_LENGTH) {
			return words.add(normalize(word));
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<String> words) {
		boolean isAllAdded = true;
		for (String word : words) {
			if (!add(word)) {
				isAllAdded = false;
			}
		}
		return isAllAdded;
	}

	@Override
	public int size() {
		return words.size();
	}

	@Override
	public WordList initFromFile(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\Lab\\wordlists\\sowpods.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				add(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return this;
	}

	@Override
	public Set<String> validWordsUsingAllTiles(String tileRackPart) {
		Set<String> validWords = new HashSet<>();
		String normalizedTiles = normalize(tileRackPart);
		for (String word : words) {
			if (normalize(word).equals(normalizedTiles)) {
				validWords.add(word);
			}
		}
		return validWords;
	}

	@Override
	public Set<String> allValidWords(String tileRack) {
		Set<String> validWords = new HashSet<>();
		if (tileRack == null || tileRack.isEmpty()) return validWords;

		Set<String> allSubsets = scrabble.util.SubSets.getSubSets(tileRack);


		for (String subset : allSubsets) {
			if (words.contains(subset)) {
				validWords.add(subset);
			}
		}
		return validWords;
	}


	private String normalize(String input) {
		char[] chars = input.toLowerCase().toCharArray();
		java.util.Arrays.sort(chars);
		return new String(chars);
	}
}
