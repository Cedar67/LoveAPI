package utils.ApiAW;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.ConfigGlobal.MediaTypeJSON;
import static utils.ConfigGlobal.g_sessionID;
import static testcases.application.ApiTest.LE.AW.Config.*;




public class ApiBasicAW {

    public static org.slf4j.Logger log = LoggerFactory.getLogger(ApiBasicAW.class);
    public static OkHttpClient client = new OkHttpClient();

    public static String getUrl(String API_server, String Uri_server,String API_name, String API_param) throws InterruptedException, IOException {

        if(Uri_server !="" && API_param !=""){
            return API_server + "/" + API_name + "/" + Uri_server +"/"+ API_param;
        }

        if(Uri_server !="" && API_param ==""){
            return API_server + "/" + API_name + "/" + Uri_server;
        }

        if(Uri_server =="" && API_param !=""){

            if(API_param.contains("?")){
                return API_server + API_name + API_param;
            }

            return API_server + API_name +"/"+ API_param;
        }

//        return API_server + "/" + API_name;
        return API_server + API_name;

    }

    public static String getDatetime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");

        return dateFormat.format( now );
    }

    public static String getDatetimeForSQL(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format( now );
    }

    public static String getTime(){
        Date now = new Date();
        return String.valueOf(now.getTime());
    }

    public static Map assertMethod(Map Actual, Map Expected){

        String responseBody = Actual.get("responseBody").toString();
        String responseCode = Actual.get("responseCode").toString();
        String responseKeyword = null;

        JSONObject datas = JSON.parseObject(responseBody);
        responseKeyword = datas.get("code").toString();

        Actual.put("responseKeyword", responseKeyword);

        if(Expected.get("Expected").equals(responseKeyword) && responseCode.equals("200")){
            Actual.put("TestResult", "Pass");
        }else{
            Actual.put("TestResult", "Fail");
        }

//        assertEquals(Actual.get("responseBody"), Expected.get("Expected"));
//        assertEquals(Actual.get("responseCode"), "200");

        System.out.println("responseKeyword Expected   :" + Expected.get("Expected"));
        System.out.println("responseKeyword Actual   :" + Actual.get("responseKeyword"));
        System.out.println("responseCode Expected   :" + "200");
        System.out.println("responseCode Actual   :" + Actual.get("responseCode"));

//        assertEquals(Actual.get("TestResult"), "Pass");

        return Actual;
    }

    public static Map assertMethodUpdate2(Map Actual, Map Expected) {

        String responseBody = Actual.get("responseBody").toString();
        String responseCode = Actual.get("responseCode").toString();

        if(responseBody.contains("createTime")) {
            responseBody = regx(responseBody);
        }

        Actual.put("responseKeyword", responseBody);
        Actual.put("responseBody", responseBody);


        if(Expected.get("Expected").equals(responseBody) && responseCode.equals("200")){
            Actual.put("TestResult", "Pass");
        }else{
            Actual.put("TestResult", "Fail");
        }

        return Actual;
    }

    public static Map assertMethodUpdate(Map Actual, Map Expected) {

        String responseBody = Actual.get("responseBody").toString();
        String responseCode = Actual.get("responseCode").toString();
        String UriParam = Expected.get("UriParam").toString();
        String responseKeyword = null;
        String ExpectedUpdate = null;


        if(responseBody.contains("createTime")) {
            JSONObject responseBodyJSON = JSON.parseObject(responseBody);
            JSONObject bodyJSON = null;

            if (responseBody.contains("[")) {
                JSONArray listJSONArray = null;

                if (responseBody.contains("list")) {
                    String responseExpected = JSON.parseObject(responseBody).getString("response");
                    bodyJSON = JSON.parseObject(responseExpected);
                    listJSONArray = bodyJSON.getJSONArray("list");

                    int Num = listJSONArray.size();

                    for (int i = (Num - 1); i >= 0; i--) {
                        listJSONArray.getJSONObject(i).put("createTime", "973872000000");
                        listJSONArray.getJSONObject(i).put("updateTime", "973872000000");

                        if (listJSONArray.getJSONObject(i).containsKey("recommendTime")) {
                            listJSONArray.getJSONObject(i).put("recommendTime", "973872000000");
                        }
                    }

                    bodyJSON.put("list", listJSONArray);
                    responseBodyJSON.put("response", bodyJSON);
                    responseBody = responseBodyJSON.toJSONString();

                } else {
                    bodyJSON = JSON.parseObject(responseBody);
                    listJSONArray = bodyJSON.getJSONArray("response");

                    int Num = listJSONArray.size();

                    for (int i = (Num - 1); i >= 0; i--) {
                        listJSONArray.getJSONObject(i).put("createTime", "973872000000");
                        listJSONArray.getJSONObject(i).put("updateTime", "973872000000");

                        if (listJSONArray.getJSONObject(i).containsKey("recommendTime")) {
                            listJSONArray.getJSONObject(i).put("recommendTime", "973872000000");
                        }
                    }

                    bodyJSON.put("response", listJSONArray);
                    responseBody = bodyJSON.toString();
                }


            } else if (responseBody.contains("\"response\":{")) {

                responseBodyJSON = JSON.parseObject(responseBody);
                String responseExpected = responseBodyJSON.getString("response");
                bodyJSON = JSON.parseObject(responseExpected);

                bodyJSON.put("createTime", "973872000000");
                bodyJSON.put("updateTime", "973872000000");

                if (bodyJSON.containsKey("recommendTime")) {
                    bodyJSON.put("recommendTime", "973872000000");
                }

                responseBodyJSON.put("response", bodyJSON);
                responseBody = responseBodyJSON.toString();
            }
        }

        Actual.put("responseKeyword", responseBody);
        Actual.put("responseBody", responseBody);


        if(Expected.get("Expected").equals(responseBody) && responseCode.equals("200")){
            Actual.put("TestResult", "Pass");
        }else{
            Actual.put("TestResult", "Fail");
        }

//        assertEquals(Actual.get("responseBody"), Expected.get("Expected"));
//        assertEquals(Actual.get("responseCode"), "200");
//        assertEquals(Actual.get("TestResult"), "Pass");
//        assertEquals(responseBody, Expected.get("Expected"));

//        System.out.println("responseKeyword Expected   :" + Expected.get("Expected"));
//        System.out.println("responseKeyword Actual   :" + Actual.get("responseKeyword"));
//        System.out.println("responseCode Expected   :" + "200");
//        System.out.println("responseCode Actual   :" + Actual.get("responseCode"));



        return Actual;
    }

    public static String getToken(String API_server, String username, String password) throws InterruptedException, IOException {
        String resultBody;
        String returnValue;
        String resultCode;
        String resultTemp;
        String url = API_server + "admin/auth/token";

        JSONObject datas = new JSONObject();
        datas.put("password", password);
        datas.put("name", username);

        log.info("发送的数据为:" + datas.toJSONString());
        RequestBody body = RequestBody.create(MediaTypeJSON, datas.toJSONString());

        Request request = new Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .post(body)
                    .build();

        Response response = client.newCall(request).execute();
        resultCode = String.valueOf(response.code());

        if (response.isSuccessful()) {
            resultBody = response.body().string();
//            log.info("返回数据为：\n" + resultBody);

            if (JSON.parseObject(resultBody).containsKey("code")){
                resultTemp = JSON.parseObject(resultBody).get("code").toString();
                if (resultTemp.equals("2100")){
                    returnValue = JSON.parseObject(resultBody).getJSONObject("data").get("token").toString();
                    return returnValue;
                } else {
                    log.info("返回头：\n" + response.headers().toString() + "\n 返回信息：\n" + response.body().string());
                    return resultTemp;
                }
            }else {
                log.info("返回头：\n" + response.headers().toString() + "\n 返回信息：\n" + response.body().string());
                return resultCode;
            }

        } else {
            log.info("返回头：\n" + response.headers().toString() + "\n 返回信息：\n" + response.body().string());
            return resultCode;
        }

	}

    public static Map getByUrl(Map param, String sessionID) throws IOException, InterruptedException {

        Map<String, String> returnMap = new HashMap<String, String>();
        String API_server = param.get("API_server").toString();
        String API_name = param.get("API_name").toString();
        String UriParam = "";

        if (param.containsKey("UriParam")) {
            UriParam = param.get("UriParam").toString();
        }

        String url = ApiBasicAW.getUrl(API_server, "",API_name, UriParam);

        Request.Builder builder = new Request.Builder();

        if (param.containsKey("token")) {
            builder.addHeader("token", param.get("token").toString());
        }

        if (sessionID != ""){
            builder.addHeader("Cookie", sessionID);
        }

        Request request = builder.url(url).build();

        Response response = client.newCall(request).execute();

        String SessionID = getSessionIDFromResponse(response.headers());
        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());
        returnMap.put("sessionID", SessionID);

        return returnMap;

    }

    public static String getSessionIDFromUrl(String headers) throws InterruptedException, IOException {
        String SessionID = "";


            SessionID = headers.split(";")[0];



        return SessionID;

    }

    public static String getSessionIDFromResponse(Headers headers) throws InterruptedException, IOException {
        String SessionID = "";

        Map<String, List<String>> headersMap = headers.toMultimap();

        if (headersMap.containsKey("set-cookie")){
            SessionID = headers.get("set-cookie").split(";")[0];
        }

        if (headersMap.containsKey("cookie")){
            SessionID = headers.get("cookie");
        }

        return SessionID;

    }

    public static Map getCode(String API_Address, JSONObject login_conn) throws InterruptedException, IOException {
        String responseBody = "";
        String code = "";
        String sessionID = "";
        Map<String, String> refreshCode;
        Map<String, String> returnValue = new HashMap();
        Map<String, String> getCode;

        Map<String, String> param;
        {
            param = new HashMap<String, String>();
            param.put("API_server", API_Address);
        }

//      刷新验证码
        param.put("API_name", login_conn.getString("API_name_refreshCode"));
        refreshCode = getByUrl(param,"");

//      获取验证码的值
        param.put("API_name", login_conn.getString("API_name_getCodeValue"));
        sessionID = refreshCode.get("sessionID");
        getCode = getByUrl(param,sessionID);
        responseBody = getCode.get("responseBody");
        code = JSON.parseObject(responseBody).getString("response");


        returnValue.put("code", code);
        returnValue.put("sessionID", sessionID);

        return returnValue;
    }

    public static String getSessionID(String API_Address, JSONObject login_conn, Map param) throws InterruptedException, IOException {

        String url = API_Address + "/" + login_conn.getString("API_name_login");
//        login_conn.remove("API_name_login");

        String username = param.get("TokenNeed").toString();

        switch (username) {
            case "Admin":
                login_conn.put("username", username_Admin);
                break;
            case "Provider":
                login_conn.put("username", username_Provider);
                break;
            case "ORG":
                login_conn.put("username", username_ORG);
                break;
        }

        Request.Builder builder = new Request.Builder();
        builder.header("Content-Type", "application/json");

        if(login_conn.containsKey("API_name_refreshCode")){
            Map code = getCode(API_Address, login_conn);

            url = url + "/"  + code.get("code").toString();
            builder.header("Cookie", code.get("sessionID").toString());

//            login_conn.remove("API_name_getCodeValue");
//            login_conn.remove("API_name_refreshCode");
        }

        ApiBasicAW.log.info("发送的数据为:" + login_conn.toString());
        RequestBody body = RequestBody.create(MediaTypeJSON, login_conn.toJSONString());

        builder.post(body);

        Request request = builder.url(url).build();

        Response response = ApiBasicAW.client.newCall(request).execute();
        String sessionString = response.request().url().url().toString();
        g_sessionID = sessionString.split("=")[1];

        if(g_sessionID.contains("?"))
        {
            g_sessionID = g_sessionID.split("[?]")[0];
        }

//        g_token = JSONObject.parseObject(responseBody).getString("access_token");
//        g_tokenType = JSONObject.parseObject(responseBody).getString("token_type");


        return g_sessionID;

    }

    public static String regx(String old) {
        String t = old;//原字符串

        if(old.contains("updateTime")) {
            String regx1 = "\"updateTime\":(\\d{13})";//正则表达式1
            Pattern p1 = Pattern.compile(regx1);
            Matcher m1 = p1.matcher(t);

            while (m1.find()) {
                String s1 = m1.group();//需要替换的字符串
                t = t.replace(s1, "\"updateTime\":973872000000");//替换
            }
        }

        if(old.contains("createTime")) {
            String regx2 = "\"createTime\":(\\d{13})";//正则表达式2
            Pattern p2 = Pattern.compile(regx2);
            Matcher m2 = p2.matcher(t);

            while (m2.find()) {
                String s2 = m2.group();//需要替换的字符串
                t = t.replace(s2, "\"createTime\":973872000000");//替换
            }
        }

        if(old.contains("id")) {
            String regx2 = "\"id\":(\\d{1,4}),";//正则表达式2
            Pattern p2 = Pattern.compile(regx2);
            Matcher m2 = p2.matcher(t);

            while (m2.find()) {
                String s2 = m2.group();//需要替换的字符串
                if(s2.contains("}")){
                    //不处理
                }else {
                    t = t.replace(s2, "\"id\":888888,");//替换
                }
            }
        }

        return t;
    }

    public static String updateSessionID(Map param) {
        String returnValue ="";

        if (param.get("TokenNeed").equals("Yes")) {
            returnValue = g_sessionID;
        }
        return returnValue;
    }
}
