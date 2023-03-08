package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private ArrayList<Video> toWatchList;
    private ArrayList<Video> finishedList;
}
