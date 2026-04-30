package banksystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsAccountTest {
  @Test
  void testDeposit() {
    // Tạo tài khoản với 50k
    SavingsAccount account = new SavingsAccount(123L, 50000.0);
    // Nạp thêm 20k
    account.deposit(20000.0);
    // Kiểm tra xem số dư có đúng là 70k không
    assertEquals(99999.0, account.getBalance(), "Số dư sau khi nạp tiền chưa chính xác!");
  }
}