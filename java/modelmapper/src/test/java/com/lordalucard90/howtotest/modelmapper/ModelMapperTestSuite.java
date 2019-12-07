package com.lordalucard90.howtotest.modelmapper;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
    ModelMapperGettersSettersAndConstructorTest.class,
    ModelMapperSimpleMappingTest.class,
})
public class ModelMapperTestSuite {
}
