package com.huben.service;

import com.huben.dao.ConfigDAO;
import com.huben.entity.Config;

/**
 * @author koishi
 */
public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    static ConfigDAO dao = new ConfigDAO();

    static {
        init();
    }

    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    private static void init(String key, String value) {
        System.out.println("service"+key+"->"+value);
        Config config = dao.getByKey(key);
        System.out.println(config);
        if (config.getValue() == null) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            System.out.println(c);
            dao.add(c);
        }
        System.out.println(config);
    }

    public String get(String key){
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    public void update(String key,String value){
        System.out.println(key+"->"+value);
        Config config = dao.getByKey(key);
        config.setValue(value);
        System.out.println(config);
        dao.update(config);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
