package com.proyect.reservationmanager.model;

public class Client {
    private String id;
    private String name;
    private String documentId; // DNI/Passport
    private String phone;
    private String email;

    public Client(String id, String name, String documentId, String phone, String email) {
        this.id = id;
        this.name = name;
        this.documentId = documentId;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
