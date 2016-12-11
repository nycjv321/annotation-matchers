# Annotation Matchers

## About
This is a helper library to easily unit test the declaration of Annotations

## Dependencies

    - org.hamcrest.hamcrest-core
    - com.nycjv321.reflection
    - Java 8
    
## Example Usage

See the unit tests. If you're too impatient (shame on you), see below:

    import static has;
    import static org.hamcrest.MatcherAssert.assertThat;

    // ...

    // Assert that the TestClass#convert(String[][] arguments) 
    // "arguments" parameter has the WebParam annotation. 
    assertThat(
        Receiver
            .of(TestClass.class)
            .on("convert", String[][].class)
            .criterion("arguments"),
        has(WebParam.class)
    );