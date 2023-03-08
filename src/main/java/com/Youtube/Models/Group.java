package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    private String id;
    private String name;
    private String description;
    private ArrayList<User> userList;
    private ArrayList<Video> videoList;
    private ArrayList<ForumMessage> forumMessages;
}
