package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lớp Main để chạy chương trình. */
public class Main {
  // Khai báo Logger cho hàm Main
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  /**
   * Hàm main để chạy và kiểm thử các tính năng của hệ thống.
   *
   * @param args Các đối số dòng lệnh.
   */
  public static void main(String[] args) {
    logger.info("=== BẮT ĐẦU CHẠY THỬ NGHIỆM HỆ THỐNG NGÂN HÀNG ===");

    try {
      // 1. Khởi tạo ngân hàng và khách hàng
      Bank bank = new Bank();
      Customer customer = new Customer(123456789L, "Nguyen Van A");
      bank.getCustomerList().add(customer);
      logger.info("Đã tạo khách hàng thành công: {}", customer.getFullName());

      // 2. Khởi tạo tài khoản và gán cho khách hàng
      CheckingAccount checking = new CheckingAccount(1001L, 50000.0);
      SavingsAccount savings = new SavingsAccount(2002L, 100000.0);

      customer.addAccount(checking);
      customer.addAccount(savings);
      logger.info("Đã mở tài khoản Vãng lai và Tiết kiệm cho khách hàng.");

      // 3. Kiểm thử các giao dịch thành công (Happy Path)
      logger.info("--- Bắt đầu thử nghiệm giao dịch hợp lệ ---");
      checking.deposit(10000.0);
      savings.withdraw(500.0); // Rút hợp lệ (Nhỏ hơn hạn mức 1000 của Savings)

      // 4. Kiểm thử các giao dịch lỗi (Để kích hoạt exception và Log WARN)
      logger.info("--- Bắt đầu thử nghiệm giao dịch sinh lỗi ---");
      // Cố tình rút quá hạn mức của tài khoản tiết kiệm (Sẽ báo lỗi InvalidFundingAmount)
      savings.withdraw(2000.0);
      // Cố tình rút quá số dư của tài khoản vãng lai (Sẽ báo lỗi InsufficientFunds)
      checking.withdraw(1000000.0);

      // 5. In lịch sử giao dịch để xem cấu trúc String.format có mượt không
      logger.info("Lịch sử tài khoản Vãng lai:\n{}", checking.getTransactionHistory());

    } catch (Exception e) {
      logger.error("Đã xảy ra lỗi hệ thống nghiêm trọng trong hàm main", e);
    }

    logger.info("=== KẾT THÚC THỬ NGHIỆM ===");
  }
}