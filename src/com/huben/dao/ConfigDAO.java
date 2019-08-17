package com.huben.dao;

import com.huben.entity.Config;
import com.huben.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author koishi
 */
public class ConfigDAO {
    public void add(Config config) {
        String sql = "INSERT INTO config VALUES (NULL,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                config.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Config config) {
        String sql = "UPDATE config SET key_=?,value=? WHERE id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.setInt(3, config.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "DELETE from config WHERE id=" + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Config get(int id) {
        Config config = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "SELECT * from config WHERE id=" + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                config = new Config();
                config.setKey(rs.getString("key_"));
                config.setValue(rs.getString("value"));
                config.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<>();
        String sql = "SELECT * FROM config ORDER BY id DESC LIMIT ?,?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Config config = new Config();
                config.setId(rs.getInt("id"));
                config.setKey(rs.getString("key_"));
                config.setValue(rs.getString("value"));
                configs.add(config);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configs;
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "SELECT COUNT(*) FROM config";

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public Config getByKey(String key) {
        Config config = new Config();
        String sql = "SELECT * FROM config WHERE key_=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                config.setId(rs.getInt("id"));
                config.setKey(key);
                config.setValue(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }
}
