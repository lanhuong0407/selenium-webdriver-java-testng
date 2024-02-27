package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Topic_01_Data_Type {
    //Kiểu dữ liệu trong java - 2 nhóm

    // I- Kiểu dữ liệu nguyên thủy - (Primitive Type)
    // 8 loại
    // Số nguyên: byte - short- int - long
    // K có phần thập phaan: nhân viên trong 1 cty, số hsinh trong 1 lớp học

    // Số thực: float - double
    // Có phần thập phân: điểm số, lương...
    float fNumber = 9.99f;
    double dNumber = 35.880692d;

    // Kí tự: char
    // Đại diện cho 1 ký tự
    char c = 'M';

    // Logic: boolean (luận lý), chỉ đúng hoặc sai (true/false)
    boolean status = true;

    // II- Kiểu dữ liệu tham chiếu - (Reference Type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className("")));
    // Interface

    // Object
   // Object name = 'ABC' ;

    // Array (kiểu nguyên thủy, tham chiếu...)

    // Collection: List/ Set/ Queue

    // String - Chuỗi ký tự
    //String name = 'Huong';

    // Khai báo biến để lưu trữ 1 loại dữ liệu nào đó
    // Access Modifier: Phạm vi truy cập (public/ private/ protected/ default)
    // Kiểu dữ liệu
    // Tên biến
    // Giá trị của biến- gán vào với dấu =
    // Nếu như không gán:dữ liệu mặc định

}
