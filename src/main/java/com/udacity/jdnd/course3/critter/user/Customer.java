package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String phoneNumber;
    private String notes;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;


    public void addPet(Pet pet) {
        if(this.pets == null){
            this.pets = new ArrayList<>();
        }
        this.pets.add(pet);
    }


    public Customer() {
    }

    public Customer(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", pets=" + pets +
                '}';
    }
}
