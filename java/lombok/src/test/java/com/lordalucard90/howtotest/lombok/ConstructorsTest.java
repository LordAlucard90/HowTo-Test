package com.lordalucard90.howtotest.lombok;

import lombok.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class ConstructorsTest {
    @Test
    public void noArgsConstructorInstantiationTest() {
        var testClass = new NoArgsConstructorTestClass();
        assertNull(testClass.string);
    }

    @Test
    public void noArgsConstructorPrivateConstructorTest() throws NoSuchMethodException {
        var declaredConstructor = NoArgsConstructorPrivateTestClass.class.getDeclaredConstructor();
        assertNotNull(declaredConstructor);
    }

    @Test
    public void noArgsConstructorStaticNameConstructorTest() {
        var testClass = NoArgsConstructorStaticNameTestClass.of();
        assertNotNull(testClass);
        assertEquals(NoArgsConstructorStaticNameTestClass.class, testClass.getClass());
    }

    @Test(expected = NoSuchMethodException.class)
    public void requiredArgsConstructorNoVoidConstructorTest() throws NoSuchMethodException {
        RequiredArgsConstructorTestClass.class.getDeclaredConstructor();
    }

    @Test(expected = NoSuchMethodException.class)
    public void requiredArgsConstructorNoAllArgsConstructorTest() throws NoSuchMethodException {
        RequiredArgsConstructorTestClass.class.getDeclaredConstructor(int.class, String.class, Object.class);
    }

    @Test
    public void requiredArgsConstructorPrivateConstructorTest() throws NoSuchMethodException {
        var declaredConstructor = RequiredArgsConstructorPrivateTestClass.class.getDeclaredConstructor(int.class, String.class);
        assertNotNull(declaredConstructor);
    }

    @Test(expected = NoSuchMethodException.class)
    public void requiredArgsConstructorPrivateConstructorNoDefaultConstructorTest() throws NoSuchMethodException {
        RequiredArgsConstructorPrivateTestClass.class.getDeclaredConstructor();
    }

    @Test
    public void requiredArgsConstructorOnlyRequiredFieldsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var constructor = RequiredArgsConstructorTestClass.class.getDeclaredConstructor(int.class, String.class);
        assertNotNull(constructor);
        var instance = constructor.newInstance(0, "");
        assertEquals(0, instance.finalInt);
        assertEquals("", instance.requiredString);
        assertNull(instance.optional);
    }

    @Test(expected = NoSuchMethodException.class)
    public void requiredArgsConstructorNoSetFinalConstructorTest() throws NoSuchMethodException {
        RequiredArgsConstructorNoSetFinalTestClass.class.getDeclaredConstructor(int.class, String.class);
    }

    @Test
    public void requiredArgsConstructorStaticNameConstructorTest() {
        var testClass = RequiredArgsConstructorStaticNameTestClass.of(0, "");
        assertEquals(0, testClass.finalInt);
        assertEquals("", testClass.requiredString);
        assertNull(testClass.optional);
    }

    @Test(expected = NoSuchMethodException.class)
    public void allArgsConstructorNoVoidConstructorTest() throws NoSuchMethodException {
        AllArgsConstructorTestClass.class.getDeclaredConstructor();
    }

    @Test
    public void allArgsConstructorNoAllArgsConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        var constructor = AllArgsConstructorTestClass.class.getDeclaredConstructor(int.class, String.class, Object.class);
        assertNotNull(constructor);
        var instance = constructor.newInstance(0, "", Boolean.TRUE);
        assertEquals(0, instance.finalInt);
        assertEquals("", instance.requiredString);
        assertEquals(Boolean.TRUE, instance.optional);
    }

    @Test(expected = NoSuchMethodException.class)
    public void allArgsConstructorNoSetFinalConstructorTest() throws NoSuchMethodException {
        AllArgsConstructorNoSetFinalTestClass.class.getDeclaredConstructor(int.class, String.class);
    }

    @Test
    public void allArgsConstructorPrivateConstructorTest() throws NoSuchMethodException {
        var declaredConstructor = AllArgsConstructorPrivateTestClass.class.getDeclaredConstructor(int.class, String.class, Object.class);
        assertNotNull(declaredConstructor);
    }

    @Test(expected = NoSuchMethodException.class)
    public void allArgsConstructorPrivateConstructorNoDefaultConstructorTest() throws NoSuchMethodException {
        AllArgsConstructorPrivateTestClass.class.getDeclaredConstructor();
    }

    @Test
    public void allArgsConstructorStaticNameConstructorTest() {
        var testClass = AllArgsConstructorStaticNameTestClass.of(0, "", Boolean.TRUE);
        assertEquals(0, testClass.finalInt);
        assertEquals("", testClass.requiredString);
        assertEquals(Boolean.TRUE, testClass.optional);
    }
}

/*
    Tested Classes
 */
@NoArgsConstructor
class NoArgsConstructorTestClass {
    String string;

    public NoArgsConstructorTestClass(String string) {
        this.string = string;
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NoArgsConstructorPrivateTestClass {
    String string;
}

@NoArgsConstructor(staticName = "of")
class NoArgsConstructorStaticNameTestClass {
    String string;
}

@RequiredArgsConstructor
class RequiredArgsConstructorTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@RequiredArgsConstructor
class RequiredArgsConstructorNoSetFinalTestClass {
    final int finalInt = 42;
    @NonNull
    String requiredString;
}

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class RequiredArgsConstructorPrivateTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@RequiredArgsConstructor(staticName = "of")
class RequiredArgsConstructorStaticNameTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor
class AllArgsConstructorTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor
class AllArgsConstructorNoSetFinalTestClass {
    final int finalInt = 42;
    String requiredString;
}


@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AllArgsConstructorPrivateTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor(staticName = "of")
class AllArgsConstructorStaticNameTestClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}
