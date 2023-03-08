package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForumMessage extends Message{
    private String id;
    private Group group;
}
