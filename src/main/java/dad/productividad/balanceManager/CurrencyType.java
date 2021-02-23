package dad.productividad.balanceManager;

public enum CurrencyType {

    EURO("€"), POUND("£"), DOLAR("$"), YEN("¥"), SWISS_FRANC("₣"), YUAN("¥");
    private final String text;

    CurrencyType(final String text) {
        this.text = text;

    }

	@Override
	public String toString() {
		return text;
	}
}
