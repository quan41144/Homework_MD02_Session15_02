package hwss1502;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static SubjectManager manage = new SubjectManager();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("================ Lựa chọn ==================");
            System.out.println("1. Hiển thị danh sách môn học");
            System.out.println("2. Thêm môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Tìm kiếm môn học theo tên");
            System.out.println("5. Lọc môn học theo tín chỉ");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Danh sách môn học:");
                    if (manage.list.isEmpty()) {
                        System.out.println("Danh sách trống!");
                        break;
                    }
                    manage.display();
                    break;
                case 2:
                    try {
                        System.out.print("new code: ");
                        String code1 = sc.nextLine();
                        System.out.print("new name: ");
                        String name1 = sc.nextLine();
                        System.out.print("new credits: ");
                        int credits1 = Integer.parseInt(sc.nextLine());
                        CreditsException.checkCredits(credits1);
                        LocalDate startDate = null;
                        System.out.print("new start date (dd/MM/yyyy): ");
                        startDate = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        manage.add(new Subject(code1, name1, credits1, startDate));
                        System.out.println("Add success.");
                    }
                    catch (CreditsException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Nhập sai định dạng ngày tháng năm (dd/MM/yyyy)");
                    }
                    break;
                case 3:
                    System.out.print("code to delete: ");
                    String code2 = sc.nextLine();
                    if (!isExitCode(code2)) {
                        System.out.println("Code not found.");
                        break;
                    }
                    for (Subject sub : manage.list) {
                        if (sub.getCode().equalsIgnoreCase(code2)) {
                            manage.remove(sub);
                            break;
                        }
                    }
                    System.out.println("Delete success.");
                    break;
                case 4:
                    System.out.print("name to find: ");
                    String name2 = sc.nextLine();
                    boolean found = false;
                    for (Subject sub : manage.list) {
                        if (sub.getName().contains(name2)) {
                            System.out.println(sub.toString());
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Name not found.");
                    }
                    break;
                case 5:
                    try {
                        System.out.print("Credits to filter: ");
                        int credits2 = Integer.parseInt(sc.nextLine());
                        CreditsException.checkCredits(credits2);
                        System.out.println("Filter credits > " + credits2 + ":");
                        for (Subject sub : manage.list) {
                            if (sub.getCredits() > credits2) {
                                System.out.println(sub.toString());
                            }
                        }
                    }
                    catch (NumberFormatException | CreditsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    public static boolean isExitCode(String code) {
        for (Subject sub : manage.list) {
            if (sub.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
}
