package com.kaishengit.dao;

import com.kaishengit.entity.Document;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Administrator on 2016/6/16.
 */
public class DocumentDao {

    public void insertFile(Document dd){
        String sql="INSERT into t_document(filename, savename, md5, extname, size, dispsize) VALUES(?,?,?,?,?,?)";
        Dbhelp.userUpdate(sql,dd.getFilename(),dd.getSavename(),dd.getMd5(),dd.getExtname(),dd.getSize(),dd.getDispsize());
    }

    public Document finByMd5(String md5) {
        String sql="select *from t_document where md5=?";
        return Dbhelp.userFind(sql, new BeanHandler<>(Document.class), md5);
    }
}
