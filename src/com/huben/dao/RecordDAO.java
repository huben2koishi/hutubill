package com.huben.dao;

import com.huben.entity.Record;
import com.huben.utils.DBUtil;
import com.huben.utils.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koishi
 */
public class RecordDAO {
    public void add(Record record) {
        String sql = "insert into record values(null,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                record.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Record record) {
        String sql = "update record set spend= ?, cid= ?, comment =?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.setInt(5, record.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from record where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Record get(int id) {
        Record record = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from record where id = " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                record = new Record();
                record.setSpend(rs.getInt("spend"));
                record.setCid(rs.getInt("cid"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                record.setId(id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return record;
    }

    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Record> list(int start, int count) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();

                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
                record.setCid(rs.getInt("cid"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }


    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from record";

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

    public List<Record> list(int cid) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where cid = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Record record = new Record();

                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
                record.setCid(cid);
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public List<Record> listToday(){
        return list(DateUtil.today());
    }

    public List<Record> list(java.util.Date day) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date =?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();

                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
                record.setCid(rs.getInt("cid"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }

    public List<Record> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }

    public List<Record> list(java.util.Date start, java.util.Date end) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date >=? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record();

                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
                record.setCid(rs.getInt("cid"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }
}