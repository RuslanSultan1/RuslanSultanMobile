package enums;

public enum Contacts {
    IVAN("Ivan", "89001111111", "ivan@mail.com");

    private String contactName;
    private String contactPhone;
    private String contactEmail;

    Contacts(String contactName, String contactPhone, String contactEmail) {
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}


