package com.example.myrent;

import android.net.Uri;

public class ImageConstructor
{
    private String uploadid;
    private Uri imageurl;
    private String houseid;

    public ImageConstructor() {
    }

    public ImageConstructor(String uploadid, Uri imageurl, String houseid) {
        this.uploadid = uploadid;
        this.imageurl = imageurl;
        this.houseid = houseid;
    }

    public String getUploadid() {
        return uploadid;
    }

    public void setUploadid(String uploadid) {
        this.uploadid = uploadid;
    }

    public Uri getImageurl() {
        return imageurl;
    }

    public void setImageurl(Uri imageurl) {
        this.imageurl = imageurl;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }
}
