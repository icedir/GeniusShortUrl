import com.genius.GeniusShortUrlApplication;
import com.genius.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = {GeniusShortUrlApplication.class})
class HashUtilTest {

    @Test
    void murmurHash32bitTest(){
        log.info("start murmurHash32bit test...");
        String testUrl = "https://cloud.tencent.com/developer/article/1779117";
        int hashInt = HashUtil.murmurHash32bit(testUrl);
        log.info("test url:{}, hash int:{}",testUrl,hashInt);
        log.info("murmurHash32bit test success!");
    }
}
