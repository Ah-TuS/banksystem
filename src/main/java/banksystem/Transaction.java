package banksystem;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Đại diện cho một giao dịch. */
public class Transaction {
  public static final int TYPE_DEPOSIT_CHECKING = 1;
  public static final int TYPE_WITHDRAW_CHECKING = 2;
  public static final int TYPE_DEPOSIT_SAVINGS = 3;
  public static final int TYPE_WITHDRAW_SAVINGS = 4;
  // Khai báo Logger cho class
  private static final Logger logger = LoggerFactory.getLogger(Transaction.class);
  private int type;
  private double amount;
  private double initialBalance;
  private double finalBalance;

  /**
   * Khởi tạo một giao dịch mới.
   *
   * @param type Kiểu giao dịch (Nạp/Rút cho Vãng lai/Tiết kiệm).
   * @param amount Số tiền giao dịch.
   * @param initialBalance Số dư ban đầu trước khi giao dịch.
   * @param finalBalance Số dư cuối cùng sau khi giao dịch.
   */
  public Transaction(int type, double amount, double initialBalance, double finalBalance) {
    this.type = type;
    this.amount = amount;
    this.initialBalance = initialBalance;
    this.finalBalance = finalBalance;
  }

  // Vi phạm: Thiếu Javadoc cho phương thức public (Google Style cực kỳ khắt khe lỗi này)
  // Vi phạm: Tên phương thức không tuân thủ camelCase (có dấu gạch dưới)
  // Vi phạm: Tên tham số 't' quá ngắn, không rõ nghĩa

  /**
   * Lấy chuỗi mô tả tương ứng với mã kiểu giao dịch.
   *
   * @param transactionType Mã kiểu giao dịch.
   * @return Chuỗi mô tả kiểu giao dịch.
   */
  public static String getTypeString(int transactionType) {
    switch (transactionType) {
      // Vi phạm: Thụt lề (Indentation) sai chuẩn, không nhất quán
      case TYPE_DEPOSIT_CHECKING: // Vi phạm: Magic Number '1' thay vì dùng TYPE_DEPOSIT_CHECKING
        return "Nạp tiền vãng lai";
      case TYPE_WITHDRAW_CHECKING: // Vi phạm: Magic Number '2'
        return "Rút tiền vãng lai";
      case TYPE_DEPOSIT_SAVINGS: // Vi phạm: Magic Number '3'
        return "Nạp tiền tiết kiệm";
      case TYPE_WITHDRAW_SAVINGS:
        return "Rút tiền tiết kiệm";
      default:
        return "Không rõ";
    }
  }

  /**
   * Lấy kiểu giao dịch.
   *
   * @return Mã kiểu giao dịch.
   */
  public int getType() {
    return type;
  }

  /**
   * Thiết lập kiểu giao dịch.
   *
   * @param type Mã kiểu giao dịch mới.
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Lấy số tiền giao dịch.
   *
   * @return Số tiền giao dịch.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Thiết lập số tiền giao dịch.
   *
   * @param amount Số tiền giao dịch mới.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Lấy số dư ban đầu.
   *
   * @return Số dư trước giao dịch.
   */
  public double getInitialBalance() {
    return initialBalance;
  }

  /**
   * Thiết lập số dư ban đầu.
   *
   * @param initialBalance Số dư ban đầu mới.
   */
  public void setInitialBalance(double initialBalance) {
    this.initialBalance = initialBalance;
  }

  /**
   * Lấy số dư cuối cùng.
   *
   * @return Số dư sau giao dịch.
   */
  public double getFinalBalance() {
    return finalBalance;
  }

  /**
   * Thiết lập số dư cuối cùng.
   *
   * @param finalBalance Số dư cuối cùng mới.
   */
  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  /**
   * Lấy chuỗi tóm tắt thông tin của giao dịch.
   *
   * @return Chuỗi định dạng chứa thông tin tóm tắt.
   */
  public String getTransactionSummary() {
    // Vi phạm: Logging lộn xộn, dùng trực tiếp System.out, format tùy tiện
    logger.debug("Bắt đầu xử lý lấy tóm tắt cho loại giao dịch: {}", this.type);

    // Vi phạm Cực nặng: Dòng code siêu dài (Line Length > 200 ký tự)
    // Vi phạm: Thiếu khoảng trắng (Whitespace) quanh các toán tử '+'
    // Vi phạm: Không sử dụng biến tạm, nhồi nhét mọi logic format vào một dòng
    // Vi phạm: Gọi trực tiếp hàm Locale.US nhiều lần thay vì hằng số hoặc format chung

    // Đã sửa thành một lệnh format duy nhất, tách dòng dễ đọc
    return String.format(
        Locale.US,
        "- Kiểu giao dịch: %s. Số dư ban đầu: $%.2f. Số tiền: $%.2f. Số dư cuối: $%.2f.",
        getTypeString(type),
        initialBalance,
        amount,
        finalBalance);
  }
}
