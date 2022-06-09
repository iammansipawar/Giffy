package com.example.giffy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CategoryModel implements Parcelable {

    private String name;
    private String address;
    private String image;
    private float delivery_charge;
    private Hours hours;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

//    public static Creator<CategoryModel> getCREATOR() {
//
//    };

    private List<Menu> menus;

    protected CategoryModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        image = in.readString();
        delivery_charge = in.readFloat();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeFloat(delivery_charge);
        dest.writeTypedList(menus);
    }
}
