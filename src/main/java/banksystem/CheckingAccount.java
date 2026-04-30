package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lớp đại diện cho tài khoản vãng lai. */
public class CheckingAccount extends Account {
  private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);

  /**
   * Khởi tạo tài khoản vãng lai.
   *
   * @param accountNumber Số định danh của tài khoản.
   * @param balance Số dư ban đầu.
   */
  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction t =
          new Transaction(Transaction.TYPE_DEPOSIT_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(t);
      logger.info("Nạp tiền tài khoản vãng lai thành công. Số tiền: {}", amount);
    } catch (BankException e) {
      // Dùng Logger mức độ Cảnh báo thay cho System.out.println
      logger.warn("Cảnh báo nạp tiền: {}", e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction t =
          new Transaction(Transaction.TYPE_WITHDRAW_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(t);
      logger.info("Rút tiền tài khoản vãng lai thành công. Số tiền: {}", amount);
    } catch (BankException e) {
      logger.warn("Cảnh báo rút tiền: {}", e.getMessage());
    } catch (Exception e) {
      // Lỗi nghiêm trọng do hệ thống
      logger.error("Đã xảy ra lỗi hệ thống khi rút tiền", e);
      throw new RuntimeException(e);
    }
  }
}
