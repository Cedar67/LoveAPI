package testcases.application.ApiTest.LE.AW;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.LoggerFactory;
import utils.ApiAW.ApiBasicAW;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static testcases.application.ApiTest.LE.AW.Config.API_server;
import static testcases.application.ApiTest.LE.AW.Config.port;
import static utils.ApiAW.ApiBasicAW.getCode;
import static utils.ApiAW.ApiBasicAW.getDatetime;
import static utils.ConfigGlobal.MediaTypeJSON;
import static utils.ConfigGlobal.g_sessionID;

/**
 * Define the public method of Selenium test script.
 */



public class logicAW {

    public static org.slf4j.Logger log = LoggerFactory.getLogger(logicAW.class);
    private static OkHttpClient client = new OkHttpClient();


    public static String UpdateId(Map param) throws IOException, InterruptedException {

        String KeyField = "name";
        String KeyValue = "";
        String targetValue = "";
        String ApiName = param.get("ApiName").toString();
        String UriParam = param.get("UriParam").toString();
        String DBtableName = "";

        if(param.containsKey("DBtableName")) {
            DBtableName = param.get("DBtableName").toString();
        }else{
            DBtableName = sqlAW.getDBtableName(ApiName);
        }


        if(param.containsKey("KeyField")) {
            KeyField = param.get("KeyField").toString();

        }else{
            if(ApiName.contains("registration")){
                KeyField = "account";
            }
            if(ApiName.contains("label")){
                KeyField = "name";
            }
        }


        if(param.containsKey("KeyValue")) {
            KeyValue = param.get("KeyValue").toString();

        }else{
            if(param.containsKey("RequestBody")) {
                JSONObject RequestBody = JSON.parseObject(param.get("RequestBody").toString());
                KeyValue = RequestBody.getString(KeyField);
            }
        }


        if(param.containsKey("targetValue")) {
            targetValue = param.get("targetValue").toString();
        }else{
            targetValue = "888888";
        }


        sqlAW.sqlUpdateCreateValue(DBtableName, KeyField, KeyValue, targetValue);

        return "1";
    }

    public static Map UpdateUriCode(String address, String UriParam) throws IOException, InterruptedException {
        Map<String, String> returnValue = new HashMap();
        JSONObject login_conn = new JSONObject();

        login_conn.put("API_name_getCodeValue", "getCodeValue");
        login_conn.put("API_name_refreshCode", "getVerificationCode");

        Map code = getCode(address, login_conn);
        UriParam = UriParam.replace("{code}",code.get("code").toString());

        returnValue.put("UriParam", UriParam);
        returnValue.put("sessionID", code.get("sessionID").toString());

        return returnValue;

    }

    public static Map createTestData(Map param) throws IOException, InterruptedException {

        String UriParam = param.get("UriParam").toString();
        String BodyParam = param.get("RequestBody").toString();
        String ApiName = getCreateApiName(param.get("ApiName").toString());

        String address = "";

        //Builder对象
        Request.Builder builder = new Request.Builder();

        builder.addHeader("Cookie", g_sessionID);
        address = API_server + port;

        if(UriParam.contains("888888")){
            UriParam = "";
        }

        String url = ApiBasicAW.getUrl(address, "", ApiName, UriParam);

        log.info("发送的数据为:" + BodyParam);
        RequestBody body = RequestBody.create(MediaTypeJSON, BodyParam);

        builder.post(body);

        Request request = builder.url(url).build();

        Response response = client.newCall(request).execute();

        Map<String, String> returnMap = new HashMap<String, String>();

        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());

        return returnMap;

    }

    public static String getCreateApiName(String ApiName){

        if(ApiName.contains("attractBusiness")){
            return "/attractBusiness/follow";

        }else if(ApiName.contains("earlyWarning")){
            return "/earlyWarning/updateEarlyWarningInfo";

        }else if(ApiName.contains("/label/deleteLabelGroup")){
            return "/label/addLabelGroup";

        }else if(ApiName.contains("earlyWarning")){
            return "/earlyWarning/updateEarlyWarningInfo";

        }else if(ApiName.contains("enterprise")){
            return "/enterprise";

        }else{
            return "-1";
        }
    }

    public static String getGetApiName(String ApiName){

        if(ApiName.equals("/attractBusiness/follow")){
            return "/attractBusiness/acount/folllow";

        }else if(ApiName.contains("/earlyWarning/updateEarlyWarningInfo")){
            return "/earlyWarning/getEarlyWarningConfig";

        }else if(ApiName.contains("label")){
            return "/label/getLabel";

        }else if(ApiName.contains("enterprise")){
            return "/enterprise/details";

        }else if(ApiName.contains("message")){
            return "/message/getList";

        }else if(ApiName.contains("apply")){
            return "/apply/detail";

        }else{
            return "-1";
        }
    }

    public static String UpdateToRequestBodyFromExpected(String responseExpected) throws IOException, InterruptedException {

        String returnBody = null;
        JSONObject responseJSON = null;

        responseJSON = JSON.parseObject(responseExpected);

        if(responseJSON.containsKey("enterpriseData")){
            responseJSON = responseJSON.getJSONObject("enterpriseData");
        }
//        responseExpected.remove("id");
        responseJSON.remove("createTime");
        responseJSON.remove("createBy");
        responseJSON.remove("updateTime");
        responseJSON.remove("updateBy");
        responseJSON.remove("remark");

        if(responseJSON.containsKey("readNumber")){
            responseJSON.remove("readNumber");
        }
        if(responseJSON.containsKey("recommendTime")){
            responseJSON.remove("recommendTime");
        }
        if(responseJSON.containsKey("recommendBy")){
            responseJSON.remove("recommendBy");
        }

        returnBody = responseJSON.toJSONString();
        return returnBody;
    }

    public static void prepareTestData(Map param) throws IOException, InterruptedException {

        sqlAW.sqlDeleteTableData(param.get("ApiName").toString());

        if(param.get("HttpMethod").equals("Get")) {
            String Expected = param.get("Expected").toString();
            JSONObject bodyJSON = null;

            if(Expected.contains("[")){
                JSONArray listJSONArray = null;
                String RequestBody = null;

                if(Expected.contains("list")) {
                    String responseExpected = JSON.parseObject(Expected).getString("response");
                    bodyJSON = JSON.parseObject(responseExpected);
                    listJSONArray = bodyJSON.getJSONArray("list");
                }else {
                    bodyJSON = JSON.parseObject(Expected);
                    listJSONArray = bodyJSON.getJSONArray("response");
                }

                int Num = listJSONArray.size();

                if(param.get("ApiName").toString().contains("enterprise")
                        ||param.get("ApiName").toString().contains("surveys")
                        ||param.get("ApiName").toString().contains("link"))
                {
                    for (int i = 0; i < Num; i++) {
                        RequestBody = UpdateToRequestBodyFromExpected(listJSONArray.getJSONObject(i).toString());

                        param.put("RequestBody", RequestBody);
                        sleep(1000);
                        createTestData(param);
                    }
                }else {
                    for (int i = (Num-1); i >= 0; i--) {
                        RequestBody = UpdateToRequestBodyFromExpected(listJSONArray.getJSONObject(i).toString());

                        param.put("RequestBody", RequestBody);
                        sleep(1000);
                        createTestData(param);
                    }
                }

            }else{

                String responseExpected = JSON.parseObject(Expected).getString("response");
                bodyJSON = JSON.parseObject(responseExpected);

                param.put("RequestBody", responseExpected);
                createTestData(param);

            }

        }

    }

}
