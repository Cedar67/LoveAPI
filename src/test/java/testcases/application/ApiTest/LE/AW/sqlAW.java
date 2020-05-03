package testcases.application.ApiTest.LE.AW;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import static testcases.application.ApiTest.LE.AW.Config.DB_conn;

/**
 * Define the public method of Selenium test script.
 */



public class sqlAW {

    public static void sqlDeleteTableData(String ApiName) throws InterruptedException, IOException {

        sqlExecute(DB_conn, "TRUNCATE TABLE `" + getDBtableName(ApiName) + "`");
    }


    public static void sqlDeleteTableDataByTable(String TableName) throws InterruptedException, IOException {

        sqlExecute(DB_conn, "TRUNCATE TABLE `" + TableName + "`");
    }


    public static String getDBtableName(String ApiName){

        if(ApiName.contains("label")){
            return "sp_label_group";

        }else if(ApiName.contains("login")){
            return "sp_us_user";

        }else if(ApiName.contains("registration")){
            return "sp_us_user";

        }else if(ApiName.contains("enterprise")){
            return "sp_enterprise";

        }else if(ApiName.contains("enterprise")){
            return "sp_enterprise";

        }else if(ApiName.contains("apply")){
            return "sp_settle_apply";

        }else{
            return "-1";

        }
    }

    public static String sqlSetDefaultTimeByID(String ApiName, String targetID)
            throws InterruptedException, IOException {

        String DBtableName = getDBtableName(ApiName);

        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        String fieldValue = null;

        String sqlSetCreateTime = "UPDATE "+DBtableName+" SET create_time = '2000-11-11 00:00:00' WHERE id = '"+targetID+"'";
        String sqlSetUpdateTime = "UPDATE "+DBtableName+" SET update_time = '2000-11-11 00:00:00' WHERE id = '"+targetID+"'";

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection("jdbc:mysql://" +
                            DB_conn.get("DB_server").toString() +
                            DB_conn.get("DB_name").toString(),
                    DB_conn.get("username").toString(),
                    DB_conn.get("password").toString());

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();

            stmt.execute(sqlSetCreateTime);
            stmt.execute(sqlSetUpdateTime);
//            stmt.execute(sqlSetRecommendTime);

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
        return fieldValue;
    }

    public static String sqlUpdateCreateValue(String DBtableName, String field, String value, String targetID)
            throws InterruptedException, IOException {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        String fieldValue = null;

        String sqlSetId = "UPDATE "+DBtableName+" SET id = "+targetID+" WHERE "+field+" = '"+value+"'";
//        String sqlSetCreateTime = "UPDATE "+DBtableName+" SET create_time = '2000-11-11 00:00:00' WHERE id = '"+targetID+"'";
//        String sqlSetUpdateTime = "UPDATE "+DBtableName+" SET update_time = '2000-11-11 00:00:00' WHERE id = '"+targetID+"'";
//        String sqlSetRecommendTime = "UPDATE "+DBtableName+" SET recommend_time = '2000-11-11 00:00:00' WHERE id = '888888'";

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection("jdbc:mysql://" +
                            DB_conn.get("DB_server").toString() +
                            DB_conn.get("DB_name").toString(),
                    DB_conn.get("username").toString(),
                    DB_conn.get("password").toString());

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();

            stmt.execute(sqlSetId);
//            stmt.execute(sqlSetCreateTime);
//            stmt.execute(sqlSetUpdateTime);
//            stmt.execute(sqlSetRecommendTime);

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
        return fieldValue;
    }

    public static void sqlUpdateId(String ApiName, String newId, String oldId)
            throws InterruptedException, IOException {
//        UPDATE sp_news_info SET id = 999999 WHERE id = 42458
        sqlExecuteUpdate(getDBtableName(ApiName), "id", oldId, "id", newId);

    }

    public static void sqlUpdateById(String ApiName, String searchValue,String setField, String setValue)
            throws InterruptedException, IOException {

        sqlExecuteUpdate(getDBtableName(ApiName), "id", searchValue, setField, setValue);

    }

    public static String sqlExecute(Map DB_conn, String sql)
            throws InterruptedException, IOException {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        String fieldValue = null;

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection("jdbc:mysql://" +
                            DB_conn.get("DB_server").toString() +
                            DB_conn.get("DB_name").toString(),
                    DB_conn.get("username").toString(),
                    DB_conn.get("password").toString());

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();

            stmt.execute(sql);

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
        return fieldValue;
    }

    public static String sqlExecuteBatch(Map DB_conn, String[][] sql)
            throws InterruptedException, IOException {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        String fieldValue = null;
        String connInfo = null;

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            connInfo = "jdbc:mysql://" +
                    DB_conn.get("DB_server").toString() +
                    DB_conn.get("DB_name").toString() ;


//            conn = DriverManager.getConnection(connInfo,
//                    DB_conn.get("username").toString(),
//                    DB_conn.get("password").toString());


            Properties properties = new Properties();
            properties.setProperty("useSSL", "false");
            properties.setProperty("user", DB_conn.get("username").toString());
            properties.setProperty("password", DB_conn.get("password").toString());

            conn = DriverManager.getConnection(connInfo, properties);

            // 执行查询
//            System.out.println(connInfo);
            stmt = conn.createStatement();

            for(int i=0;i<sql.length;i++) {
                for (int j = 0; j < sql[i].length; j++) {
                    stmt.execute(sql[i][j]);
                }
            }

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
        return fieldValue;
    }

