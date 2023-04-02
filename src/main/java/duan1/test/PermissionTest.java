package duan1.test;

import duan1.controllers.user.PermissionController;
import duan1.models.user.PermissionModel;
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

public class PermissionTest {
    PermissionController perCtrl = new PermissionController();
    int l=26;
    @Test(dataProvider = "add")
    public void addPermission(String data){
        String name = data.substring(data.lastIndexOf("permissionName")+15, data.lastIndexOf("PERMISSION_FORM")).trim();
        String expected = data.substring(data.lastIndexOf("PERMISSION_FORM")).trim();
        try {
            l++;
            PermissionModel model = new PermissionModel();
            model.name = name;
            perCtrl.add(model);
            if(expected.equals("PERMISSION_FORM_SUCCESS & SEND_SOCKET_UPDATE_PERMISSION")){
                writeExcel("USER_LOGIN_SUCCESS & REDIRECT_TO_HOME", "PASS");
                Assert.assertTrue(true);
            }
            else{
                writeExcel("PERMISSION_FORM_SUCCESS & SEND_SOCKET_UPDATE_PERMISSION", "FAIL");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e){
            if(e.getMessage().equals(expected))
                writeExcel(e.getMessage(),"PASS");
            else
                writeExcel(e.getMessage(), "FAIL");
            Assert.assertEquals(e.getMessage(), expected);
        }
    }

    @Test(dataProvider = "update")
    public void updatePermission(String data){
        String name = data.substring(data.lastIndexOf("permissionName")+15, data.lastIndexOf("PERMISSION_FORM")).trim();
        String expected = data.substring(data.lastIndexOf("PERMISSION_FORM")).trim();
        try {
            l++;
            PermissionModel model = new PermissionModel();
            model.name = name;
            PermissionModel model1 = new PermissionModel();
            model1._id = "639c396b3e27173e1c343fff";
            perCtrl.updateOne(model1, model);
            if(expected.equals("PERMISSION_FORM_SUCCESS & SEND_SOCKET_UPDATE_PERMISSION")){
                writeExcel("USER_LOGIN_SUCCESS & REDIRECT_TO_HOME", "PASS");
                Assert.assertTrue(true);
            }
            else{
                writeExcel("PERMISSION_FORM_SUCCESS & SEND_SOCKET_UPDATE_PERMISSION", "FAIL");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e){
            if(e.getMessage().equals(expected))
                writeExcel(e.getMessage(),"PASS");
            else
                writeExcel(e.getMessage(), "FAIL");
            Assert.assertEquals(e.getMessage(), expected);
        }
    }

    @DataProvider(name = "add")
    public Iterator<Object[]> readAddExcel(){
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>();
            for(int i=27;i<30;i++){
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

    @DataProvider(name = "update")
    public Iterator<Object[]> readUpdateExcel(){
        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\FPT_Polytechnic\\FPT Polytechnic\\SPRING-2023\\SOF304-Kiểm thử nâng cao\\PS24279_VoMinhVuong_ASM.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Object[]> list = new ArrayList<>();
            for(int i=31;i<34;i++){
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
            if(l==29)
                l++;
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
