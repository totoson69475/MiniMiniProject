package classes;

abstract public class User {

    protected String id;
    protected String password;
    protected String name;
    protected String phoneNumber;
    protected String email;
    protected String address;
    protected String department;


    // 생성자
    public User(String id, String password,String name, String phoneNumber, String email, String address, String department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.department = department;
    }
    User(){};

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this. id = id;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    

    public void viewUserInfo() {

    }

    public void editUserInfo() {

    }

}