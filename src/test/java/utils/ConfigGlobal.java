package utils;

import okhttp3.MediaType;

/**
 * Define the ConfigGlobal of test script.
 */


public  class ConfigGlobal {
	public static final String pathWebdriver_Chrome = "/Users/Cedar/Dev/Install/chromedriver";
	public static final String pathWebdriver_Firefox = "/Users/Cedar/Dev/Install/geckodriver";

    public static final String sheetTestcaseInput = "Sheet1";

    public static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MediaTypeJSON2 = MediaType.parse("application/x-www-form-urlencoded");

    public static String g_sessionID = "";
    public static String g_token = "";
    public static String g_tokenType = "";

    public static interface TestcaseField{
        int Test_Suite = 1;
        int Test_Case_ID = 2;
        int HTTP_Type = 4;
        int API_Name = 5;
        int NeedToken = 6;
        int URI_Param = 7;
        int RequestBody = 8;
        int Expected = 9;
        int ResponseBody = 10;
        int KeyValue = 11;
        int HTTP_Code = 12;
        int Test_Execute_Time = 13;
        int Test_Result = 14;
    }


    public static String getExcelPath(String FileName) {
        String userdirPath = System.getProperty("user.dir");
        if(userdirPath.endsWith("target")){
            userdirPath = userdirPath.replace("target","");
        }
        return userdirPath + "/resources/" + FileName;
    }


    public void testEnvSetUp(String testEnv) throws Exception {

        if(testEnv == "DaaS"){

        }

//        String xmlPath = XmlUtil.class.getClassLoader().getResource("paramData.xml").getPath();

//        Map<String, String> paramsMap = XmlUtil.readXMLDocument(xmlPath, "signIn");

//        server = paramsMap.get("server");
//        uri = paramsMap.get("uri");
////        token = paramsMap.get("token");
//        username = paramsMap.get("username");
//        password = paramsMap.get("password");
//
//        System.out.println("XmlUtil:"+paramsMap);
    }
}
