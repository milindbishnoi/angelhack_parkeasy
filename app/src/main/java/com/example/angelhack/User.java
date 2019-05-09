package com.example.angelhack;

public class User {
    String name;
    String contact;
    int building;
    int slot;

    public User(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
    public void setBuilding(int building){
        this.building = building;
    }

    public void setSlot(int slot){
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
