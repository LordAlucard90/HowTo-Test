package com.lordalucard90.howtotest.lombok;

import lombok.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class DataAndValueTest {
    @Test(expected = NoSuchMethodException.class)
    public void dataTestClassNoArgsConstructorTest() throws NoSuchMethodException {
        DataTestClass.class.getDeclaredConstructor();
    }

    @Test(expected = NoSuchMethodException.class)
    public void dataTestClassAllArgsConstructorTest() throws NoSuchMethodException {
        DataTestClass.class.getDeclaredConstructor(int.class, String.class, Object.class);
    }

    @Test
    public void dataTestClassRequiredArgsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var declaredConstructor = DataTestClass.class.getDeclaredConstructor(int.class, String.class);
        assertNotNull(declaredConstructor);
        DataTestClass instance = declaredConstructor.newInstance(0, "");
        assertEquals(0, instance.finalInt);
        assertEquals("", instance.requiredString);
        assertNull(instance.optional);
    }

    @Test(expected = NullPointerException.class)
    public void dataTestClassNullDataTest() {
        new DataTestClass(0, null);
    }

    @Test(expected = IllegalAccessException.class)
    public void dataTestClassPrivateGetterTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var declaredMethod = DataTestClass.class.getDeclaredMethod("getOptional");
        declaredMethod.invoke(new Object());
    }

    @Test(expected = IllegalAccessException.class)
    public void dataTestClassPrivateSettersTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DataTestClass testClass = new DataTestClass(0, "");
        var declaredMethod = DataTestClass.class.getDeclaredMethod("setRequiredString", String.class);
        declaredMethod.invoke(testClass,"");
    }

    @Test
    public void dataTestClassToStringTest() {
        DataTestClass testClass = new DataTestClass(0, "str");
        String text = testClass.toString();
        assertNotNull(text);
        assertTrue(text.contains("DataTestClass"));
        assertTrue(text.contains("finalInt"));
        assertTrue(text.contains("0"));
        assertTrue(text.contains("requiredString"));
        assertTrue(text.contains("str"));
        assertFalse(text.contains("optional"));
    }

    @Test
    public void dataTestClassEqualsAndHashCodeTest() {
        var testClassA = new DataTestClass(0, "A");
        var testClassB = new DataTestClass(0, "B");
        assertEquals(testClassA, testClassA);
        assertNotEquals(testClassA, testClassB);
        assertNotNull(testClassA.hashCode());
        assertNotNull(testClassB.hashCode());
        assertEquals(testClassA.hashCode(), testClassA.hashCode());
        assertNotEquals(testClassA.hashCode(), testClassB.hashCode());
    }

    @Test(expected = NoSuchMethodException.class)
    public void dataStaticConstructorTestClassNoArgsConstructorTest() throws NoSuchMethodException {
        DataStaticConstructorTestClass.class.getDeclaredMethod("of");
    }

    @Test(expected = NoSuchMethodException.class)
    public void dataStaticConstructorTestClassAllArgsConstructorTest() throws NoSuchMethodException {
        DataStaticConstructorTestClass.class.getDeclaredMethod("of", int.class, String.class, Object.class);
    }

    @Test
    public void dataStaticConstructorTestClassRequiredArgsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var declaredConstructor = DataStaticConstructorTestClass.class.getDeclaredMethod("of", int.class, String.class);
        assertNotNull(declaredConstructor);
        DataStaticConstructorTestClass instance = (DataStaticConstructorTestClass) declaredConstructor.invoke(DataStaticConstructorTestClass.class, 0, "");
        assertEquals(0, instance.finalInt);
        assertEquals("", instance.requiredString);
        assertNull(instance.optional);
    }

    @Test(expected = NoSuchMethodException.class)
    public void valueTestClassNoArgsConstructorTest() throws NoSuchMethodException {
        ValueTestClass.class.getDeclaredConstructor();
    }

    @Test(expected = NoSuchMethodException.class)
    public void valueTestClassAllArgsConstructorTest() throws NoSuchMethodException {
        ValueTestClass.class.getDeclaredConstructor(int.class, String.class, Object.class);
    }

    @Test
    public void valueTestClassRequiredArgsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var declaredConstructor = ValueTestClass.class.getDeclaredConstructor(String.class, Object.class);
        assertNotNull(declaredConstructor);
        ValueTestClass instance = declaredConstructor.newInstance("", Boolean.TRUE);
        assertEquals(0, instance.getFinalInt());
        assertEquals("", instance.getRequiredString());
        assertEquals(Boolean.TRUE, instance.getOptional());
    }

    @Test(expected = NullPointerException.class)
    public void valueTestClassNullValueTest() {
        new ValueTestClass(null, Boolean.FALSE);
    }

    @Test(expected = NoSuchMethodException.class)
    public void valueTestClassPrivateSettersTest() throws NoSuchMethodException {
        ValueTestClass.class.getDeclaredMethod("setRequiredString", String.class);
    }

    @Test
    public void valueTestClassToStringTest() {
        ValueTestClass testClass = new ValueTestClass("str", Boolean.TRUE);
        String text = testClass.toString();
        assertNotNull(text);
        assertTrue(text.contains("ValueTestClass"));
        assertTrue(text.contains("finalInt"));
        assertTrue(text.contains("0"));
        assertTrue(text.contains("requiredString"));
        assertTrue(text.contains("str"));
        assertFalse(text.contains("optional"));
    }

    @Test
    public void valueTestClassEqualsAndHashCodeTest() {
        var testClassA = new ValueTestClass("A", Boolean.TRUE);
        var testClassB = new ValueTestClass("B", Boolean.FALSE);
        assertEquals(testClassA, testClassA);
        assertNotEquals(testClassA, testClassB);
        assertNotNull(testClassA.hashCode());
        assertNotNull(testClassB.hashCode());
        assertEquals(testClassA.hashCode(), testClassA.hashCode());
        assertNotEquals(testClassA.hashCode(), testClassB.hashCode());
    }

    @Test(expected = NoSuchMethodException.class)
    public void valueStaticConstructorTestClassNoArgsConstructorTest() throws NoSuchMethodException {
        ValueStaticConstructorTestClass.class.getDeclaredMethod("of");
    }

    @Test(expected = NoSuchMethodException.class)
    public void valueStaticConstructorTestClassAllArgsConstructorTest() throws NoSuchMethodException {
        ValueStaticConstructorTestClass.class.getDeclaredMethod("of", int.class, String.class, Object.class);
    }

    @Test
    public void valueStaticConstructorTestClassRequiredArgsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var declaredConstructor = ValueStaticConstructorTestClass.class.getDeclaredMethod("of", String.class, Object.class);
        assertNotNull(declaredConstructor);
        ValueStaticConstructorTestClass instance = (ValueStaticConstructorTestClass) declaredConstructor.invoke(ValueStaticConstructorTestClass.class,"", Boolean.TRUE);
        assertEquals(0, instance.getFinalInt());
        assertEquals("", instance.getRequiredString());
        assertEquals(Boolean.TRUE, instance.getOptional());
    }
}

/*
    Tested Classes
 */
@Data
class DataTestClass {
    final int finalInt;
    @NonNull
    @Setter(AccessLevel.PRIVATE)
    String requiredString;
    @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    Object optional;
}

@Data(staticConstructor = "of")
class DataStaticConstructorTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@Value
class ValueTestClass {
    int finalInt = 0;
    @NonNull
    String requiredString;
    @ToString.Exclude
    Object optional;
}

@Value(staticConstructor = "of")
class ValueStaticConstructorTestClass {
    int finalInt = 0;
    @NonNull
    String requiredString;
    Object optional;
}

