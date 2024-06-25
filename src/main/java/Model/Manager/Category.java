/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Category {
    private int idCategory;
    private String nameCategory;
    private boolean statusCategory;

    public Category() {
    }

    public Category(int idCategory, String nameCategory, boolean statusCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.statusCategory = statusCategory;
    }

    public Category(String nameCategory, boolean statusCategory) {
        this.nameCategory = nameCategory;
        this.statusCategory = statusCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public boolean isStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(boolean statusCategory) {
        this.statusCategory = statusCategory;
    }

    @Override
    public String toString() {
        return "Category{" + "idCategory=" + idCategory + ", nameCategory=" + nameCategory + ", statusCategory=" + statusCategory + '}';
    }

    
}
