package com.lordalucard90.howtotest.lombok;

import lombok.NonNull;
import org.junit.Test;

public class NotNullParamTest {
    class NotNullParamConstructorTestClass {
        private String field1;

        NotNullParamConstructorTestClass(@NonNull String field1) throws Exception {
            this.field1 = field1;
            throw new SuperException();
        }

        private class SuperException extends Exception {
        }
    }

    @Test(expected = NullPointerException.class)
    public void whenNullFieldInConstructor_thenThrowsException() throws Exception {
        new NotNullParamConstructorTestClass(null);
    }

    class NotNullParamConstructorTestClassExtension extends NotNullParamConstructorTestClass {
        private String field2;

        NotNullParamConstructorTestClassExtension(String field1, @NonNull String field2) throws Exception {
            super(field1);
            this.field2 = field2;
        }

        NotNullParamConstructorTestClassExtension() throws Exception {
            this("field1", null);
        }
    }

    @Test(expected = NotNullParamConstructorTestClass.SuperException.class)
    public void whenNullFieldInDerivedConstructor_thenThrowsExceptionAfterSuper() throws Exception {
        new NotNullParamConstructorTestClassExtension("field1", null);
    }

    @Test(expected = NotNullParamConstructorTestClass.SuperException.class)
    public void whenNullFieldInDerivedConstructor_thenThrowsExceptionAfterThis() throws Exception {
        new NotNullParamConstructorTestClassExtension();
    }

    class NotNullParamMethodTestClass {
        private String field;

        void setfield(@NonNull String field) {
            this.field = field;
        }
    }

    @Test(expected = NullPointerException.class)
    public void whenNullFieldInMethod_thenThrowsException() {
        NotNullParamMethodTestClass testClass = new NotNullParamMethodTestClass();
        testClass.setfield(null);
    }
}
