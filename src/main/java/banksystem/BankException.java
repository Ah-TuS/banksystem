package banksystem;

/** Lớp ngoại lệ chung cho các lỗi liên quan đến nghiệp vụ ngân hàng. */
public class BankException extends Exception {

  /**
   * Khởi tạo ngoại lệ với thông báo lỗi.
   *
   * @param message Thông báo chi tiết về nguyên nhân gây lỗi.
   */
  public BankException(String message) {
    super(message);
  }
}
