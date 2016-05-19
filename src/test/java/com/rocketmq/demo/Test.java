/*
 * Copyright (C), 2013-2014, 上海汽车集团股份有限公司
 */
package com.rocketmq.demo;

import java.net.URLEncoder;

import com.meidusa.toolkit.common.util.Base64;

/**
 * @author hejian
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String content = Base64.encode("328活动，还有么？".getBytes());
		System.out.println(content);
	}

}
