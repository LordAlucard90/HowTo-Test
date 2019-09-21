package com.lordalucard90.howtotest.lombok;

import lombok.val;
import lombok.var;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class VariablesTest {

    // when you uncomment this part you cannot compile so it is final
//    @Test
//    public void givenVal_whenTryToReassign_thenNotCompile_Test() {
//        val finalString = "initial";
//        finalString = "changed";
//    }

    @Test
    public void givenVal_whenAssign_thenInfersAutomatically_Test() {
        val inferred = "";
        assertThat(inferred, instanceOf(String.class));
    }

    @Test
    public void givenVal_whenAssignFor_thenInfersAutomatically_Test() {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        for (val inferred: strings) {
            assertThat(inferred, instanceOf(String.class));
        }
    }

    @Test
    public void givenVar_whenReassign_thenChange_Test() {
        var mutableString = "initial";
        assertEquals("initial", mutableString);
        mutableString = "changed";
        assertEquals("changed", mutableString);
    }

    @Test
    public void givenVar_whenAssign_thenInfersAutomatically_Test() {
        var inferred = "";
        assertThat(inferred, instanceOf(String.class));
    }

    @Test
    public void givenVar_whenAssignFor_thenInfersAutomatically_Test() {
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        for (var inferred: strings) {
            assertThat(inferred, instanceOf(String.class));
        }
    }
}