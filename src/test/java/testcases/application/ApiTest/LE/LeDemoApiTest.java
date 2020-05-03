package testcases.application.ApiTest.LE;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testcases.application.ApiTest.LE.AW.sqlAW;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static testcases.application.ApiTest.LE.AW.Config.*;
import static testcases.application.ApiTest.LE.AW.RestClient.requestByFormBody;
import static testcases.application.ApiTest.LE.AW.RestClient.requestByUrlParams;
import static utils.ApiAW.ApiBasicAW.assertMethodUpdate;
import static utils.ApiAW.ApiBasicAW.getSessionID;
import static utils.ConfigGlobal.*;
import static utils.dataProvider.ExcelUtil.*;

public class LeDemoApiTest {

    Map paramInput = null;
    Map response = new HashMap();
    Map result = null;
    String methodName = null;
    int rowNum = -1;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
//        getSessionID(API_server + port, login_param, paramInput);
    }


    @BeforeMethod
    public void testStart(Method method) throws Exception {
        //获取用例ID
        methodName = method.getName();
        //获取用例行号
        rowNum = getRowNum(methodName, TestcaseField.Test_Case_ID, sheetTestcaseInput, excelPath);
        //获取用例测试参数和期望值
        paramInput = getTestcaseInputByRowNum(rowNum, excelPath);
        //鉴权
//        getSessionID(API_server + port, login_param, paramInput);
        //提交HTTP请求,并获取返回值
        response = TestStep(paramInput);
        //判断返回值是否符合预期
        result = assertMethodUpdate(response, paramInput);
    }

    @AfterMethod
    public void testEnd(Method method) throws Exception {
        setExcelRow(excelPath, rowNum, result);
        sleep(500);
    }


    @Test
    public void Test_Case_0_1_1() throws Exception {
        assertEquals(result.get("responseKeyword"), paramInput.get("Expected"));
    }

    @Test
    public void Test_Case_0_1_2() throws Exception {
        assertEquals(result.get("responseKeyword"), paramInput.get("Expected"));
    }

    @Test
    public void Test_Case_0_1_3() throws Exception {
        assertEquals(result.get("responseKeyword"), paramInput.get("Expected"));
    }






    private Map TestStep(Map param) throws IOException, InterruptedException {

//        //初始化测试数据
//        sqlDeleteAll(param);
//
//        //构造测试数据
//        sqlCreate();
        Map<String, String> returnMap;

        //执行测试
        String BodyParam = param.get("RequestBody").toString();

        if(BodyParam.isEmpty()) {
            returnMap = requestByUrlParams(param, g_sessionID);
        }else{
            returnMap = requestByFormBody(param, g_sessionID);
        }


        return returnMap;
    }


    private static void sqlCreate() throws InterruptedException, IOException {
//        String now = getDatetimeForSQL();
        String now = "2018-09-25 14:16:30";
        String[] sql1= new String[1];
        String[] sql2= new String[1];


        for(int j=0;j<1;j++) {
            int i = j+10;

            sql1[j] = "INSERT INTO market_dept_client (dept_id, client_id, release_id, sub_release_id) VALUES ('" + i + "', '17', '19', '10')";
            sql2[j] = "UPDATE market_dept_client SET id = '" + i + "' WHERE name = '" + i + "'";

        }

        String[][] sql = {sql1, sql2};

        sqlAW.sqlExecuteBatch(DB_conn, sql);

    }


    private static void sqlDeleteAll(Map param) throws InterruptedException, IOException {

    }
}


