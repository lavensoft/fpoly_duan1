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

public class LoginTest {
    UserController userCtrl = new UserController();
    int l=16;
    @Test(dataProvider = "login")
    public void login(String data){
        UserModel user = new UserModel();
        String username = data.substring(data.lastIndexOf("username")+10,data.lastIndexOf("password")-3);
        String password = data.substring(data.lastIndexOf("password")+10, data.lastIndexOf("USER"));
        String expected = data.substring(data.lastIndexOf("USER"));
        try {
            l++;
            if(username.trim().equals("NULL") && password.trim().equals("NULL")){
                user= userCtrl.login("","");
            }
            else if(username.trim().equals("NULL")){
                user = userCtrl.login("", password.trim());
            }
            else if(password.trim().equals("NULL")){
                user = userCtrl.login(username.trim(), "");
            }
            else {
                user = userCtrl.login(username.trim(), password.trim());
            }
            if(expected.equals("USER_LOGIN_SUCCESS & REDIRECT_TO_HOME")){
                writeExcel("USER_LOGIN_SUCCESS & REDIRECT_TO_HOME", "PASS");
            }
            else{
                writeExcel("USER_LOGIN_SUCCESS & REDIRECT_TO_HOME", "FAIL");
            }
            Assert.assertEquals(expected, "USER_LOGIN_SUCCESS & REDIRECT_TO_HOME");
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

    @DataProvider(name = "login")
    public Iterator<Object[]> readExcel(){
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>();
            for(int i=17;i<24;i++){
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