    public static void sqlExecuteUpdate(String table, String searchField, String searchValue, String setField, String setValue)
            throws InterruptedException, IOException {

//        UPDATE sp_news_info SET id = 999999 WHERE id = 42458
        sqlExecute(DB_conn,
                "UPDATE `" + table + "` SET `" + setField + "` = '" + setValue + "' WHERE `" + searchField + "` = '" + searchValue + "'");
    }

    public static String sqlExecuteQuery(Map DB_conn, String sql, String field)
            throws InterruptedException, IOException {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        String fieldValue = null;

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection("jdbc:mysql://" +
                    DB_conn.get("DB_server").toString() +
                    DB_conn.get("DB_name").toString(),
                    DB_conn.get("username").toString(),
                    DB_conn.get("password").toString());

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();

//            sql = "SELECT * FROM `smartPark_portal`.`sp_news_info` WHERE `title` LIKE '%ss%'";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
//                `id` int(11) NOT NULL AUTO_INCREMENT,
//                `title` varchar(255) DEFAULT NULL COMMENT '标题',
//                `read_number` int(11) DEFAULT '0' COMMENT '阅读量',
//                `digest` varchar(255) DEFAULT NULL COMMENT '摘要',
//                `content` text COMMENT '内容',
//                `img` varchar(255) DEFAULT NULL COMMENT '标题图片',
//                `status` int(11) DEFAULT '0' COMMENT '状态（-1 删除 0 发布 1 置顶）',
//                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
//                `create_by` int(11) DEFAULT NULL COMMENT '创建人',
//                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
//                `update_by` int(11) DEFAULT NULL COMMENT '更新人',
//                `recommend_time` datetime DEFAULT NULL COMMENT '置顶时间',
//                `recommend_by` int(11) DEFAULT NULL COMMENT '置顶人',
//                `remark` varchar(255) DEFAULT NULL COMMENT '备注',
//                `top_img` varchar(255) DEFAULT NULL COMMENT '置顶图片',
                switch (field) {
                    case "id":
                        fieldValue  = rs.getInt("id")+"";
                        break;
                    case "title":
                        fieldValue = rs.getString("title");
                        break;
                    default:
                        fieldValue  = rs.getInt("id")+"";
                        break;
                }

                // 输出数据
//                System.out.print("fieldValue: " + fieldValue);
//                System.out.print("\n");
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
//        System.out.println("Goodbye!");
        return fieldValue;
    }

    public static String sqlQueryByField(String table, String title, String field, String search)
            throws InterruptedException, IOException {
        String fieldValue = null;

        fieldValue = sqlExecuteQuery(DB_conn, "SELECT * FROM `" + table + "` WHERE `" + title + "` LIKE '%" + search + "%'", field);

        return fieldValue;
    }

    public static String sqlQueryByTitleField(String table, String field, String search)
            throws InterruptedException, IOException {
        String fieldValue = null;

        fieldValue = sqlExecuteQuery(DB_conn, "SELECT * FROM `" + table + "` WHERE `title` LIKE '%" + search + "%'", field);

        return fieldValue;
    }


    public static void DeleteValueByField(String DBtableName, String field, String value)
            throws InterruptedException, IOException {
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;

//        DELETE FROM sp_news_info WHERE title = 'CedarTestTitle115'
        String sql = "DELETE FROM "+DBtableName+" WHERE "+field+" = '"+value+"'";

        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection("jdbc:mysql://" +
                            DB_conn.get("DB_server").toString() +
                            DB_conn.get("DB_name").toString(),
                    DB_conn.get("username").toString(),
                    DB_conn.get("password").toString());

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();

            stmt.execute(sql);

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static String DeleteValueById(Map param, String KeyValue)
            throws IOException, InterruptedException {

        String ApiName = param.get("ApiName").toString();
        String DBtableName = getDBtableName(ApiName);

        DeleteValueByField(DBtableName, "id", KeyValue);
        return "1";

    }

    public static String DeleteValueByTitle(Map param)
            throws IOException, InterruptedException {

        String KeyField = "";
        String KeyValue = null;
        String ApiName = param.get("ApiName").toString();
        String DBtableName = getDBtableName(ApiName);

        JSONObject RequestBody = JSON.parseObject(param.get("RequestBody").toString());

        if(ApiName.contains("label")){
            KeyField = "name";

        }else if(ApiName.contains("notice")){
            KeyField = "title";

        }else if(ApiName.contains("policy")){
            KeyField = "title";

        }else if(ApiName.contains("resources")){
            KeyField = "name";

        }else if(ApiName.contains("enterprise")){
            KeyField = "name";

        }else{
            return "-1";

        }

        KeyValue = RequestBody.getString(KeyField);

        DeleteValueByField(DBtableName, KeyField, KeyValue);
        return "1";

    }


}
