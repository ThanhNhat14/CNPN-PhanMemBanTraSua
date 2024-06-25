/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Manager;

/**
 *
 * @author Admin
 */
public class Size {
    private int idSize;
    private String nameOfSize;

    public Size() {
    }

    public Size(int idSize, String nameOfSize) {
        this.idSize = idSize;
        this.nameOfSize = nameOfSize;
    }

    public Size(String nameOfSize) {
        this.nameOfSize = nameOfSize;
    }

    

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getNameOfSize() {
        return nameOfSize;
    }

    public void setNameOfSize(String nameOfSize) {
        this.nameOfSize = nameOfSize;
    }

   
    
    
    
}
