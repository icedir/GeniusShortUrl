import com.genius.GeniusShortUrlApplication;
import com.genius.util.UrlConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

/**
 * UrlConvertUtil unit test
 * @author icedir
 */
@Slf4j
@SpringBootTest(classes = {GeniusShortUrlApplication.class})
class UrlConvertUtilTest {

    @Test
    void testConvertUrlToShort(){
        String url = "http://www.google.com";
        log.info("Start test convertUrlToShort, url:{}", url);
        String sUrl = UrlConvertUtil.convertUrlToShort(url);
        assert !sUrl.isEmpty();
        assert sUrl.length() < url.length();
        log.info("convertUrlToShort test success, result:{}", sUrl);
    }
}
