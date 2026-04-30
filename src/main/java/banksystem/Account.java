package banksystem;

import java.util.ArrayList;
import java.util.List; // Vi phạm: Wildcard import (nên import cụ thể List, ArrayList)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng cơ bản. Lưu trữ thông tin số dư, số tài khoản
 * và danh sách giao dịch.
 */
public abstract class Account {
  // Vi phạm: Đặt tên hằng số không đúng chuẩn (phải là UPPER_SNAKE_CASE)
  public static final String CHECKING_TYPE = "CHECKING";
  public static final String SAVINGS_TYPE = "SAVINGS";
  private static final Logger logger = LoggerFactory.getLogger(Account.class);
  protected List<Transaction> transactionList;
  // Vi phạm: Tên biến instance bắt đầu bằng dấu gạch dưới hoặc quá ngắn, không rõ nghĩa
  private long accountNumber;
  private double balance;

  // Vi phạm: Thụt lề (Indentation) không đồng nhất, không dùng 2 spaces theo chuẩn Google

  /**
   * Khởi tạo một tài khoản ngân hàng mới.
   *
   * @param accountNumber Số tài khoản định danh.
   * @param balance Số dư ban đầu của tài khoản.
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactionList = new ArrayList<>();
  }

  // Vi phạm: Viết hàm trên một dòng, thiếu khoảng trắng giữa các toán tử/ngoặc
  /**
   * Lấy số tài khoản.
   *
   * @return Số tài khoản hiện tại.
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Cập nhật số tài khoản.
   *
   * @param accountNumber Số tài khoản mới.
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Lấy số dư tài khoản.
   *
   * @return Số dư hiện tại.
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Cập nhật số dư tài khoản.
   *
   * @param balance Số dư mới.
   */
  protected void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Lấy danh sách các giao dịch của tài khoản.
   *
   * @return Danh sách giao dịch.
   */
  public List<Transaction> getTransactionList() {
    return transactionList;
  }

  /**
   * Cập nhật danh sách giao dịch cho tài khoản.
   *
   * @param transactionList Danh sách giao dịch mới.
   */
  public void setTransactionList(List<Transaction> transactionList) {
    // Vi phạm: Thiếu dấu ngoặc nhọn cho câu lệnh if (mặc dù vẫn chạy đúng)
    if (transactionList == null) {
      this.transactionList = new ArrayList<>();
    } else {
      this.transactionList = transactionList;
    }
  }

  // Vi phạm: Thiếu Javadoc cho phương thức public
  /**
   * Thực hiện nạp tiền vào tài khoản.
   *
   * @param amount Số tiền cần nạp.
   */
  public abstract void deposit(double amount);

  /**
   * Thực hiện rút tiền khỏi tài khoản.
   *
   * @param amount Số tiền cần rút.
   */
  public abstract void withdraw(double amount);

  /**
   * Logic cốt lõi để cộng tiền vào số dư.
   *
   * @param amount Số tiền cần nạp.
   * @throws InvalidFundingAmountException Nếu số tiền nạp <= 0.
   */
  protected void doDepositing(double amount) throws InvalidFundingAmountException {
    // Vi phạm: Whitespace quanh toán tử (amount<=0)
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
  }

  /**
   * Logic cốt lõi để trừ tiền khỏi số dư.
   *
   * @param amount Số tiền cần rút.
   * @throws InvalidFundingAmountException Nếu số tiền rút <= 0.
   * @throws InsufficientFundsException Nếu số dư không đủ để rút.
   */
  protected void doWithdrawing(double amount)
      throws InvalidFundingAmountException, InsufficientFundsException {
    // Vi phạm: Tung ra Exception quá chung chung thay vì Exception cụ thể
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
  }

  /**
   * Thêm một giao dịch mới vào danh sách lịch sử.
   *
   * @param transaction Giao dịch cần thêm.
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      transactionList.add(transaction);
    }
  }

  /**
   * Lấy chuỗi thông tin lịch sử của tất cả giao dịch.
   *
   * @return Chuỗi định dạng chứa lịch sử giao dịch.
   */
  public String getTransactionHistory() {
    // Vi phạm: Dòng code quá dài (Line length) và dùng cộng chuỗi trong vòng lặp (Performance
    // smell)
    StringBuilder historyBuilder = new StringBuilder();
    historyBuilder.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");

    for (int i = 0; i < transactionList.size(); i++) {
      // Vi phạm: Không dùng StringBuilder
      historyBuilder.append(transactionList.get(i).getTransactionSummary());
      if (i < transactionList.size() - 1) {
        historyBuilder.append("\n");
      }
    }

    // Vi phạm: In log trực tiếp ra console để debug (Thay vì dùng Logger)
    logger.debug("Đã trích xuất lịch sử cho tài khoản: {}", accountNumber);

    return historyBuilder.toString();
  }

  /**
   * Kiểm tra tính bằng nhau giữa hai tài khoản dựa trên số tài khoản.
   *
   * @param obj Đối tượng cần so sánh.
   * @return true nếu cùng số tài khoản, ngược lại trả về false.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account other = (Account) obj;
    return this.accountNumber == other.accountNumber;
  }

  /**
   * Tính toán mã băm cho tài khoản.
   *
   * @return Mã băm sinh ra từ số tài khoản.
   */
  @Override
  public int hashCode() {
    // Vi phạm: Format code lộn xộn
    return (int) (accountNumber ^ (accountNumber >>> 32));
  }
}
