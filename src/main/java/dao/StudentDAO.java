package dao;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAll() {
        final String sql = "SELECT * FROM `students`";

        List<Student> studentList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setFull_name(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setBirth_day(rs.getString("birth_day"));
                s.setAddress(rs.getString("address"));
                s.setPhone_number(rs.getString("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setGpa(rs.getDouble("gpa"));

                studentList.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public Student getByName(String full_name) {
        final String sql = "SELECT * FROM `students` WHERE  `full_name` LIKE '%"+full_name+"%'";
        Student s = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getString("id"));
                s.setFull_name(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setBirth_day(rs.getString("birth_day"));
                s.setAddress(rs.getString("address"));
                s.setPhone_number(rs.getString("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setGpa(rs.getDouble("gpa"));
            }else {
                System.out.println("Không tìm thấy thông tin sinh viên");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void insert(Student s) {
        final String sql = String.format("INSERT  INTO `students` VALUES ( '%s','%s','%d','%s','%s','%s','%s','%f') ",
                s.getId(),s.getFull_name(),s.getGender(),s.getBirth_day(),s.getAddress(),s.getPhone_number(),s.getEmail(),s.getGpa()
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Thêm thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        final String sql = "DELETE FROM `students` WHERE  `id` =  '"+id+"' ";
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Student s, String id) {

        final String sql = String.format("UPDATE `students` SET `full_name`='%s',`gender`='%d',`birth_day`='%s',`address`='%s' , `phone_number`='%s',`email`='%s', `gpa`='%f' WHERE `id` = '%s'",
                s.getFull_name(),s.getGender(),s.getBirth_day(),s.getAddress(),s.getPhone_number(),s.getEmail(),s.getGpa(), id
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Student> getInformation() {

        List<Student> studentList = new ArrayList<>();

        final String sql = " SELECT * FROM students " +
                " WHERE gender = 0 " +
                " AND address = 'Hà Nội'" +
                " AND gpa >= 2.5 ";

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("id"));
                student.setFull_name(rs.getString("full_name"));
                student.setGender(rs.getInt("gender"));
                student.setBirth_day(rs.getString("birth_day"));
                student.setAddress(rs.getString("address"));
                student.setPhone_number(rs.getString("phone_number"));
                student.setEmail(rs.getString("email"));
                student.setGpa(rs.getDouble("gpa"));
                studentList.add(student);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
