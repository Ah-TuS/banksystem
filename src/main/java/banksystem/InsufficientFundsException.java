package banksystem;

import java.util.Locale;

/** Ngoại lệ khi số dư tài khoản không đủ. */
public class InsufficientFundsException extends BankException {

  /**
   * Khởi tạo ngoại lệ với số dư tài khoản không đủ để thực hiện giao dịch.
   *
   * @param amount Số tiền yêu cầu giao dịch gây ra lỗi.
   */
  public InsufficientFundsException(double amount) {
    super(
        "Số dư tài khoản không đủ $"
            + String.format(Locale.US, "%.2f", amount)
            + " để thực hiện giao dịch");
  }
}
