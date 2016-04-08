package com.bit.learning.hive.jdbc;

import org.apache.hive.jdbc.HiveStatement;
import org.junit.Test;

import java.sql.*;


public class HiveJdbcTest {

    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    private static final String CONN_URL = "jdbc:hive2://localhost:10000/default";

    private static final String USER_NAME = "yuzt";

    private static final String PASS_WORD = "";

    private volatile boolean isDone = false;

    static {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQueryWithExecute() throws SQLException {
        runExecute("select * from t1");
    }

    @Test
    public void testCreateWithExecute() throws SQLException {
        runExecute("create table t2 as select * from t1");
    }


    @Test
    public void testLoadWithExecute() throws SQLException {
        runExecute("load data local inpath '/home/yuzt/krb5.conf' into table t2");
    }

    @Test
    public void testCreateDatabase() throws SQLException {
        runExecute("create database db_2");
    }

    @Test
    public void testDropDatabase() throws SQLException {
        runExecute("drop database db_2");
    }

    @Test
    public void testCreateTable() throws SQLException {
        runExecute("create table t3 (line string)");
    }

    @Test
    public void testDropTable() throws SQLException {
        runExecute("drop table t3");
    }

    @Test
    public void testMRIsKickedOff() throws SQLException {
        runExecuteWithLog("select count(1) from t2");
    }

    @Test
    public void testWithoutMRIsKickedOff() throws SQLException {
        runExecuteWithLog("select * from t1 join t2 on t1.line = t2.line ");
    }

    @Test
    public void testCreateFunctionWithUnsafeCode() throws SQLException {
        String sql = "create function x100 as 'com.bit.learning.hive.udf.LengthUDF' using jar 'hdfs://hadoop.bit.com:9000/user/learning.hive.0.14.0-1.0-SNAPSHOT.jar'";
        createFunction(sql);
    }

    @Test
    public void testCreateFunctionWithSafeCode() throws SQLException {
        String sql = "create function c100 as 'com.jd.hive.udf.LengthUDF' using jar 'hdfs://hadoop.bit.com:9000/user/hive.udf.safe-1.0-SNAPSHOT.jar'";
        createFunction(sql);
    }


    private void createFunction(String sql) throws SQLException {
        Connection conn = null;
        HiveStatement hstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASS_WORD);
            Statement stmt = conn.createStatement();
            if (!(stmt instanceof HiveStatement)) {
                throw new SQLException("The Statement is of HiveStatement");
            }

            hstmt = (HiveStatement) stmt;
            hstmt.execute("use db_1");
            boolean success = hstmt.execute(sql);
//            if (!success) {
//                throw new SQLException("创建失败");
//            }
        } catch(Exception e) {
            System.out.println("函数创建失败");
            throw new SQLException(e);
        }finally {

            if (rs != null) {
                rs.close();
            }

            if (hstmt != null) {
                hstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }


    private void runExecute(String sql) throws SQLException {
        Connection conn = null;
        HiveStatement hstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASS_WORD);
            Statement stmt = conn.createStatement();
            if (!(stmt instanceof HiveStatement)) {
                throw new SQLException("The Statement is of HiveStatement");
            }

            hstmt = (HiveStatement) stmt;
            hstmt.execute("use db_1");
            hstmt.execute(sql);
            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getObject(1));
                }
            }
        } finally {

            if (rs != null) {
                rs.close();
            }

            if (hstmt != null) {
                hstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }


    private void runExecuteWithLog(String sql) throws SQLException {
        Connection conn = null;
        HiveStatement hstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASS_WORD);
            Statement stmt = conn.createStatement();
            if (!(stmt instanceof HiveStatement)) {
                throw new SQLException("The Statement is of HiveStatement");
            }

            hstmt = (HiveStatement) stmt;

            final HiveStatement innerHStmt = hstmt;


            Thread t = new Thread(new LogTask(hstmt));
            t.start();

            hstmt.execute("use db_1");
            rs = hstmt.executeQuery(sql);


            if (rs != null) {
                while (rs.next()) {
                    System.out.println(rs.getObject(1));
                }
            }

            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {

            isDone = true;

            if (rs != null) {
                rs.close();
            }

            if (hstmt != null) {
                hstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }
}
