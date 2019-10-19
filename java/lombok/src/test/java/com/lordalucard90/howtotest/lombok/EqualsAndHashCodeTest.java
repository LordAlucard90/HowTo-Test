package com.lordalucard90.howtotest.lombok;

import lombok.EqualsAndHashCode;
import lombok.var;
import org.junit.Test;

import static org.junit.Assert.*;

public class EqualsAndHashCodeTest {
    @Test
    public void toEqualsAndHashCodeDefaultImplementationTest() {
        var testClassA = new Object();
        var testClassB = new Object();
        assertEquals(testClassA, testClassA);
        assertNotEquals(testClassA, testClassB);
        assertNotNull(testClassA.hashCode());
        assertNotNull(testClassB.hashCode());
        assertNotEquals(testClassA.hashCode(), testClassB.hashCode());
    }

    @Test
    public void toEqualsAndHashCodeBaseTest() {
        var testClassA = new EqualsAndHashCodeBaseTestClass();
        var testClassB = new EqualsAndHashCodeBaseTestClass();
        var testClassC = new EqualsAndHashCodeBaseTestClass();
        testClassC.baseString = "another";
        assertNotNull(testClassA.hashCode());
        assertNotNull(testClassB.hashCode());
        assertNotNull(testClassC.hashCode());
        assertEquals(testClassA, testClassB);
        assertEquals(testClassA.hashCode(), testClassB.hashCode());
        assertNotEquals(testClassA, testClassC);
        assertNotEquals(testClassA.hashCode(), testClassC.hashCode());
    }

    @Test
    public void equalsAndHashCodeOnlyExplicitTest() {
        var testClassA = new EqualsAndHashCodeOnlyExplicitTestClass("A");
        var testClassB = new EqualsAndHashCodeOnlyExplicitTestClass("B");
        assertEquals(testClassA, testClassB);
        assertEquals(testClassA.hashCode(), testClassB.hashCode());
    }

    @Test
    public void equalsAndHashCodeUsingGettersTest() {
        var testClassA = new EqualsAndHashCodeUsingGettersTestClass("A");
        var testClassB = new EqualsAndHashCodeUsingGettersTestClass("B");
        assertEquals("A", testClassA.fixedString);
        assertEquals("B", testClassB.fixedString);
        assertEquals(testClassA.getFixedString(), testClassB.getFixedString());
        assertNotEquals(testClassA, testClassB);
    }

    @Test
    public void equalsAndHashCodeExcludeTest() {
        var testClassA = new EqualsAndHashCodeExcludeTestClass("A");
        var testClassB = new EqualsAndHashCodeExcludeTestClass("B");
        assertNotEquals(testClassA.varyingString, testClassB.varyingString);
        assertEquals(testClassA, testClassB);
    }

    @Test
    public void equalsAndHashCodeOfTest() {
        var testClassA = new EqualsAndHashCodeOfTestClass("A");
        var testClassB = new EqualsAndHashCodeOfTestClass("B");
        assertNotEquals(testClassA.varyingString, testClassB.varyingString);
        assertEquals(testClassA, testClassB);
    }

    @Test
    public void equalsAndHashCodeParentTest() {
        var testClassA = new EqualsAndHashCodeChildTestClass();
        var testClassB = new EqualsAndHashCodeChildTestClass();
        assertEquals(testClassA, testClassB);
        assertEquals(testClassA.hashCode(), testClassB.hashCode());
    }
}

/*
    Tested Classes
 */
@EqualsAndHashCode
class EqualsAndHashCodeBaseTestClass {
    static String staticString = "static";
    String baseString = "base";
    @EqualsAndHashCode.Exclude
    String excludedString = "excluded";
}

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class EqualsAndHashCodeOnlyExplicitTestClass {
    @EqualsAndHashCode.Include
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeOnlyExplicitTestClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode(doNotUseGetters = true)
class EqualsAndHashCodeUsingGettersTestClass {
    String fixedString = "included";

    public EqualsAndHashCodeUsingGettersTestClass(String fixedString) {
        this.fixedString = fixedString;
    }

    public String getFixedString() {
        return "fixed";
    }
}

@EqualsAndHashCode(of = {"fixedString"})
class EqualsAndHashCodeOfTestClass {
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeOfTestClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode(exclude = {"varyingString"})
class EqualsAndHashCodeExcludeTestClass {
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeExcludeTestClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode
class EqualsAndHashCodeParentTestClass {
    String parentString = "parent";
}

@EqualsAndHashCode(callSuper = true)
class EqualsAndHashCodeChildTestClass extends EqualsAndHashCodeParentTestClass {
    String childString = "child";
}
