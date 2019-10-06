package com.lordalucard90.howtotest.lombok;

import lombok.ToString;
import lombok.var;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToStringTest {
    @Test
    public void toStringBaseTest() {
        var testClass = new ToStringBaseTestClass();
        String text = testClass.toString();
        assertNotNull(text);
        assertTrue(text.contains("ToStringBaseTestClass"));
        assertTrue(text.contains("baseString"));
        assertTrue(text.contains("base"));
        assertFalse(text.contains("staticString"));
        assertFalse(text.contains("static"));
        assertFalse(text.contains("excludedString"));
        assertFalse(text.contains("excluded"));
    }

    @Test
    public void toStringOnlyExplicitTest() {
        var testClass = new ToStringOnlyExplicitTestClass();
        String text = testClass.toString();
        assertTrue(text.contains("includedString"));
        assertFalse(text.contains("excludedString"));
    }

    @Test
    public void toStringUsingGettersTest() {
        var testClass = new ToStringUsingGettersTestClass();
        String text = testClass.toString();
        assertEquals("base", testClass.getBaseString());
        assertTrue(text.contains("baseString"));
        assertFalse(text.contains("base"));
        assertTrue(text.contains("other"));
    }

    @Test
    public void toStringChangeOrderAndNamesTest() {
        var testClass = new ToStringChangeOrderAndNamesTestClass();
        String text = testClass.toString();
        assertTrue(text.contains("firstString"));
        assertTrue(text.contains("secondString"));
        assertTrue(text.contains("firstString=second, secondString=first"));
    }

    @Test
    public void toStringExcludeTest() {
        var testClass = new ToStringExcludeTestClass();
        String text = testClass.toString();
        assertTrue(text.contains("includedString"));
        assertFalse(text.contains("excludedString"));
    }

    @Test
    public void toStringOfTest() {
        var testClass = new ToStringOfTestClass();
        String text = testClass.toString();
        assertTrue(text.contains("includedString"));
        assertFalse(text.contains("excludedString"));
    }

    @Test
    public void toStringParentTest() {
        var testClass = new ToStringChildTestClass();
        String text = testClass.toString();
        assertTrue(text.contains("ToStringParentTestClass"));
        assertTrue(text.contains("parentString"));
        assertTrue(text.contains("ToStringChildTestClass"));
        assertTrue(text.contains("childString"));
    }
}

/*
    Tested Class
 */
@ToString
class ToStringBaseTestClass {
    static String staticString = "static";
    String baseString = "base";
    @ToString.Exclude
    String excludedString = "excluded";
}

@ToString(onlyExplicitlyIncluded = true)
class ToStringOnlyExplicitTestClass {
    @ToString.Include
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString(doNotUseGetters = true)
class ToStringUsingGettersTestClass {
    String baseString = "other";

    public String getBaseString() {
        return "base";
    }
}

@ToString
class ToStringChangeOrderAndNamesTestClass {
    @ToString.Include(name = "secondString", rank = -1)
    String first = "first";
    @ToString.Include(name = "firstString", rank = 1)
    String second = "second";
}

@ToString(of = {"includedString"})
class ToStringOfTestClass {
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString(exclude = {"excludedString"})
class ToStringExcludeTestClass {
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString
class ToStringParentTestClass {
    String parentString = "parent";
}

@ToString(callSuper = true)
class ToStringChildTestClass extends ToStringParentTestClass {
    String childString = "child";
}
