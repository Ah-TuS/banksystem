package banksystem;

import java.util.ArrayList;
import java.util.List;

/** Lớp đại diện cho một khách hàng trong hệ thống ngân hàng. */
public class Customer {
  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /** Khởi tạo khách hàng mặc định (không tham số). */
  public Customer() {
    this(0L, "");
  }

  /**
   * Khởi tạo khách hàng với mã số và họ tên.
   *
   * @param idNumber Mã số định danh của khách hàng (VD: CMND/CCCD).
   * @param fullName Họ và tên đầy đủ của khách hàng.
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  /**
   * Lấy mã số định danh của khách hàng.
   *
   * @return Mã số định danh.
   */
  public long getIdNumber() {
    return idNumber;
  }

  /**
   * Cập nhật mã số định danh cho khách hàng.
   *
   * @param idNumber Mã số định danh mới.
   */
  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  /**
   * Lấy họ tên khách hàng.
   *
   * @return Họ tên đầy đủ.
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Cập nhật họ tên khách hàng.
   *
   * @param fullName Họ tên đầy đủ mới.
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Lấy danh sách tài khoản của khách hàng.
   *
   * @return Danh sách tài khoản.
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Cập nhật danh sách tài khoản cho khách hàng.
   *
   * @param accountList Danh sách tài khoản mới.
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /**
   * Thêm một tài khoản mới vào danh sách của khách hàng.
   *
   * @param account Tài khoản cần thêm.
   */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
    }
  }

  /**
   * Xóa một tài khoản khỏi danh sách của khách hàng.
   *
   * @param account Tài khoản cần xóa.
   */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    accountList.remove(account);
  }

  /**
   * Lấy thông tin cơ bản của khách hàng dưới định dạng chuỗi.
   *
   * @return Chuỗi thông tin khách hàng.
   */
  public String getCustomerInfo() {
    return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
  }
}
