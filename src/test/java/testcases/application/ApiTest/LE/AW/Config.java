package testcases.application.ApiTest.LE.AW;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static utils.ConfigGlobal.getExcelPath;

/**
 * Define the ConfigGlobal of test script.
 */


public  class Config {

    public static final String excelPath = getExcelPath("Testcase_API.xls");


    public static final String serverDomain = "movie.douban.com";
    public static final String port = "";
    public static final String API_server = "http://"+ serverDomain;
    public static final String username = "xxx";
    public static final String password = "xxx";

    public static final String username_Provider = "xxxx";
    public static final String username_Admin = "xxx";
    public static final String username_ORG = "xxxxx";


    public static final JSONObject login_param;
    static
    {
        login_param = new JSONObject();
        login_param.put("password", password);
        login_param.put("username", username);
        login_param.put("API_name_login", "xxxxxxxxxxxxxxxxxx");
    }


    public static final String NAME_MYSQL = ":3306/db_oauth";
    public static final String DB_IP = "10.0.xx.xx";
    public static final String USER_MYSQL = "root";
    public static final String PWD_MYSQL = "xxxxxx";
    public static final String PORT_MYSQL = "3306";

    public static final Map<String, String> DB_conn;
    static
    {
        DB_conn = new HashMap<String, String>();

        DB_conn.put("DB_server", DB_IP);
        DB_conn.put("DB_port", PORT_MYSQL);
        DB_conn.put("DB_name", NAME_MYSQL);
        DB_conn.put("username", USER_MYSQL);
        DB_conn.put("password", PWD_MYSQL);
    }

}
