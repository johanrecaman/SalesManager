package src.models;

import java.time.LocalDate;

public class Supplier extends User {

    private String companyName;
    private String taxId;
    private String phoneNumber;
    private String city;
    private String state;
    private String country;
    private String address;
    private LocalDate registrationDate;

    public Supplier(String name, String email, String password, String companyName, String taxId, String phoneNumber, 
                    String city, String state, String country, String address, LocalDate registrationDate) {
        super(name, email, password);
        this.companyName = companyName;
        this.taxId = taxId;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
