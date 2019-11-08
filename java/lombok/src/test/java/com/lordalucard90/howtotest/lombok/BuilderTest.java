package com.lordalucard90.howtotest.lombok;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.var;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

// todo constructor and to method

// todo
@Builder(toBuilder = true)
class BuilderToBuilderTestClass {
    private String aString;
}

