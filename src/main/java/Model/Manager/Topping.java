/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Topping {

    private int idTopping;
    private String nameTopping;
    private int priceTopping;
    private String imageTopping;
    private boolean statusTopping;

    public Topping() {
    }

    public Topping(int idTopping, String nameTopping, int priceTopping, String imageTopping, boolean statusTopping) {
        this.idTopping = idTopping;
        this.nameTopping = nameTopping;
        this.priceTopping = priceTopping;
        this.imageTopping = imageTopping;
        this.statusTopping = statusTopping;
    }

    public Topping(String nameTopping, int priceTopping, String imageTopping, boolean statusTopping) {
        this.nameTopping = nameTopping;
        this.priceTopping = priceTopping;
        this.imageTopping = imageTopping;
        this.statusTopping = statusTopping;
    }

    public int getIdTopping() {
        return idTopping;
    }

    public void setIdTopping(int idTopping) {
        this.idTopping = idTopping;
    }

    public String getNameTopping() {
        return nameTopping;
    }

    public void setNameTopping(String nameTopping) {
        this.nameTopping = nameTopping;
    }

    public int getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(int priceTopping) {
        this.priceTopping = priceTopping;
    }

    public String getImageTopping() {
        return imageTopping;
    }

    public void setImageTopping(String imageTopping) {
        this.imageTopping = imageTopping;
    }

    public boolean isStatusTopping() {
        return statusTopping;
    }

    public void setStatusTopping(boolean statusTopping) {
        this.statusTopping = statusTopping;
    }

    @Override
    public String toString() {
        return "Topping{" + "idTopping=" + idTopping + ", nameTopping=" + nameTopping + ", priceTopping=" + priceTopping + ", imageTopping=" + imageTopping + ", statusTopping=" + statusTopping + '}';
    }
    
    
}
