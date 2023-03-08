package com.Youtube.Models;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private ArrayList<Video> toWatchList;
    private ArrayList<Video> finishedList;
}
