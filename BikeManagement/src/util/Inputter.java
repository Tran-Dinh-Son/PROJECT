package util;

import java.util.Scanner;
import static util.Inputter.sc;

/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
public class Inputter {
    //props
    public static Scanner sc = new Scanner(System.in);
    
    //method nhập số nguyên 
    public static int getAnInteger(String inpMsg, String errMsg){ 
        while(true){
            try{
                System.out.println(inpMsg);//hiển thị dòng  nhập
                int number = Integer.parseInt(sc.nextLine());
                return number;//hiện số vừa nhập ra bên ngoài sử dụng 
            }catch(Exception e){
                System.out.println(errMsg);//hiển thị nếu nhập sai
            }
        }
    }
    //hàm nhập số nguyên trong khoảng 
    public static int getAnInteger(String inpMsg, String errMsg,
                                    int lowerBound, int upperBound){//Nhập số thực trong khoảng xác định
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while(true){
            try{
                System.out.println(inpMsg);//hiển hị dòng mời nhập
                int number = Integer.parseInt(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;//hiện số vừa nhập ra bên ngoài sử dụng 
            }catch(Exception e){
                System.out.println(errMsg);//hiển thị  nếu nhập sai
            }
        }
    }
        //method nhập số thực
        public static double getADouble(String inpMsg, String errMsg){ 
            while(true){
                try{
                    System.out.println(inpMsg);//hiển hị dòng  nhập
                    double number = Double.parseDouble(sc.nextLine());
                    return number;//hiện số vừa nhập ra bên ngoài sử dụng 
                }catch(Exception e){
                    System.out.println(errMsg);//hiển thịi nếu nhập sai
                }
            }
        }
    //hàm nhập số thực trong khoảng 
    public static double getADouble(String inpMsg, String errMsg,
                                    double lowerBound, double upperBound){
        if(lowerBound > upperBound){
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while(true){
            try{
                System.out.println(inpMsg);//hiển hị dòng  nhập
                double number = Double.parseDouble(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;//hiện số vừa nhập ra bên ngoài sử dụng 
            }catch(Exception e){
                System.out.println(errMsg);//hiển thị  nếu nhập sai
            }
        }
    }
    
    //hàm nhập chuỗi cấm rỗng 
    public static String getString(String inpMsg, String errMsg){
        System.out.println(inpMsg);//mời nhập
        while(true){
            try{
                String str = sc.nextLine();
                if(str.isEmpty()){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    
    //hàm nhập chuỗi theo regex
    public static String getString(String inpMsg, String errMsg, String regex){
        System.out.println(inpMsg);//mời nhập
        while(true){
            try{
                String str = sc.nextLine();
                if(str.isEmpty() || !str.matches(regex)){ // chuỗi rỗng trả về true
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }
    
    //hàm nhập ngày tháng năm chuẩn format (chuỗi, format)
    
//    hàm cập nhật thông tin 
    public static String getStringUpdate(String inpMsg){
        System.out.println(inpMsg);
        String str = sc.nextLine();
        return str;
    }
    
    //hàm nhập chuỗi theo regex đc trống
    public static String getStringUpdateRegex(String inpMsg, String errMsg, String regex){
        System.out.println(inpMsg);//mời nhập
        while(true){
            try{
                String str = sc.nextLine();
                if(str.isEmpty()) return str;
                if(!str.matches(regex)){
                    throw new Exception();
                }
                return str;
            }catch(Exception e){
                System.out.println(errMsg);
            }
        }
    }

    //hàm giới hạn số nguyên đc bỏ trống nì 
     public static int getIntUpdate(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    return 0;
                }
                int number = Integer.parseInt(string);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
     
 
     public static int getMenu(String messageInfo, String messsageErrorOutOfRange,//trả về một số nguyên và nhận vào 5 tham số
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);//Chuỗi thông báo yêu cầu người dùng nhập
                int number = Integer.parseInt(sc.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);//Chuỗi thông báo lỗi khi người dùng nhập số nằm ngoài phạm vi cho phép
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);//Chuỗi thông báo lỗi khi người dùng nhập dữ liệu không phải số.
            }
        } while (true);
    }
     
     public static int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(sc.nextLine());// chuyển đổi chuỗi này thành một số nguyên. Nếu chuỗi không thể chuyển đổi thành số nguyên, sẽ ném ra ngoại lệ
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
}

    
