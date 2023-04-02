package duan1.test;

import duan1.controllers.user.UserController;
import duan1.models.user.UserModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountTest {
    UserController userCtrl = new UserController();
    int l=34;
    @Test(dataProvider = "addAccount")
    public void addAccount(String data){
        UserModel user = new UserModel();
        String name = data.substring(data.lastIndexOf("name")+6,data.lastIndexOf("email")-3).trim();
        String email = data.substring(data.lastIndexOf("email")+7,data.lastIndexOf("password")-3).trim();
        String password = data.substring(data.lastIndexOf("password")+10,data.lastIndexOf("cccd")-3).trim();
        String cccd = data.substring(data.lastIndexOf("cccd")+6,data.lastIndexOf("salary")-3).trim();
        String salary = data.substring(data.lastIndexOf("salary")+7, data.lastIndexOf("ACCOUNT")).trim();
        String expected = data.substring(data.lastIndexOf("ACCOUNT"));
        try {
            UserModel us = new UserModel();
            us.name = name;
            us.email = email;
            us.password = password;
            us.cccd = cccd;
            l++;
            if(salary.equals("")){
                us.salary = 0.0;
            }
            else {
                us.salary = Double.parseDouble(salary);
            }
            userCtrl.add(us);
            if(expected.equals("ACCOUNT_ADD_SUCCESS")){
                writeExcel("ACCOUNT_ADD_SUCCESS", "PASS");
            }
            else{
                writeExcel("ACCOUNT_ADD_SUCCESS", "FAIL");
            }
            Assert.assertEquals(expected, "ACCOUNT_ADD_SUCCESS");
        }
        catch (Exception e){
            if(e.getMessage().equals(expected.trim())){
                writeExcel(e.getMessage(), "PASS");
            }
            else {
                writeExcel(e.getMessage(), "FAIL");
            }
            Assert.assertEquals(e.getMessage(), expected.trim());
        }
    }

    @DataProvider(name = "addAccount")
    public Iterator<Object[]> readExcel(){
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>();
            for(int i=35;i<70;i++){
                Row row = sheet.getRow(i);
                Object[] o = new Object[]{row.getCell(5).toString()+ row.getCell(6).toString()};
                list.add(o);
            }
            file.close();
            Iterator<Object[]> ob = new Iterator<Object[]>() {
                int k=-2;
                @Override
                public boolean hasNext() {
                    while(k<list.size()-1){
                        k++;
                        return true;
                    }
                    return false;
                }

                @Override
                public Object[] next() {
                    return new Object[]{list.get(k)[0].toString()};
                }
            };
            return ob;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void writeExcel(String actual, String status){
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row roww = sheet.getRow(l);
            roww.getCell(11).setCellValue(actual);
            roww.getCell(12).setCellValue(status);
            FileOutputStream os = new FileOutputStream("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx");
            workbook.write(os);
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
