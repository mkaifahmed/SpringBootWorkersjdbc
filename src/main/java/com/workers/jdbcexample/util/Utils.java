package com.workers.jdbcexample.util;

import java.util.Date;

public class Utils {
public static String getCurrentTimestamp() {
	return new Date().toString();
}
}