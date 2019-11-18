package com.lordalucard90.howtotest.lombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.With;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WithTest {
    @Test
    public void withTestClassTest() {
        WithTestClass testClass = new WithTestClass("string", 1, "hidden");
        WithTestClass withClass = testClass.withString("newString");
        assertNotEquals(testClass, withClass);
        assertNotEquals(testClass.string, withClass.string);
        assertEquals(testClass.anInt, withClass.anInt);
        assertEquals(testClass.hidden, withClass.hidden);
    }

    @Test(expected = NoSuchMethodException.class)
    public void withTestClassNoHiddenWithMethodTest() throws NoSuchMethodException {
        WithTest.class.getDeclaredMethod("withHidden", String.class);
    }
}
/*
    Tested Classes
 */
@With
@AllArgsConstructor
class WithTestClass {
    String string;
    int anInt;
    @With(value = AccessLevel.NONE)
    String hidden;
}