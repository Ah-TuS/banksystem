package banksystem;

/** Lớp Main dùng để xác minh quá trình đóng gói JAR. */
public class Main {

  /**
   * Hàm main khởi chạy độc lập.
   *
   * @param args Các đối số dòng lệnh.
   */
  public static void main(String[] args) {
    System.out.println("=================================================");
    System.out.println("   HỆ THỐNG NGÂN HÀNG ĐÃ KHỞI CHẠY THÀNH CÔNG!   ");
    System.out.println("=================================================");
    System.out.println("Tệp .jar đã được đóng gói chuẩn bởi maven-jar-plugin.");
    System.out.println("Các logic nghiệp vụ và Logging đã được đảm bảo qua Unit Test.");
  }
}