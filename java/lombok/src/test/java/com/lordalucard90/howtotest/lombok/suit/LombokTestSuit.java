package com.lordalucard90.howtotest.lombok.suit;

import com.lordalucard90.howtotest.lombok.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ValAndVarTest.class,
        NotNullParamTest.class,
        CleanUpTest.class,
        AccessorsTest.class,
        ToStringTest.class,
        EqualsAndHashCodeTest.class,
        ConstructorsTest.class,
        DataAndValueTest.class,
        BuilderTest.class,
        SneakyThrowsTest.class,
        SynchronizedTest.class,
        WithTest.class,
        LogTest.class
})
public class LombokTestSuit {
}
