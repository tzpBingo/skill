package org.guava;

import com.google.common.base.Strings;

public class StringsTest {

    public static void main(String[] args) {
        System.out.println(Strings.commonPrefix("asdf","asgg"));    // as
        System.out.println(Strings.commonSuffix("爱思助手","助手"));  // 助手
        System.out.println(Strings.emptyToNull(""));    // null
        System.out.println(Strings.emptyToNull(null));  // null
        System.out.println(Strings.emptyToNull("i4"));  // i4
        System.out.println(Strings.padEnd("10",5,'0')); // 10000
        System.out.println(Strings.padStart("678",5,'0'));  // 00678
        System.out.println(Strings.isNullOrEmpty(""));  // true
        System.out.println(Strings.repeat("i4",2)); // i4i4
    }
}
