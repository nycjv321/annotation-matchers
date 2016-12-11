package com.nycjv321.matchers;
import com.nycjv321.reflection.Receiver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.jws.WebParam;

import static com.nycjv321.matchers.DescribableMatcher.has;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by fedora on 12/10/16.
 */
public class AnnotationMatchersTest {

    @Test(enabled = false)
    abstract class TestClass {

        @DataProvider
        public abstract Object[][] data();

        @DataProvider
        public abstract Object[][] convert(@WebParam String[][] arguments);

    }

    @Test
    public void receiverHas() throws Exception {
        assertThat(
            Receiver.of(TestClass.class),
            has(Test.class)
        );
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void inputNull() throws Exception {
        assertThat(
                null,
                has(Test.class)
        );
    }

    @Test
    public void messageHas() {
        assertThat(
            Receiver
                .of(TestClass.class)
                .on("data"),
            has(DataProvider.class)
        );
    }

    // This test only passes if the -parameters java compiler is in effect.
    @Test(enabled = false)
    public void criteriaHas() {
        assertThat(
            Receiver
                .of(TestClass.class)
                .on("convert", String[][].class)
                .criterion("arguments"),
            has(WebParam.class)
        );
    }

    @Test
    public void missingAnnotation() {
        try {
            assertThat(
                    Receiver.of(TestClass.class),
                    has(DataProvider.class)
            );
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "\n" +
                    "Expected: Class be annotated with \"org.testng.annotations.DataProvider\"\n" +
                    "     but: \"Receiver\" was not");
        }
    }
}