package com.bijendra.myswipedeck.myswipedeck;

/**
 * Created by Newdream on 03-Dec-16.
 */

public class MyCardInfo {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    String title;
    String desc;
    int imageResId;
    public MyCardInfo(String title,String desc,int imageResId)
    {
        this.title=title;
        this.desc=desc;
        this.imageResId=imageResId;

    }

}
