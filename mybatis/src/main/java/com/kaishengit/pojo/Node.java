package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Node implements Serializable{

    private static final long serialVersionUID = -7559050945596290365L;
    private Integer id;
    private String nodename;

    public Node() {}

    public Node(String nodename) {
        this.nodename = nodename;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", nodename='" + nodename + '\'' +
                '}';
    }
}
