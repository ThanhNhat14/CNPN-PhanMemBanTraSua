/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Utils.Employee;

/**
 *
 * @author admin
 */
public enum AccountPermission {
    MANAGER("manager","Quản lí",3),
    STAFF("staff","Nhân viên",2),
    Customer("customer","Khách hàng",1),
    INACTIVE("inactive", "Nghỉ việc",0);
    private String id, name;
    private int priority;

    AccountPermission(String id, String name, int priority){
        this.id=id;
        this.name=name;
        this.priority=priority;    
    }
    
    public static AccountPermission getByID(String id){
        for(AccountPermission e : values()){
            if(e.id.equals(id)){
                return e;
            }
        }
            return STAFF;
    }
    
    public static AccountPermission getByName(String name){
         for(AccountPermission e : values()){
            if(e.name.equals(name)){
                return e;
            }
        }
            return STAFF;
    }
       
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int compare(AccountPermission other){
        return priority - other.priority;
    }
}
