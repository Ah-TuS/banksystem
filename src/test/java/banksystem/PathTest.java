package banksystem;

import java.io.File;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathTest {
//  @Test
//  void testFilePath_Bad() {
//    // Cố tình code cứng dấu gạch chéo của Linux/Mac (Forward slash)
//    String expectedPath = "logs/transaction.txt";
//
//    // Paths.get sẽ tự động sinh đường dẫn theo hệ điều hành đang chạy
//    String actualPath = Paths.get("logs", "transaction.txt").toString();
//
//    // So sánh
//    assertEquals(expectedPath, actualPath, "Đường dẫn file không khớp!");
//  }

  @Test
  void testFilePath_Good() {
    // Sử dụng File.separator để Java tự động lấy đúng dấu gạch chéo (\ hay /)
    // tùy thuộc vào hệ điều hành đang chạy.
    String expectedPath = "logs" + File.separator + "transaction.txt";

    // Paths.get cũng tự động sinh đường dẫn chuẩn theo OS
    String actualPath = Paths.get("logs", "transaction.txt").toString();

    assertEquals(expectedPath, actualPath, "Đường dẫn file đã khớp trên mọi hệ điều hành!");
  }
}