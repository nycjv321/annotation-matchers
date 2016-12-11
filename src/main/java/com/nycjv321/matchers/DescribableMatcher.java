package com.nycjv321.matchers;

import com.nycjv321.reflection.Describable;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * Created by fedora on 12/10/16.
 */
class DescribableMatcher<T extends Annotation> extends BaseMatcher<Describable> {
    private final Class<T> annotation;

    public DescribableMatcher(Class<T> annotation) {
        this.annotation = annotation;
    }

    public static <T extends Annotation> Matcher<Describable> has(Class<T> annotation) {
        return new DescribableMatcher<>(annotation);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Class be annotated with ").appendValue(annotation.getCanonicalName());
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendValue(item.getClass().getSimpleName()).appendText(" was not");
    }

    @Override
    public boolean matches(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        Describable describable = (Describable) o;
        return Objects.nonNull(describable.get(annotation));
    }
}
