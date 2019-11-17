package com.lordalucard90.howtotest.lombok;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.var;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BuilderTest {
    @Test(expected = NoSuchMethodException.class)
    public void builderBaseTestClassNoNoArgsConstructorTest() throws NoSuchMethodException {
        BuilderBaseTestClass.class.getDeclaredConstructor();
    }

    @Test(expected = NoSuchMethodException.class)
    public void builderBaseTestClassNoStringArgConstructorsTest() throws NoSuchMethodException {
        BuilderBaseTestClass.class.getDeclaredConstructor(String.class);
    }

    @Test(expected = NoSuchMethodException.class)
    public void builderBaseTestClassNoStringAndIntArgConstructorsTest() throws NoSuchMethodException {
        BuilderBaseTestClass.class.getDeclaredConstructor(String.class, int.class);
    }

    @Test
    public void builderBaseTestClassTestAllArgsConstructorTest() throws NoSuchMethodException {
        var declaredConstructor = BuilderBaseTestClass.class.getDeclaredConstructor(String.class, int.class, boolean.class);
        assertNotNull(declaredConstructor);
    }

    @Test
    public void builderBaseTestClassTest(){
        var testedClass = BuilderBaseTestClass.builder()
                .aString("test")
                .anInt(1)
                .aBoolean(true)
                .build();
        assertEquals("test", testedClass.aString);
        assertEquals(1, testedClass.anInt);
        assertTrue(testedClass.aBoolean);
    }

    @Test(expected = IllegalAccessException.class)
    public void builderPrivateTestClassTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method declaredMethod = BuilderPrivateTestClass.class.getDeclaredMethod("builder");
        assertNotNull(declaredMethod);
        declaredMethod.invoke(null);
    }

    @Test(expected = NoSuchMethodException.class)
    public void BuilderDifferentMethodsNamesTestClassNoBuilderMethodTest() throws NoSuchMethodException {
        BuilderDifferentMethodsNamesTestClass.class.getDeclaredMethod("builder");
    }

    @Test(expected = NoSuchMethodException.class)
    public void BuilderDifferentMethodsNamesTestClassNoBuildMethodTest() throws NoSuchMethodException {
        BuilderDifferentMethodsNamesTestClass.class.getDeclaredMethod("build");
    }

    @Test(expected = NoSuchMethodException.class)
    public void BuilderDifferentMethodsNamesTestClassNewMethodsNamesTest() throws NoSuchMethodException {
        Method start = BuilderDifferentMethodsNamesTestClass.class.getDeclaredMethod("start");
        assertNotNull(start);
        Method end = BuilderDifferentMethodsNamesTestClass.class.getDeclaredMethod("end");
        assertNotNull(end);
    }

    @Test
    public void BuilderToBuilderTestClassTest() {
        BuilderToBuilderTestClass testClassA = BuilderToBuilderTestClass
                .builder()
                .first("first")
                .second("second")
                .build();
        BuilderToBuilderTestClass testClassB = testClassA
                .toBuilder()
                .first("other")
                .build();
        assertEquals("first", testClassA.first);
        assertEquals("second", testClassA.second);
        assertNotEquals(testClassA, testClassB);
        assertEquals("other", testClassB.first);
        assertEquals("second", testClassB.second);
    }

    @Test
    public void BuilderSingularTestClassTest() {
        List<String> multipleStrings = Arrays.asList("a", "b");

        BuilderSingularTestClass testClass = BuilderSingularTestClass
                .builder()
                .multipleString(multipleStrings)
                .singularStrings(multipleStrings.get(0))
                .singularStrings(multipleStrings.get(1))
                .build();

        assertEquals(multipleStrings, testClass.multipleString);
        assertEquals(multipleStrings, testClass.singularStrings);
    }

    @Test
    public void BuilderDefaultTestClassTest() {
        BuilderDefaultTestClass testClass = BuilderDefaultTestClass.builder().build();

        assertNull(testClass.string);
        assertEquals("default", testClass.stringWithDefault);
    }

    @Test
    public void BuilderConstructorTestClassTest() {
        BuilderConstructorTestClass testClass = BuilderConstructorTestClass
                .builder()
                .aString("string")
                .anInt(1)
                .build();

        assertEquals("string", testClass.aString);
        assertEquals(1, testClass.anInt);
    }

    @Test
    public void BuilderMethodTestClassTest() {
        BuilderMethodTestClass testClass = BuilderMethodTestClass
                .builder()
                .aString("string")
                .anInt(1)
                .build();

        assertEquals("string", testClass.aString);
        assertEquals(1, testClass.anInt);
    }
}
/*
    Tested Classes
 */
@Builder
class BuilderBaseTestClass {
    static String staticString;
    String aString;
    int anInt;
    boolean aBoolean;
}

@Builder(access = AccessLevel.PRIVATE)
class BuilderPrivateTestClass {
    private String aString;
}

@Builder(builderMethodName = "start", buildMethodName = "end")
class BuilderDifferentMethodsNamesTestClass {
    private String aString;
}

@Builder(toBuilder = true)
class BuilderToBuilderTestClass {
    String first;
    String second;
}

@Builder
class BuilderSingularTestClass {
    List<String> multipleString;
    @Singular("singularStrings")
    List<String> singularStrings;
}

@Builder
class BuilderDefaultTestClass {
    String string;
    @Builder.Default String stringWithDefault = "default";
}

class BuilderConstructorTestClass {
    String aString;
    int anInt;

    @Builder
    public BuilderConstructorTestClass(String aString, int anInt) {
        this.aString = aString;
        this.anInt = anInt;
    }
}

class BuilderMethodTestClass {
    String aString;
    int anInt;

    private BuilderMethodTestClass(){}

    @Builder
    public static BuilderMethodTestClass methodBuilder(String aString, int anInt) {
        BuilderMethodTestClass obj = new BuilderMethodTestClass();
        obj.aString = aString;
        obj.anInt = anInt;
        return obj;
    }
}