package testcases.application.ApiTest.LE.AW;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import utils.ApiAW.ApiBasicAW;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.ApiAW.ApiBasicAW.getDatetime;
import static utils.ConfigGlobal.MediaTypeJSON;
import static utils.ConfigGlobal.g_sessionID;



/**
 * Define the class of http request.
 */
public class RestClient {

    private static OkHttpClient client = new OkHttpClient();


    public static Map requestByFormBody(Map param, String sessionID) throws IOException, InterruptedException {

        String BodyParam = param.get("RequestBody").toString();
        String UriParam = param.get("UriParam").toString();
        String ApiName = param.get("ApiName").toString();
        String HttpMethod = param.get("HttpMethod").toString();

//        RequestBody body = RequestBody.create(MediaTypeJSON, BodyParam);
        String url = null;
        url = ApiBasicAW.getUrl(Config.API_server + Config.port, "",ApiName, UriParam);

        String[] BodyParamArr = BodyParam.split(":");

        //Builder对象
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add(BodyParamArr[0], BodyParamArr[1]);
        FormBody formBody = formBuilder.build();
        okhttp3.Request.Builder requestBuilder = new Request.Builder().url(url).post(formBody);

//        switch (HttpMethod) {
//            case "Post":
//                formBuilder.post(formBody);
//                break;
//            case "Put":
//                formBuilder.put(formBody);
//                break;
//            case "Delete":
//                formBuilder.delete(formBody);
//                break;
//            case "Get":
//                break;
//            default:
//                formBuilder.post(formBody);
//                break;
//        }

        if (sessionID != "") {
//            builder.addHeader("Cookie", sessionID);
            requestBuilder.addHeader("Cookie", "JSESSIONID="+sessionID);
        }

        Request request = requestBuilder
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        Map<String, String> returnMap = new HashMap<String, String>();


        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());

        return returnMap;

    }


    public static Map requestByUrlParams(Map param, String sessionID) throws IOException, InterruptedException {

        String BodyParam = param.get("RequestBody").toString();
        String UriParam = param.get("UriParam").toString();
        String ApiName = param.get("ApiName").toString();
        String HttpMethod = param.get("HttpMethod").toString();

        RequestBody body = RequestBody.create(MediaTypeJSON, BodyParam);

        String url = null;
        url = ApiBasicAW.getUrl(Config.API_server + Config.port, "",ApiName, UriParam);

        //Builder对象
        Request.Builder builder = new Request.Builder();

        switch (HttpMethod) {
            case "Post":
                builder.post(body);
                break;
            case "Put":
                builder.put(body);
                break;
            case "Delete":
                builder.delete(body);
                break;
            case "Get":
                break;
            default:
                builder.post(body);
                break;
        }

        if (sessionID != "") {
//            builder.addHeader("Cookie", sessionID);
            builder.addHeader("Cookie", "JSESSIONID="+sessionID);
        }

        Request request = builder
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        Map<String, String> returnMap = new HashMap<String, String>();


        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());

        return returnMap;

    }

    public static Map requestByToken(Map param, String token, String tokenType) throws IOException, InterruptedException {

        String BodyParam = param.get("RequestBody").toString();
        String UriParam = param.get("UriParam").toString();
        String ApiName = param.get("ApiName").toString();
        String HttpMethod = param.get("HttpMethod").toString();

        RequestBody body = RequestBody.create(MediaTypeJSON, BodyParam);

        String url = null;
        url = ApiBasicAW.getUrl(Config.API_server + Config.port, "",ApiName, UriParam);

        //Builder对象
        Request.Builder builder = new Request.Builder();

        switch (HttpMethod) {
            case "Post":
                builder.post(body);
                break;
            case "Put":
                builder.put(body);
                break;
            case "Delete":
                builder.delete(body);
                break;
            case "Get":
                break;
            default:
                builder.post(body);
                break;
        }

        if (token != "") {
            builder.addHeader("Authorization", tokenType+" "+token);
        }

        Request request = builder
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        Map<String, String> returnMap = new HashMap<String, String>();


        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());

        return returnMap;

    }

    public static Map requestBySessionID(Map param, String sessionID) throws IOException, InterruptedException {

        String BodyParam = param.get("RequestBody").toString();
        String UriParam = param.get("UriParam").toString();
        String ApiName = param.get("ApiName").toString();
        String HttpMethod = param.get("HttpMethod").toString();

        RequestBody body = RequestBody.create(MediaTypeJSON, BodyParam);

        String url = null;
        url = ApiBasicAW.getUrl(Config.API_server + Config.port, "",ApiName, UriParam);

        //Builder对象
        Request.Builder builder = new Request.Builder();

        switch (HttpMethod) {
            case "Post":
                builder.post(body);
                break;
            case "Put":
                builder.put(body);
                break;
            case "Delete":
                builder.delete(body);
                break;
            case "Get":
                break;
            default:
                builder.post(body);
                break;
        }

        if (sessionID != "") {
            builder.addHeader("Cookie", sessionID);
        }

        Request request = builder
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        Map<String, String> returnMap = new HashMap<String, String>();


        String responseBody = response.body().string();
        String responseCode = String.valueOf(response.code());

        returnMap.put("responseBody", responseBody);
        returnMap.put("responseCode", responseCode);
        returnMap.put("responseTime", getDatetime());

        return returnMap;

    }

    public static Map getTestResult(Map param) throws IOException, InterruptedException {

        String UriParam = param.get("UriParam").toString();
        String ApiName = logicAW.getGetApiName(param.get("ApiName").toString());

        String address = Config.API_server + Config.port;
        String url = null;

        //Builder对象
        Request.Builder builder = new Request.Builder();

        if (param.get("TokenNeed").equals("Yes")) {
            builder.addHeader("Cookie", g_sessionID);
        }else if(param.get("TokenNeed").equals("No")) {
        }


        url = ApiBasicAW.getUrl(address, "",ApiName, UriParam);


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

    public static String getBodyFromExpected(String Expected) throws IOException, InterruptedException {

        String returnBody = null;
        String responseExpectedUpdate = null;
        JSONObject responseJSON = null;
        String responseExpected = null;

//        responseJSON = JSON.parseObject(Expected);

        responseExpected = JSON.parseObject(Expected).getString("response");

        responseExpectedUpdate = logicAW.UpdateToRequestBodyFromExpected(responseExpected);

        responseJSON = JSON.parseObject(responseExpectedUpdate);

        returnBody = responseJSON.toJSONString();
        return returnBody;
    }

}
