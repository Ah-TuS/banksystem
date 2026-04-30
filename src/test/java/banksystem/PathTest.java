package banksystem;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathTest {

  @Test
  void testFilePath_Bad() {
    // Cố tình code cứng dấu gạch chéo của Linux/Mac (Forward slash)
    String expectedPath = "logs/transaction.txt";

    // Paths.get sẽ tự động sinh đường dẫn theo hệ điều hành đang chạy
    String actualPath = Paths.get("logs", "transaction.txt").toString();

    // So sánh
    assertEquals(expectedPath, actualPath, "Đường dẫn file không khớp!");
  }
}