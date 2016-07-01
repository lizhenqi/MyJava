package com.kaishengit.service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/1.
 */
public class BookService {

    private String bookName;
    private Integer num;
    private List<String> lists;
    private Set<String> sets;
    private Map<String,Object> maps;
    private Properties properties;//也是一种特殊的map。


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }



    public void showBook() {
        System.out.println("BookService{" +
                "bookName='" + bookName + '\'' +
                ", num=" + num +
                ", lists=" + lists +
                ", sets=" + sets +
                ", maps=" + maps +
                ", properties=" + properties +
                '}');
    }

}
