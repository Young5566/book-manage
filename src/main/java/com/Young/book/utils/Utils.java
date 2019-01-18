package com.Young.book.utils;

import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class Utils {
    /**
     *  返回
     * @return uuid
     */
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
