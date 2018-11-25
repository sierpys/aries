package com.alibaba.accessor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Accessor implements Access {
    //º”‘ÿ ˝æ›ø‚«˝∂Ø
    public String dbDriver;
    //mysql¡¨Ω”µÿ÷∑
    public String dbUrl;
    // ˝æ›ø‚√˚≥∆
    public String username;
    // ˝æ›ø‚√‹¬Î
    public String userPassword;

    private int retry = 0;

    public Connection connection = null;//ªÒ»°¡¨Ω”
    public Statement statement = null;//Ω´≤Œ ˝ªØµƒsql”Ôæ‰∑¢ÀÕµΩ ˝æ›ø‚
    public ResultSet resultSet = null;//µ√µΩΩ·π˚ºØ

    /**
     * ªÒ»°÷ÿ“™µƒ ˝æ›£¨¡¨Ω”±æµÿ ˝æ›ø‚
     *
     * @param dbDriver     String¿‡–Õ£¨ ˝æ›ø‚«˝∂Ø
     * @param dbUrl        String¿‡–Õ£¨ ˝æ›ø‚µÿ÷∑
     * @param username     String¿‡–Õ£¨”√ªß–’√˚
     * @param userPassword String¿‡–Õ£¨”√ªß√‹¬Î
     */
    public Accessor(String dbDriver, String dbUrl, String username, String userPassword) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.username = username;
        this.userPassword = userPassword;
        getConn(dbDriver, dbUrl, username, userPassword);//¡¨Ω” ˝æ›ø‚
    }

    /**
     * ªÒ»°÷ÿ“™µƒ ˝æ›£¨¡¨Ω”‘∂≥Ã ˝æ›ø‚
     *
     * @param dbDriver     String¿‡–Õ£¨ ˝æ›ø‚«˝∂Ø
     * @param username     String¿‡–Õ£¨”√ªß–’√˚
     * @param userPassword String¿‡–Õ£¨”√ªß√‹¬Î
     */
    public Accessor(String dbDriver, String username, String userPassword) {
        this.dbDriver = dbDriver;
        this.username = username;
        this.userPassword = userPassword;
        getRemoteConn(dbDriver, username, userPassword);//¡¨Ω” ˝æ›ø‚
    }

    /**
     * ªÒ»°±æµÿ ˝æ›ø‚µƒ¡¨Ω”
     *
     * @param dbDriver     String¿‡–Õ£¨ ˝æ›ø‚«˝∂Ø
     * @param dbUrl        String¿‡–Õ£¨ ˝æ›ø‚µÿ÷∑
     * @param username     String¿‡–Õ£¨”√ªß–’√˚
     * @param userPassword String¿‡–Õ£¨”√ªß√‹¬Î
     * @return ∑µªÿ¡¨Ω”
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConn(String dbDriver, String dbUrl, String username, String userPassword) {
        try {
            Class.forName(dbDriver); //÷∏∂®¡¨Ω”¿‡–Õ
            connection = DriverManager.getConnection(dbUrl, username, userPassword);//ªÒ»°¡¨Ω”
            System.out.println(" ˝æ›ø‚¡¨Ω”≥…π¶");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * ªÒ»°‘∂≥Ã ˝æ›ø‚µƒ¡¨Ω”
     *
     * @param dbDriver     String¿‡–Õ£¨ ˝æ›ø‚«˝∂Ø
     * @param username     String¿‡–Õ£¨”√ªß–’√˚
     * @param userPassword String¿‡–Õ£¨”√ªß√‹¬Î
     * @return ∑µªÿ¡¨Ω”
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getRemoteConn(String dbDriver, String username, String userPassword) {
        String remoteDatabaseUrl = "jdbc:mysql://172.26.93.87:3306/test";
        try {
            Class.forName(dbDriver); //÷∏∂®¡¨Ω”¿‡–Õ
            connection = DriverManager.getConnection(remoteDatabaseUrl, username, userPassword);//ªÒ»°¡¨Ω”
            System.out.println(" ˝æ›ø‚¡¨Ω”≥…π¶");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * ¥¥Ω®±Ì£¨œ»≈–∂œ±Ì «∑Ò¥Ê‘⁄£¨≤ª¥Ê‘⁄‘Ú¥¥Ω®±Ì
     *
     * @param createSql    String¿‡–Õ£¨“™¥¥Ω®µƒ±Ìµƒsql”Ôæ‰
     * @param databaseName String¿‡–Õ£¨“™¥¥Ω®µƒ±Ìµƒ ˝æ›ø‚µƒ√˚◊÷
     * @param username     String¿‡–Õ£¨“™¥¥Ω®µƒ±Ìµƒ√˚◊÷
     * @return 0 ¥¥Ω®≥…π¶£ª-1 ¥¥Ω® ß∞‹
     * @throws SQLException
     */
    public int createTable(String createSql, String databaseName, String username) {

        String tableName = createSql.split(" ")[2].split("\\(")[0];

        try {
            if (!isExistTable(tableName, databaseName, username)) {
                for (int i = 0; i <= retry; i++) {
                    statement = connection.createStatement();
                    if (statement.executeUpdate(createSql) == 0) {
                        return 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    /**
     * œÚ÷∏∂®±Ì÷–≤Â»Î ˝æ›
     *
     * @param insertSql String¿‡–Õ£¨≤Â»Î”Ôæ‰
     * @throws SQLException
     */
    public void insertRecord(String insertSql) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * œÚ÷∏∂®±Ì÷–≤È—Ø ˝æ›
     *
     * @param selectSql String¿‡–Õ£¨≤Â»Î”Ôæ‰
     * @throws SQLException
     */
    public ResultSet queryRecord(String selectSql) {

        try {
            statement = connection.createStatement();//¥¥Ω®“ª∏ˆstatement∂‘œÛœÚ ˝æ›ø‚∑¢ÀÕsql”Ôæ‰
            resultSet = statement.executeQuery(selectSql); //≤È—Ø ˝æ›
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    /**
     * …æ≥˝±Ì÷–µƒ ˝æ›
     *
     * @param deleteSql String¿‡–Õ£¨…æ≥˝”Ôæ‰
     * @throws SQLException
     */
    public void deleteRecord(String deleteSql) {

        try {
            statement = connection.createStatement();//¥¥Ω®“ª∏ˆstatement∂‘œÛœÚ ˝æ›ø‚∑¢ÀÕsql”Ôæ‰
            statement.executeUpdate(deleteSql);//…æ≥˝ ˝æ›
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ∏¸–¬±Ì÷–µƒ ˝æ›
     *
     * @param updateSql String¿‡–Õ£¨∏¸–¬”Ôæ‰
     * @throws SQLException
     */
    public void updateRecord(String updateSql) {
        try {
            statement = connection.createStatement();//¥¥Ω®“ª∏ˆstatement∂‘œÛœÚ ˝æ›ø‚∑¢ÀÕsql”Ôæ‰
            statement.executeUpdate(updateSql);//∏¸–¬ ˝æ›

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * œ»≈–∂œ±Ì «∑Ò“—¥Ê‘⁄£¨»Ù¥Ê‘⁄∑µªÿtrue,≤ª¥Ê‘⁄∑µªÿfalse
     *
     * @param tableName    String¿‡–Õ£¨∏¸–¬”Ôæ‰
     * @param databaseName String¿‡–Õ£¨∏¸–¬”Ôæ‰
     * @param username     String¿‡–Õ£¨∏¸–¬”Ôæ‰
     * @return »Áπ˚±Ì√˚“—¥Ê‘⁄£¨∑µªÿtrue£¨»Áπ˚±Ì√˚≤ª¥Ê‘⁄£¨∑µªÿfalse
     */
    public boolean isExistTable(String tableName, String databaseName, String username) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();//ªÒ»° ˝æ›ø‚÷–µƒœ‡πÿ–≈œ¢
            //ªÒ»°÷∏∂® ˝æ›ø‚÷–µƒÀ˘”–±Ì
            resultSet = databaseMetaData.getTables(databaseName, username, "%", new String[]{"TABLE"});

            while (resultSet.next()) {

                //≈–∂œ“™¥¥Ω®µƒ±Ì√˚ «∑Ò“—æ≠¥Ê‘⁄
                if (resultSet.getString("TABLE_NAME").equals(tableName)) {
                    return true;//»Áπ˚±Ì√˚“—¥Ê‘⁄£¨∑µªÿtrue
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;//»Áπ˚±Ì√˚≤ª¥Ê‘⁄£¨∑µªÿfalse
    }

    /**
     * ªÒ»°À˘¡¨Ω” ˝æ›ø‚µƒ ±º‰
     */
    public void getTimezone() {
        String sql = "SELECT NOW();";
        try {
            statement = connection.createStatement();//¥¥Ω®“ª∏ˆstatement∂‘œÛœÚ ˝æ›ø‚∑¢ÀÕsql”Ôæ‰
            resultSet = statement.executeQuery(sql);//≤È—Ø ±º‰

            while (resultSet.next()) {
                Date databaseDate = new Date(resultSet.getTimestamp("now()").getTime());//µ√µΩ ±º‰
                SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//…Ë÷√ ±º‰∏Ò Ω
                String databaseTime = formatTime.format(databaseDate);//Œ™ ±º‰∏≥”Ë∏Ò Ω
                System.out.println(databaseTime);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * πÿ±’ ˝æ›ø‚µƒ¡¨Ω”
     *
     * @throws SQLException
     */
    public void closeConnection() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    /**
     * @return  ‰≥ˆ≥…‘±±‰¡ø
     */
    @Override
    public String toString() {
        return "Accessor [dbDriver=" + dbDriver + ", dbUrl=" + dbUrl + ", username=" + username + ", userPassword="
                + userPassword + ", connection=" + connection + ", statement=" + statement + ", resultSet=" + resultSet
                + "]";
    }

}
