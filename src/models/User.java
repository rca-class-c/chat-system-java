package models;


public class User {
    public int userID;
    public String fname;
    public String lname;
    public String password;
    public String email;
    public String dob;
    public String createAt;
    public String updatedAt;
    public String username;
    public String gender;
    public int categoryID;
    public String status;

    public User(String fname, String lname, String password, String email, String dob, String username, String gender, int categoryID, String status) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.gender = gender;
        this.categoryID = categoryID;
        this.status = status;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
