package br.com.devannis.webmarket.model.enums;

public enum Category {
    TSHIRTS("T-Shirts"),
    SHIRTS("Shirts"),
    PANTS("Pants"),
    SOCKS("Socks"),
    DRESSES("Dresses"),
    SKIRTS("Skirts"),
    ACCESSORIES("Accessories"),;

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
