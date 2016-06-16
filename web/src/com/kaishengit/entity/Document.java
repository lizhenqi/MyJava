package com.kaishengit.entity;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Document {
    private Integer id;
    private String filename;
    private String md5;
    private String savename;
    private String extname;
    private Long size;
    private String dispsize;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getDispsize() {
        return dispsize;
    }

    public void setDispsize(String dispsize) {
        this.dispsize = dispsize;
    }

}
