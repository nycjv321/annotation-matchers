# Annotation Matchers

## About
This is a helper library to easily unit test the usage of Annotations

### Why? (Method to the Madness)

So you may be wondering, what's the point of this library?

In my experience in both QA and Development, I've noticed that configuration 
and not implementation is the culprit of many problems found in production. 
Obviously, there are other things to consider when trying to better understanding how bugs
are introduced. For example incorrect documentation and lack of proper 
requirements can be to blame. And of course, code that doesn't correctly 
implement the 
expected functionality. Of these problems, its obvious that more unit tests 
or better unit tests that verify that the implementation actually implemented the 
feature would prevent some bugs. And spending more time reviewing documentation 
and discussing requirements might fix bugs caused by these sort of issues. 

But what about configuration?

Sometimes you may have some highly configurable component, or use some feature 
that is parameterized. And even though the feature works, or is expected to 
work based on some method contract, it doesn't! Why? Usually, its because 
someone is using it wrong, because someone forgot to properly configure it.

One aspect of "configuration", at least in Java, are annotations. And 
lately, I have found myself doing a lot of Spring Boot development. And a lot 
of it (if done right), involves annotations. I end up having 
to write integration tests confirming that I've properly annotated 
some method or parameter that enables some Spring feature. Based on my 
experience with Spring, unless I do something wrong, it kinda just "works". 
So I thought instead of having to write these sort of tests why not just 
double check that I am using Spring correctly? \<insert mind blown gif here\>

This library is intended to enable lazy folks like me to just check for human 
error and not check implementation details or frameworks that are already tested

## Dependencies

    - org.hamcrest.hamcrest-core
    - com.nycjv321.reflection
    - Java 8
    
## Example Usage

See the unit tests. If you're too impatient (shame on you), see below:

    import static has;
    import static org.hamcrest.MatcherAssert.assertThat;

    // ...

    // (This test only works on Java 8 + "-parameters" javac argument)
    // Assert that the TestClass#convert(String[][] arguments) 
    // "arguments" criterion has the WebParam annotation. 
    Criterion criterion = Receiver
        .of(TestClass.class)
        .on("convert", String[][].class)
        .criterion("arguments");
    assertThat(
        criterion,
        has(WebParam.class)
    );
    
    // additional tests here