package java8;

import java.util.EnumMap;
import java.util.Map;

enum CountryCode {

	HONGKONG("HK"), CHINA("CN"), JAPAN("JP"), CANADA("CAN");

	private final String code;

	CountryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}

public class EnumMapImpl {

	public static void main(String[] args) {

		Map<CountryCode, String> currencyMap = new EnumMap<CountryCode, String>(CountryCode.class);

		currencyMap.put(CountryCode.HONGKONG, "HKD");
		currencyMap.put(CountryCode.CHINA, "RMB");
		currencyMap.put(CountryCode.JAPAN, "YEN");

		for (CountryCode action : CountryCode.values()) {
			System.out.println(currencyMap.get(action));
		}
	}
}
