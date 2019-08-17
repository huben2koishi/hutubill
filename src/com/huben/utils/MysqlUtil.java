package com.huben.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author koishi
 */
public class MysqlUtil {
    public static void backup(String mysqlPath, String backupFile) throws IOException {
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s  -hlocalhost -P%d  %s -r \"%s\"";
        String command = String.format(commandFormat, mysqlPath, DBUtil.username, DBUtil.password, DBUtil.port, DBUtil.database, backupFile);
        Runtime.getRuntime().exec(command);
    }

    public static void recover(String mysqlPath, String recoverFile) {
        try {
            String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s   %s ";
            String command = String.format(commandFormat, mysqlPath, DBUtil.username, DBUtil.password,
                    DBUtil.database);
            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuffer sb = new StringBuffer();
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), StandardCharsets.UTF_8));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
