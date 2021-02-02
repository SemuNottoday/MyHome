package com.example.myrent;

public class RenterProfile
{

    private String username;
    private String email;
    private String bed;
    private String children;
    private String late;
    private String power;
    private String with;
    private String password;
    private Integer price;
    private String where;
    private String id;
    private String phone;
    private String latitude;
    private String longtitude;

    public RenterProfile() {
    }

    public RenterProfile( String id,String username, String email,String password, String bed, String children,
                         String late, String power, String with,
                         Integer price, String where, String phone, String latitude, String longtitude) {
        this.username = username;
        this.email = email;
        this.bed = bed;
        this.children = children;
        this.late = late;
        this.power = power;
        this.with = with;
        this.password = password;
        this.price = price;
        this.where = where;
        this.id = id;
        this.phone = phone;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWith() {
        return with;
    }

    public void setWith(String with) {
        this.with = with;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
