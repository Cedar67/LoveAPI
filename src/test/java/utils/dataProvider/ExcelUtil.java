package utils.dataProvider;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static utils.ConfigGlobal.*;
/**
 * Excel读写封装
 *
 */
public class ExcelUtil {

    public static int getRowNum(String targetContent, int targetColumn, String targetSheet, String excelPath)
            throws Exception {

        String getContentTemp = null;

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));
        HSSFSheet sheet = wb.getSheet(targetSheet);
        HSSFRow row = null;

        int rowTotal = sheet.getLastRowNum();

        for (int i = 0; i <= rowTotal; i++){

            row = sheet.getRow(i);
            row.getCell(targetColumn).setCellType(HSSFCell.CELL_TYPE_STRING);
            getContentTemp = row.getCell(targetColumn).getStringCellValue();

            if(getContentTemp.equals(targetContent)){
                wb.close();
                return i;
            }

        }

        wb.close();
        return -1;

    }

    public static String getCell(int row, int column, String sheet, String excelPath) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));

        wb.getSheet(sheet).getRow(row).getCell(column).setCellType(HSSFCell.CELL_TYPE_STRING);

        String getValue = wb.getSheet(sheet).getRow(row).getCell(column).getStringCellValue();

        wb.close();
        return getValue;

    }

    public static Map getTestcaseInputByRowNum(int rowNum, String excelPath) throws Exception {


        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));

        HSSFSheet sheet = wb.getSheet(sheetTestcaseInput);

        HSSFRow row = sheet.getRow(rowNum);

        row.getCell(TestcaseField.URI_Param).setCellType(HSSFCell.CELL_TYPE_STRING);
        row.getCell(TestcaseField.Expected).setCellType(HSSFCell.CELL_TYPE_STRING);

        String HttpMethod =     row.getCell(TestcaseField.HTTP_Type).getStringCellValue();
        String ApiName =        row.getCell(TestcaseField.API_Name).getStringCellValue();
        String TokenNeed =      row.getCell(TestcaseField.NeedToken).getStringCellValue();
        String UriParam =       row.getCell(TestcaseField.URI_Param).getStringCellValue();
        String RequestBody =    row.getCell(TestcaseField.RequestBody).getStringCellValue();
        String Expected =       row.getCell(TestcaseField.Expected).getStringCellValue();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("HttpMethod", HttpMethod);
        paramMap.put("RequestBody", RequestBody);
        paramMap.put("ApiName", ApiName);
        paramMap.put("UriParam", UriParam);
        paramMap.put("Expected", Expected);
        paramMap.put("TokenNeed", TokenNeed);

        wb.close();
        return paramMap;

    }

    public static Map getTestcaseInputByID(String testcaseID, String excelPath) throws Exception {

        int rowNum = getRowNum(testcaseID, 2, sheetTestcaseInput, excelPath);

        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));

        HSSFSheet sheet = wb.getSheet(sheetTestcaseInput);

        HSSFRow row = sheet.getRow(rowNum);

        row.getCell(6).setCellType(HSSFCell.CELL_TYPE_STRING);
        row.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);

        String ApiName =        row.getCell(TestcaseField.API_Name).getStringCellValue();
        String TokenNeed =      row.getCell(TestcaseField.NeedToken).getStringCellValue();
        String UriParam =       row.getCell(TestcaseField.URI_Param).getStringCellValue();
        String RequestBody =    row.getCell(TestcaseField.RequestBody).getStringCellValue();
        String Expected =       row.getCell(TestcaseField.Expected).getStringCellValue();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("RequestBody", RequestBody);
        paramMap.put("ApiName", ApiName);
        paramMap.put("UriParam", UriParam);
        paramMap.put("Expected", Expected);
        paramMap.put("TokenNeed", TokenNeed);

        wb.close();
        return paramMap;

    }


    public static void setExcelRow(String excelPath, int rowNum,Map setValue) throws Exception {

        String responseBody = null;
        String responseCode = null;
        String responseTime = null;
        String responseKeyword = null;
        String TestResult = null;

        if(setValue.containsKey("responseBody")) {
            responseBody = setValue.get("responseBody").toString();
        }

        if(setValue.containsKey("responseKeyword")) {
            responseKeyword = setValue.get("responseKeyword").toString();
        }

        if(setValue.containsKey("responseCode")) {
            responseCode = setValue.get("responseCode").toString();
        }

        if(setValue.containsKey("responseTime")) {
            responseTime = setValue.get("responseTime").toString();
        }

        if(setValue.containsKey("TestResult")) {
            TestResult = setValue.get("TestResult").toString();
        }
//        HSSFRichTextString Result = new HSSFRichTextString(setValue.get("Result").toString());

        try {
            // 创建Excel的工作书册 Workbook,对应到一个excel文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(rowNum);
            HSSFCell cell = null;

            CellStyle styleFail = wb.createCellStyle();
            CellStyle stylePass = wb.createCellStyle();
            CellStyle styleResponse = wb.createCellStyle();
            CellStyle style = wb.createCellStyle();

            Font font = wb.createFont();;

            stylePass.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            stylePass.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            stylePass.setFont(font);
            stylePass.setBorderTop(CellStyle.BORDER_THIN);
            stylePass.setBorderBottom(CellStyle.BORDER_THIN);
            stylePass.setBorderLeft(CellStyle.BORDER_THIN);
            stylePass.setBorderRight(CellStyle.BORDER_THIN);
            stylePass.setFillForegroundColor(HSSFColor.GREEN.index);
            stylePass.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            styleFail.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleFail.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleFail.setFont(font);
            styleFail.setBorderTop(CellStyle.BORDER_THIN);
            styleFail.setBorderBottom(CellStyle.BORDER_THIN);
            styleFail.setBorderLeft(CellStyle.BORDER_THIN);
            styleFail.setBorderRight(CellStyle.BORDER_THIN);
            styleFail.setFillForegroundColor(HSSFColor.DARK_RED.index);
            styleFail.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            styleResponse.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleResponse.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleResponse.setFont(font);
            styleResponse.setBorderTop(CellStyle.BORDER_THIN);
            styleResponse.setBorderBottom(CellStyle.BORDER_THIN);
            styleResponse.setBorderLeft(CellStyle.BORDER_THIN);
            styleResponse.setBorderRight(CellStyle.BORDER_THIN);
            styleResponse.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
            styleResponse.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style = styleResponse;

            //响应Body
            cell = row.createCell(TestcaseField.ResponseBody);
            setCell(responseBody, style, cell);

            //响应关键值
            cell = row.createCell(TestcaseField.KeyValue);
            setCell(responseKeyword, style, cell);

            //响应HTTP Code
            cell = row.createCell(TestcaseField.HTTP_Code);
            setCell(responseCode, style, cell);

            //测试执行时间
            cell = row.createCell(TestcaseField.Test_Execute_Time);
            setCell(responseTime, style, cell);


            if(TestResult.equals("Pass")) {
                style = stylePass;
            }else if(TestResult.equals("Fail")){
                style = styleFail;
            }

            //测试结论
            cell = row.createCell(TestcaseField.Test_Result);
            setCell(TestResult, style, cell);

            FileOutputStream os = new FileOutputStream(excelPath);

            wb.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * 写入Excel，在任意坐标处写入数据。
     * String value：你要输入的内容
     * int x :  行坐标，Excel从 0 算起
     * int y :  列坐标，Excel从 0 算起
	 */
	public static void writeCell(int x, int y, String value, String excelPath) {
		try {
			// 创建Excel的工作书册 Workbook,对应到一个excel文档
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					excelPath));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(x);
			HSSFCell cell = row.createCell(y);
			cell.setCellValue(value);
			FileOutputStream os = new FileOutputStream(excelPath);
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



    /**
     * 写入Excel，在任意坐标处写入数据。
     * String value：你要输入的内容
     * int x :  行坐标，Excel从 0 算起
     * int y :  列坐标，Excel从 0 算起
     */
    public static void setCell(String value, CellStyle style, HSSFCell cell) {
        try {
            cell.setCellValue(value);
            cell.setCellStyle(style);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}