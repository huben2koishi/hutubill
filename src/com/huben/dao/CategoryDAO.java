package com.huben.dao;

import com.huben.entity.Category;
import com.huben.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koishi
 */
public class CategoryDAO {
    public void add(Category category) {
        String sql = "insert into category values(null, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        String sql = "update category set name= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from category where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category category = new Category();

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                category.setName(rs.getString(2));
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from category";

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
