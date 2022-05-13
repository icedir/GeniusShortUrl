import com.genius.GeniusShortUrlApplication;
import com.genius.util.ScaleConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * ScaleConversionUtil unit test
 * @author icedir
 */
@Slf4j
@SpringBootTest(classes = {GeniusShortUrlApplication.class})
class ScaleConversionUtilTest {

    @Test
    void testConvert10To62(){
        int randomInt = new Random().nextInt();
        log.info("Start test convert10To62, random int value:{}", randomInt);
        String result = ScaleConversionUtil.convert10To62(randomInt);
        assert !result.isEmpty();
        String regex = "^[a-z0-9A-z]+$";
        assert Pattern.matches(regex, result);
        log.info("convert10To62 test success, result:{}", result);
    }
}
