package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Tai khoan tiet kiem - Lop nay thuc thi cac quy dinh ve rut tien và nap tien. */
// Vi phạm: Dấu ngoặc nhọn mở đầu dòng mới (Google Style yêu cầu cùng dòng) -> Đã sửa lên cùng dòng
public class SavingsAccount extends Account {

  private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

  // Khai báo hằng số để thay thế cho Magic Numbers
  private static final double MAX_WITHDRAW_AMOUNT = 1000.0;
  private static final double MIN_BALANCE = 5000.0;

  /**
   * Khởi tạo tài khoản tiết kiệm.
   *
   * @param accountNumber Số tài khoản định danh.
   * @param balance Số dư ban đầu.
   */
  public SavingsAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    // Vi phạm: Log không có cấu trúc, sử dụng System.err lộn xộn
    logger.debug("Giao dich nap tien dang xu ly...");

    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();

      // Vi phạm: Magic Number '3' (Nên dùng Transaction.TYPE_DEPOSIT_SAVINGS)
      // Vi phạm: Dòng code quá dài
      Transaction t =
          new Transaction(Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
      addTransaction(t);

      logger.info("Nap tien vao tai khoan {} thanh cong: +{}", getAccountNumber(), amount);
    } catch (BankException e) {
      // Vi phạm: Catch Exception chung chung (Sửa thành BankException)
      logger.warn("Loi nap tien: {}", e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    try {
      // Vi phạm: Magic Number '1000.0' thay vì hằng số MAX_WITHDRAW
      if (amount > MAX_WITHDRAW_AMOUNT) {
        throw new InvalidFundingAmountException(amount);
      }

      // Vi phạm: Magic Number '5000.0' thay vì hằng số MIN_BALANCE
      if (initialBalance - amount < MIN_BALANCE) {
        throw new InsufficientFundsException(amount);
      }

      doWithdrawing(amount);
      double finalBalance = getBalance();

      // Vi phạm: Magic Number '4' (Nên dùng Transaction.TYPE_WITHDRAW_SAVINGS)
      Transaction t =
          new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
      addTransaction(t);

      // Vi phạm: Log viết theo phong cách tùy tiện
      logger.info("[SAVINGS] Rut {} thanh cong. So du con: {}", amount, finalBalance);

    } catch (BankException e) {
      // Vi phạm: Thiếu dấu ngoặc nhọn cho khối catch đơn dòng (tùy chuẩn)
      // Vi phạm: Log lỗi nhưng không ghi rõ lỗi gì hoặc stack trace
      logger.warn("Rut tien bi loi: {}", e.getMessage());
    }
  }
}
