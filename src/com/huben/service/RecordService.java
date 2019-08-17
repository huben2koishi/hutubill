package com.huben.service;

import com.huben.dao.RecordDAO;
import com.huben.entity.Category;
import com.huben.entity.Record;

import java.util.Date;

/**
 * @author koishi
 */
public class RecordService {
    RecordDAO dao = new RecordDAO();

    public void add(int spend, Category category , String comment, Date date){
        Record r = new Record();
        r.setSpend(spend);
        r.setCid(category.getId());
        r.setComment(comment);
        r.setDate(date);

        dao.add(r);
    }
}
