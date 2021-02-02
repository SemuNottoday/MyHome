package com.example.myrent;

public class FavoriteClass
{
    private String UserId;
    private String HouseId;
    private String FavoId;
    private String Where;
    private String Price;

    public FavoriteClass() {
    }

    public FavoriteClass(String userId, String houseId, String favoId, String where, String price) {
        UserId = userId;
        HouseId = houseId;
        FavoId = favoId;
        Where = where;
        Price = price;
    }

    public String getWhere() {
        return Where;
    }

    public void setWhere(String where) {
        Where = where;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

    public String getFavoId() {
        return FavoId;
    }

    public void setFavoId(String favoId) {
        FavoId = favoId;
    }
}


