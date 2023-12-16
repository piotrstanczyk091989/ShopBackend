package pl.javaps.shop.admin.order.model;

public enum AdminOrderStatus {
    NEW("Nowy"),
    PAID("Opłacony"),
    PROCESSING("Przetwarzany"),
    WAITING_FOR_DELIVERY("Czeka na dostawe"),
    COMPLETED("Zrealizowany"),
    CANCELED("Anulowany"),
    REFUND("Zwrócone");

    private String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
