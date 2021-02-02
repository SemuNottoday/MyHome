package com.example.myrent;

public class OwnersProfile
{
    private String username;
    private String email;
    private String bed;
    private String children;
    private String late;
    private String power;
    private String live;
    private String password;
    private Integer price;
    private String where;
    private String Oid;
    private Integer phone;
    private String latitude;
    private String longtitude;

    public OwnersProfile() {
    }

    public OwnersProfile(String username, String email, String bed, String children,
                         String late, String power, String live, String password,
                         Integer price, String where, String oid, Integer phone, String latitude, String longtitude) {
        this.username = username;
        this.email = email;
        this.bed = bed;
        this.children = children;
        this.late = late;
        this.power = power;
        this.live = live;
        this.password = password;
        this.price = price;
        this.where = where;
        this.Oid = oid;
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
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

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }
}
