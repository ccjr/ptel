package mobi.ccjr.ptel.parser;

/**
 * This class parses a balance message, format is in test
 * @author ccarneiroj
 */
public class BalanceMessageParser {
	private String text;
	
	public BalanceMessageParser(String text) {
		this.text = text;
	}
	
	public String extractBalance() {
		String[] parts = text.split("[|]", -1);
		if (parts.length == 4) {
			StringBuffer sb = new StringBuffer(parts[2]);
			return sb.replace(0, 10, "").toString().trim();
		} else {
			return null;
		}
	}
}
