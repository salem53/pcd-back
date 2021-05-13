package com.pcd.freelance.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table(name = "image_table")
public class uploadFile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Lob
    @Column(name = "picByte")   //, length = 1000)
    private byte[] picByte;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "certificationPath")
    private String certificationPath;
    public uploadFile() {
    }

    public uploadFile(byte[] picByte, String name, String type, String certificationPath) {
        this.picByte = picByte;
        this.name = name;
        this.type = type;
        this.certificationPath = certificationPath;
    }

    public uploadFile(byte[] picByte, String name, String type) {
        this.picByte = picByte;
        this.name = name;
        this.type = type;
    }

    public uploadFile(String name, String type, String certificationPath) {
        this.name = name;
        this.type = type;
        this.certificationPath = certificationPath;
    }


}
