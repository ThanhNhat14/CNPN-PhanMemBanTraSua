/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Supplier {
    private int idSupplier;
    private String nameSupplier;
    private String representPerson;
    private String numberPhoneSupplier;
    private String description;
    private String address;
    private boolean statusSupplier;

    public Supplier() {
    }

    public Supplier(int idSupplier, String nameSupplier, String representPerson, String numberPhoneSupplier, String description, String address, boolean statusSupplier) {
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.representPerson = representPerson;
        this.numberPhoneSupplier = numberPhoneSupplier;
        this.description = description;
        this.address = address;
        this.statusSupplier = statusSupplier;
    }

    public Supplier(String nameSupplier, String representPerson, String numberPhoneSupplier, String description, String address, boolean statusSupplier) {
        this.nameSupplier = nameSupplier;
        this.representPerson = representPerson;
        this.numberPhoneSupplier = numberPhoneSupplier;
        this.description = description;
        this.address = address;
        this.statusSupplier = statusSupplier;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getRepresentPerson() {
        return representPerson;
    }

    public void setRepresentPerson(String representPerson) {
        this.representPerson = representPerson;
    }

    public String getNumberPhoneSupplier() {
        return numberPhoneSupplier;
    }

    public void setNumberPhoneSupplier(String numberPhoneSupplier) {
        this.numberPhoneSupplier = numberPhoneSupplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatusSupplier() {
        return statusSupplier;
    }

    public void setStatusSupplier(boolean statusSupplier) {
        this.statusSupplier = statusSupplier;
    }

    
}
