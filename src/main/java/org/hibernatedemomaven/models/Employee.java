package org.hibernatedemomaven.models;

import javax.persistence.*;

@Entity(name = "employees")
public class Employee {
    @Id
    @EmbeddedId
    private Name fullName;
    private String extension;
    private String email;
    private String officeCode;
    private String jobTitle;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street_name")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city_name")),
            @AttributeOverride(name = "state", column = @Column(name = "home_state_name")),
    })
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "office_street_name")),
            @AttributeOverride(name = "city", column = @Column(name = "office_city_name")),
            @AttributeOverride(name = "state", column = @Column(name = "office_state_name")),
    })
    private Address officeAddress;

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getAddress() {
        return homeAddress;
    }

    public void setAddress(Address address) {
        this.homeAddress = address;
    }

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", fullName=" + fullName +
                ", extension='" + extension + '\'' +
                ", email='" + email + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}