package com.bit.learning.hive.jdbc;

import org.apache.hive.jdbc.HiveStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yuzt on 16-1-29.
 */
public class LogTask implements Runnable {
    private final HiveStatement hstmt;

    public LogTask(HiveStatement hstmt) throws SQLException {
        this.hstmt = hstmt;
    }

    public void run() {
        int i = 0;
        while (i < 10) {
            i++;
            try {
                List<String> queryLogs = hstmt.getQueryLog(true, 20);
                if (queryLogs != null || queryLogs.size() > 0) {
                    for (String queryLog : queryLogs) {
                        System.out.println("---------------->>>>>>>>>>>>>>" + queryLog);
                    }
                }
            } catch (Exception e) {

            }
            try {
                Thread.sleep(6*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
