package dad.productividad.balanceManager;

import java.util.ResourceBundle;

public enum CurrencyType {

    EURO("€", "Euro"), POUND("£", ResourceBundle.getBundle("i18n/strings").getString("pound")),
    DOLLAR("$", ResourceBundle.getBundle("i18n/strings").getString("dollar")), YEN("¥", "Yen"),
    SWISS_FRANC("₣", ResourceBundle.getBundle("i18n/strings").getString("franc")), YUAN("¥", "Yuan");

    private final String symbol;
    private final String nm;

    CurrencyType(String symbol, String name) {
        this.symbol = symbol;
        this.nm = name;
    }

    @Override
	public String toString() {
		return symbol + " " + nm;
	}

    public String getSymbol() {
        return symbol;
    }
}
