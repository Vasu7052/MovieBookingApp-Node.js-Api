package com.example.vasu.moviebookingapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vasu on 04-10-2017.
 */

public class Users {

    @SerializedName("_id")
    private String _id;

    @SerializedName("name")
    private String name ;

    @SerializedName("email")
    private String email ;

    @SerializedName("password")
    private String password ;

    @SerializedName("type")
    private String type ;

    @SerializedName("create_date")
    private String create_date ;

    public Users(){

    }

    public Users(String _id, String name,String email, String password,String type, String create_date){
        this._id = _id;
        this.name = name ;
        this.email = email ;
        this.password = password ;
        this.type = type ;
        this.create_date = create_date ;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

}
