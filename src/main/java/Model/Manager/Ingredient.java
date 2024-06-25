/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Ingredient {
    
    private int idIngredient;
    private String nameIngredient;
    private String unitIngredient;
    private float quantityIngredient;
    private boolean statusIngredient;

    public Ingredient() {
    }

    public Ingredient(int idIngredient, String nameIngredient, String unitIngredient, float quantityIngredient, boolean statusIngredient) {
        this.idIngredient = idIngredient;
        this.nameIngredient = nameIngredient;
        this.unitIngredient = unitIngredient;
        this.quantityIngredient = quantityIngredient;
        this.statusIngredient = statusIngredient;
    }

    public Ingredient(String nameIngredient, String unitIngredient, float quantityIngredient, boolean statusIngredient) {
        this.nameIngredient = nameIngredient;
        this.unitIngredient = unitIngredient;
        this.quantityIngredient = quantityIngredient;
        this.statusIngredient = statusIngredient;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public String getUnitIngredient() {
        return unitIngredient;
    }

    public void setUnitIngredient(String unitIngredient) {
        this.unitIngredient = unitIngredient;
    }

    public float getQuantityIngredient() {
        return quantityIngredient;
    }

    public void setQuantityIngredient(float quantityIngredient) {
        this.quantityIngredient = quantityIngredient;
    }

    public boolean isStatusIngredient() {
        return statusIngredient;
    }

    public void setStatusIngredient(boolean statusIngredient) {
        this.statusIngredient = statusIngredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "idIngredient=" + idIngredient + ", nameIngredient=" + nameIngredient + ", unitIngredient=" + unitIngredient + ", quantityIngredient=" + quantityIngredient + ", statusIngredient=" + statusIngredient + '}';
    }

    
}
