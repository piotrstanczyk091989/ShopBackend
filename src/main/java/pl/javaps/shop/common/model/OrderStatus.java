package pl.javaps.shop.common.model;

public enum OrderStatus {
    NEW("Nowy"),
    PAID("Opłacony"),
    PROCESSING("Przetwarzany"),
    WAITING_FOR_DELIVERY("Czeka na dostawe"),
    COMPLETED("Zrealizowany"),
    CANCELED("Anulowany"),
    REFUND("Zwrócone");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
