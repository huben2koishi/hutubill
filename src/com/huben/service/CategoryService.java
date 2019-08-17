package com.huben.service;

import com.huben.dao.CategoryDAO;
import com.huben.dao.RecordDAO;
import com.huben.entity.Category;
import com.huben.entity.Record;

import java.util.List;

/**
 * @author koishi
 */
public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDAO = new RecordDAO();

    public List<Category> list() {
        List<Category> cs = categoryDAO.list();
        for (Category c : cs) {
            List<Record> rs = recordDAO.list(c.getId());
            c.setRecordNumber(rs.size());
        }
        cs.sort((c1, c2) -> c2.getRecordNumber() - c1.getRecordNumber());

        return cs;
    }

    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }

    public void update(int id,String name){
        Category c = new Category();
        c.setId(id);
        c.setName(name);
        categoryDAO.update(c);
    }

    public void delete(int id){
        categoryDAO.delete(id);
    }
}
