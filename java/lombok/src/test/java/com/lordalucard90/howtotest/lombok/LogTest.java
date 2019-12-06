package com.lordalucard90.howtotest.lombok;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

public class LogTest { // todo test really..
    @Test
    public void logBaseTestClassLogInfoTest() {
        LogBaseTestClass.infoLog();
    }

    @Test
    public void logBaseTestClassLogWarningTest() {
        LogBaseTestClass.warningLog();
    }

    @Test
    public void logBaseTestClassLogErrorTest() {
        LogBaseTestClass.errorLog();
    }

    @Test
    public void logBaseTestClassLogDebugTest() {
        LogBaseTestClass.debugLog();
    }
}

/*
    Test Classes
 */
@Slf4j
class LogBaseTestClass {
    public static void infoLog() {
        log.info("info");
    }

    public static void warningLog() {
        log.warn("warning");
    }

    public static void errorLog() {
        log.error("error");
    }

    public static void debugLog() {
        log.debug("debug");
    }
}
