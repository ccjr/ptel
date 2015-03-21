package mobi.ccjr.ptel.parser;

/**
 * This class parses a balance message, format is in test
 * @author ccarneiroj
 */
public class BalanceMessageParser {
	private static final String ENTRY_DELIMITER = "Airtime:";
	private String text;
	
	public BalanceMessageParser(String text) {
		this.text = text;
	}
	
	public String extractBalance() {
		String[] parts = text.split("[|]", -1);
		if (parts.length == 4) {
			return parts[2].trim().substring(ENTRY_DELIMITER.length()).trim();
		} else {
			return null;
		}
	}
}
