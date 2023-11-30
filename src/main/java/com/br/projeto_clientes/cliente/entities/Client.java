package com.br.projeto_clientes.cliente.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TBL_CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "O nome do Cliente não Pode ser em Branco!")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "O email do Cliente não Pode ser em Branco!")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "O telefone do Cliente não Pode ser em Branco!")
    private String phone;

    @Column(nullable = false)
    @NotBlank(message = "O endereço do Cliente não Pode ser em Branco!")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "A cidade do Cliente não Pode ser em Branco!")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "O cep do Cliente não Pode ser em Branco!")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "O estado do Cliente não Pode ser em Branco!")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
