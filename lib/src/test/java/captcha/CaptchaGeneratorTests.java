package captcha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import captcha.util.RandomStringGenerator;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CaptchaGeneratorTests {
    private CaptchaGenerator captchaGenerator;

    @BeforeEach
    public void setUp() {
        // Mock the config with sample values
        Config config = new Config();
        config.setLength(6);
        config.setWidth(200);
        config.setHeight(50);
        config.setDark(false);
        config.setNoise(20);
        config.setDarkBackgroundColor(Color.BLACK);
        config.setLightBackgroundColor(Color.WHITE);

        // Create the CaptchaGenerator instance with the mock config
        captchaGenerator = new CaptchaGenerator (config);
    }

    @Test
    @DisplayName("Test CaptchGenerator .generate() method")
    public void testGenerateCaptcha() {
        // Create a mock for RandomStringGenerator
        String code = "ABC123";
        RandomStringGenerator randomStringGenerator = mock(RandomStringGenerator.class);
        when(randomStringGenerator.next()).thenReturn(code);

        // Set the mock generator in the CaptchaGenerator  instance
        captchaGenerator.setRandomStringGenerator(randomStringGenerator);

        // Generate the captcha
        var generatedCaptcha = captchaGenerator.generate();

        // Verify that the generated captcha is not null
        assertNotNull(generatedCaptcha);

        // Verify that the generated captcha image is not null
        assertNotNull(generatedCaptcha.image());

        // Verify code
        assertEquals(code, generatedCaptcha.code());
    }
}
