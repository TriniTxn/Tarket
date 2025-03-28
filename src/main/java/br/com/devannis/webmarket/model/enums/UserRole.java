package br.com.devannis.webmarket.model.enums;

public enum UserRole {
    ADMIN("admin"),
    CLIENT("client"),
    USER("user"),
    SELLER("seller");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
