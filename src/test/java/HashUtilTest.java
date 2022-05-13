import com.genius.GeniusShortUrlApplication;
import com.genius.util.HashUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * HashUtil unit test
 * @author icedir
 */
@Slf4j
@SpringBootTest(classes = {GeniusShortUrlApplication.class})
class HashUtilTest {

    @Test
    void testMurmurHash32bit(){
        String testUrl = "https://cloud.tencent.com/developer/article/1779117";
        log.info("start murmurHash32bit test, test url:{}", testUrl);
        int hashInt = HashUtil.murmurHash32bit(testUrl);
        assert hashInt > 0;
        log.info("murmurHash32bit test success! hash int:{}", hashInt);
    }
}
