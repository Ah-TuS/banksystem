package banksystem;

// Vi phạm: Wildcard import
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
// Vi phạm: Wildcard import
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Lớp này đại diện cho hệ thống Ngân hàng. */
public class Bank {
  private static final Logger logger = LoggerFactory.getLogger(Bank.class);

  // Vi phạm: Dùng biểu thức chính quy trực tiếp (Magic String)
  private static final Pattern ID_PATTERN = Pattern.compile("\\d{9}");

  // Vi phạm: Tên biến không rõ nghĩa, viết tắt sai chuẩn camelCase
  private List<Customer> customerList;

  /** Khởi tạo hệ thống ngân hàng với danh sách khách hàng rỗng. */
  public Bank() {
    this.customerList = new ArrayList<>();
  }

  /**
   * Lấy danh sách khách hàng hiện tại.
   *
   * @return Danh sách khách hàng.
   */
  public List<Customer> getCustomerList() {
    return customerList;
  }

  // Vi phạm: Thụt đầu dòng (Indentation) lung tung và Javadoc thiếu tag @param

  /**
   * Set danh sach khach hang.
   *
   * @param customerList danh sach khach hang
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng và tài khoản từ luồng đầu vào.
   *
   * @param inputStream Luồng dữ liệu đầu vào.
   */
  public void readCustomerList(InputStream inputStream) {
    // Vi phạm: Log trực tiếp bằng System.out
    logger.debug("Bat dau doc du lieu...");
    if (inputStream == null) {
      return;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      Customer current = null;

      while ((line = reader.readLine()) != null) {
        line = line.trim();

        // Vi phạm: Lồng nested if quá sâu (Đã xử lý bằng early return/continue)
        if (line.isEmpty()) {
          continue;
        }

        int last = line.lastIndexOf(' ');
        if (last <= 0) {
          continue;
        }

        String token = line.substring(last + 1).trim();

        if (ID_PATTERN.matcher(token).matches()) {
          String name = line.substring(0, last).trim();
          current = new Customer(Long.parseLong(token), name);
          customerList.add(current);
          logger.info("Them khach hang: {}", name);
        } else {
          if (current == null) {
            continue;
          }

          String[] parts = line.split("\\s+");
          if (parts.length >= 3) {
            try {
              long num = Long.parseLong(parts[0]);
              double bal = Double.parseDouble(parts[2]);

              // Vi phạm: So sánh String dùng toán tử == (nếu lỡ tay) hoặc không xử lý hằng số
              if (Account.CHECKING_TYPE.equals(parts[1])) {
                current.addAccount(new CheckingAccount(num, bal));
              } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
                current.addAccount(new SavingsAccount(num, bal));
              }
            } catch (NumberFormatException e) {
              logger.warn("Du lieu so khong hop le: {}", e.getMessage());
            }
          }
        }
      }
    } catch (IOException e) {
      // Vi phạm: Catch Exception chung chung
      logger.error("Loi doc du lieu tu InputStream: ", e);
    }
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo ID.
   *
   * @return Chuỗi chứa thông tin khách hàng.
   */
  public String getCustomersInfoByIdOrder() {
    // Vi phạm: Dùng Anonymous class thay vì Lambda, thụt lề sai
    customerList.sort(Comparator.comparingLong(Customer::getIdNumber));

    // Vi phạm: Cộng chuỗi (String concatenation) trong vòng lặp - Cực tệ cho performance
    return formatCustomerListInfo(customerList);
  }

  /**
   * Lấy thông tin khách hàng sắp xếp theo Tên.
   *
   * @return Chuỗi chứa thông tin khách hàng.
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> copy = new ArrayList<>(customerList);
    copy.sort(
        (c1, c2) -> {
          int res = c1.getFullName().compareTo(c2.getFullName());
          return res != 0 ? res : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });

    // Vi phạm: Dòng code quá dài, không ngắt dòng
    // Vi phạm: Logic trùng lặp nhiều với hàm trên (Code Duplication)
    return formatCustomerListInfo(copy);
  }

  /**
   * Hàm hỗ trợ (Helper) để định dạng danh sách khách hàng thành chuỗi. Giúp loại bỏ hoàn toàn lỗi
   * trùng lặp code (Code Duplication) ở 2 hàm trên.
   *
   * @param list Danh sách khách hàng cần in.
   * @return Chuỗi thông tin đã định dạng.
   */
  private String formatCustomerListInfo(List<Customer> list) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i).getCustomerInfo());
      if (i < list.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
