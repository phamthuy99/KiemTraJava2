import dao.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class App {

    static StudentDAO studentDAO = new StudentDAO();
    static List<Student> studentList = new ArrayList<>();
    static Student student = new Student();

    private static void mainMenu() {
        System.out.println("--------QUẢN LÝ THÔNG TIN SINH VIÊN--------");
        System.out.println("1. Danh sách sinh viên");
        System.out.println("2. Nhập sinh viên mới");
        System.out.println("3. Xóa sinh viên theo mã");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm một sinh viên theo họ tên hoặc theo mã gần đúng");
        System.out.println("6. Sắp xếp sinh viên theo Gpa tăng dần");
        System.out.println("7. Tìm sinh viên nữ ở Hà Nội GPA trên 2.5");
        System.out.println("8. Sắp xếp sinh viên theo Họ tên");
        System.out.println("9. Thoát");
    }

    private static void option1() {
        studentList = studentDAO.getAll();
        String leftAlignFormat = "| %-11s | %-18s | %-9d | %11s  | %n";
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
        System.out.format("|Mã Sinh viên |     Họ tên         | Giới tính | Địa chỉ      |%n");
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.format(leftAlignFormat, studentList.get(i).getId(), studentList.get(i).getFull_name(), studentList.get(i).getGender(), studentList.get(i).getAddress());
        }
        System.out.format("+-------------+--------------------+-----------+--------------+%n");
    }
    private static void option2(Scanner in) {
        studentList = studentDAO.getAll();
        System.out.print("\t Nhập mã sinh viên: ");
        String id = in.nextLine();
        Boolean flag_id = false;
        for (Student studentID : studentList){
            if (studentID.getId().equalsIgnoreCase(id)){
                flag_id = true;
                break;
            }
        }
        if (flag_id){
            System.out.println("Mã sinh viên bị trùng!!!");
            return;
        }
        student.setId(id);
        System.out.print("\t nhập tên: ");
        student.setFull_name(in.nextLine());
        System.out.print("\t nhập giới tính: ");
        student.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\t nhập ngày sinh: ");
        student.setBirth_day(in.nextLine());
        System.out.print("\t nhập địa chỉ: ");
        student.setAddress(in.nextLine());
        System.out.print("\t nhập số đt: ");
        student.setPhone_number(in.nextLine());
        System.out.print("\t nhập email: ");
        student.setEmail(in.nextLine());
        System.out.print("\t nhập điểm GPA: ");
        student.setGpa(in.nextDouble());
        studentDAO.insert(student);
        System.out.println("Thêm thành công");
    }
    private static void option3(Scanner in) {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = in.nextLine();

        Boolean flag_id = false;
        for (Student studentID : studentList){
            if (studentID.getId().equalsIgnoreCase(id)){
                flag_id = true;
                break;
            }
        }

        if (!flag_id){
            System.out.println("Không tìm thấy sinh viên!!!");
            return;
        }

        studentDAO.delete(id);
        System.out.println("Xóa thành công");
    }
    private static void option4(Scanner in) {
        studentList = studentDAO.getAll();
        System.out.print("\t Nhập mã sinh viên cần sửa: ");
        String id = in.nextLine();
        Boolean flag_id = false;
        for (Student studentID : studentList){
            if (studentID.getId().equalsIgnoreCase(id)){
                flag_id = true;
                break;
            }
        }
        if (!flag_id){
            System.out.println("Không tìm thấy mã sinh viên");
            return;
        }
        System.out.print("\t nhập tên: ");
        student.setFull_name(in.nextLine());
        System.out.print("\t nhập giới tính: ");
        student.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\t nhập ngày sinh: ");
        student.setBirth_day(in.nextLine());
        System.out.print("\t nhập địa chỉ: ");
        student.setAddress(in.nextLine());
        System.out.print("\t nhập số đt: ");
        student.setPhone_number(in.nextLine());
        System.out.print("\t nhập email: ");
        student.setEmail(in.nextLine());
        System.out.print("\t nhập điểm GPA: ");
        student.setGpa(in.nextDouble());
        studentDAO.update(student, id);
        System.out.println("Cập nhật thành công");
    }
    private static void option5(Scanner in) {
        System.out.print("\t Nhập tên sinh viên cần tìm: ");

        String full_name = in.nextLine();

        System.out.println(studentDAO.getByName(full_name));
    }
    private static void option6() {

        studentList = studentDAO.getAll();

        Collections.sort(studentList);
        for (Student s: studentList) {
            System.out.println("ID: " + s.getId() + " - NAME: "+ s.getFull_name() + " - GPA: " + s.getGpa());
        }
    }

    private static void option7() {

        studentList = studentDAO.getInformation();

        Collections.sort(studentList);
        for (Student s: studentList) {
            System.out.println("ID: " + s.getId() + " - NAME: "+ s.getFull_name() +  " - GENDER: "+ s.getGender() + " - GPA: " + s.getGpa() + " - ADDRESS: " + s.getAddress());
        }
    }
    private static void option8() {
        studentList = studentDAO.getAll();

        Collections.sort(studentList);
        for (Student s: studentList) {
            System.out.println("ID: " + s.getId() + " - NAME: "+ s.getFull_name());
        }


    }

    public static void main(String[] args) {
        int option = -1;
        Scanner in = new Scanner(System.in);
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                continue;
            }
            if (option < 1 || option > 9) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            // Xu ly cac TH thoa man
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    option5(in);
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                case 8:
                    option8();
                    break;
            }

        }
        while (option != 9);
        in.close();
    }









}
