package com.uday.aryupaytask.models;

public class Employee {

    public String id;
    public String name;
    public String category;
    public String categoryid;
    public String address;
    public String description;
    public String contact;
    public String empcode;
    public String image;

    public Employee(String id, String name, String category, String categoryid, String address, String description, String contact, String empcode, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.categoryid = categoryid;
        this.address = address;
        this.description = description;
        this.contact = contact;
        this.empcode = empcode;
        this.image = image;
    }

    public Employee() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
