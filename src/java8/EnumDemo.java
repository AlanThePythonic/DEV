package java8;

public class EnumDemo {
	
	public enum CountryCode {

		HONGKONG("HK"), CHINA("CN"), JAPAN("JP"), CANADA("CAN");

		private final String code;

		CountryCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}
	}
	
	public static void main(String [] args){
		
		System.out.println(CountryCode.CHINA);
	}
}
