package scrabble.util;

import java.util.HashSet;
import java.util.Set;

public class SubSets {

	public static Set<String> getSubSets(String str) {
		Set<String> subsets = new HashSet<>();
		int n = str.length();
		int numSubsets = 1 << n;

		for (int i = 0; i < numSubsets; i++) {
			StringBuilder subset = new StringBuilder();
			for (int j = 0; j < n; j++) {

				if ((i & (1 << j)) != 0) {
					subset.append(str.charAt(j));
				}
			}
			subsets.add(subset.toString());
		}

		return subsets;
	}
}
