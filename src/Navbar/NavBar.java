package Navbar;


import model.Student;

import java.util.Arrays;
import java.util.Scanner;

public class NavBar {
    private static Student[] students = new Student[100];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("***************STUDENT MANAGE***************************");
            System.out.println("1. Hiển thị danh sách Sinh viên.");
            System.out.println("2. Tạo Sinh Viên.");
            System.out.println("3. Cập nhật sinh viên.");
            System.out.println("4. Xóa sinh viên.");
            System.out.println("5. Sắp Xếp Học Sinh Theo Tuổi ASC (Tăng Dần).");
            System.out.println("6. Exit.");
            System.out.print(" Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewStudents();
                    break;
                case 2:
                    addStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    sortStudentsByAge();
                    break;
                case 6:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Nhập Mã số sinh viên: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập tuổi sinh viên: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(id, name, age);
        students[count++] = student;

        System.out.print("Sinh viên đã thêm Thành công. ");

    }

    private static void viewStudents() {
        if (count == 0) {
            System.out.print("Không có học sinh để hiển thị. ");
            return;
        }
        System.out.println("Danh sách học sinh: ");
        System.out.println("ID\tName\tAge");
        for (int i = 0; i < count; i++) {
            System.out.println(students[i].getStudentId() + "\t" + students[i].getStudentName() + "\t" + students[i].getAge());
        }
    }

    private static void updateStudent(Scanner scanner) {
        if (count == 0) {
            System.out.print("Không có sinh viên nào để cập nhật. ");
            return;
        }
        System.out.print("Nhập ID sinh viên để cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        int index = findStudentIndex(id);

        if (index == -1) {
            System.out.print("Không có sinh viên để cập nhật.");
            return;
        }
        System.out.print("Nhập tên sinh viên mới:  ");
        String name = scanner.nextLine();

        System.out.print("Nhập tuổi học sinh mới:  ");
        int age = scanner.nextInt();
        scanner.nextLine();

        students[index].setStudentName(name);
        students[index].setAge(age);

        System.out.print("Sinh Viên Cập Nhật Thành Công. ");
    }

    private static void deleteStudent(Scanner scanner) {
        if (count == 0) {
            System.out.print("Không có học sinh để xóa:  ");
            return;
        }
        System.out.print("Nhập ID sinh viên để xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        int index = findStudentIndex(id);

        if (index == -1) {
            System.out.print("Không tìm thấy sinh viên.");
            return;
        }
        for (int i = index; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        count--;

        System.out.print("Xóa sinh viên thành công.");
    }

    private static void sortStudentsByAge() {
        if (count == 0) {
            System.out.print("Không có học sinh để sắp xếp.");
            return;
        }

        Arrays.sort(students, 0, count, (s1, s2) -> s1.getAge() - s2.getAge());

        System.out.println("Sắp xếp học sinh theo độ tuổi tăng dần:");
        System.out.println("ID\tName\tAge");
        for (int i = 0; i < count; i++) {
            System.out.println(students[i].getStudentId() + "\t" + students[i].getStudentName() + "\t" + students[i].getAge());
        }
    }

    private static int findStudentIndex(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == id) {
                return i;
            }
        }

        return -1;
    }
}
