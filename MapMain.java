
public class MapMain {

	public static void main(String[] args) {
		String sentence = "Paranoids are not paranoid because they are paranoid but because "
				+ "they keep putting themselves deliberately into paranoid avoidable situations";

		Map<String, Integer> map = new Map<>();
		String[] words = sentence.toLowerCase().split(" ");

		for (String elements : words) {
			System.out.println(elements);
		}

		for (String word : words) {
			Integer value = map.get(word);
			if (value == null)
				value = 1;
			else
				value = value + 1;
			map.add(word, value);
		}

		map.display();
		System.out.println();

		for (String word : words) {
			int frequency = map.get(word);
			System.out.println("Frequency of " + word + " : " + frequency);
		}
	}

}
