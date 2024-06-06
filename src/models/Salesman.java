package src.models;

import java.time.LocalDate;

public class Salesman{

    private String name;
    private String email;
    private String password;
    private String lastname;
    private LocalDate birthDate;
    private String phone;
    private String cpf;
    private String city;
    private String state;
    private String country;
    private String address;
    private LocalDate registrationDate;

    public Salesman(String name, String lastname, String email, String password, LocalDate birthDate, String phone, String cpf, 
                    String city, String state, String country, String address, LocalDate registrationDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.cpf = cpf;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
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
