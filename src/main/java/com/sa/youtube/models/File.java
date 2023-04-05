package com.sa.youtube.models;

import jakarta.persistence.Embeddable;

import java.io.FileInputStream;

@Embeddable
public class File {
    private FileInputStream fileName;
}
