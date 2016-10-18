package java8;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapSortedByLambda {

	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 10);
		map.put("b", 30);
		map.put("c", 50);
		map.put("d", 40);
		map.put("e", 100);
		map.put("f", 60);
		map.put("g", 110);
		map.put("h", 50);
		map.put("i", 90);
		map.put("k", 70);
		map.put("L", 80);

		Map<String, Integer> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		sortedMap.forEach((k, v) -> System.out.println("Code : " + k + ", Lot size : " + v));
	}
}
