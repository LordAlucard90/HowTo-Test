package com.lordalucard90.howtotest.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class AccessorsTest {
    @Test
    public void givenPublicGetters_whenGetData_thenReturns() {
        AccessorsTestClass accessorsTestClass = new AccessorsTestClass();
        accessorsTestClass.string = "test";
        accessorsTestClass.aBoolean = true;
        accessorsTestClass.readOnly = "readOnly";

        assertEquals("test", accessorsTestClass.getString());
        assertEquals("readOnly", accessorsTestClass.getReadOnly());
        assertTrue(accessorsTestClass.isABoolean());
    }

    @Test
    public void givenPublicSetters_whenSetData_thenUpdate() {
        AccessorsTestClass accessorsTestClass = new AccessorsTestClass();
        accessorsTestClass.setString("test");
        accessorsTestClass.setABoolean(true);

        assertEquals("test", accessorsTestClass.getString());
        assertTrue(accessorsTestClass.isABoolean());
    }

    @Test(expected = NoSuchMethodException.class)
    public void givenNotPublicSetters_whenSearchSetReadOnly_thenThrows() throws NoSuchMethodException {
        // search for public method
        AccessorsTestClass.class.getMethod("setReadOnly", String.class);
    }

    @Test
    public void givenNotPublicSetters_whenSearchPrivates_thenFindMethod() throws NoSuchMethodException {
        // search for private method
        Method setReadOnly = AccessorsTestClass.class.getDeclaredMethod("setReadOnly", String.class);
        assertNotNull(setReadOnly);
    }

    @Test(expected = NoSuchMethodException.class)
    public void givenNotCreatedSetters_whenSearchSetExcluded_thenThrows() throws NoSuchMethodException {
        AccessorsTestClass.class.getDeclaredMethod("setExcluded", String.class);
    }

    @Test(expected = NoSuchMethodException.class)
    public void givenNotCreatedGetters_whenSearchGetExcluded_thenThrows() throws NoSuchMethodException {
        AccessorsTestClass.class.getDeclaredMethod("getExcluded");
    }
}

/*
    Tested Class
 */
@Getter
@Setter
class AccessorsTestClass {
    String string;
    boolean aBoolean;

    @Setter(AccessLevel.PRIVATE)
    String readOnly;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    String excluded;
}
