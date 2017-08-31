package com.jy;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		System.out.print("请输入n:");
		n = scanner.nextInt();
		System.out.println("输入的数n:" + n);

		System.out.println("=======================分割线=========================");

		// 开始时间
		long startTime = System.currentTimeMillis();
		System.out.println("寻找到的第" + n + "个丑数:" + getUgly1(n));
		// 结束时间
		long stopTime = System.currentTimeMillis();
		System.out.println("耗时:" + (stopTime - startTime) + "ms");

		System.out.println("=======================分割线=========================");

		// 开始时间
		startTime = System.currentTimeMillis();
		System.out.println("寻找到的第" + n + "个丑数:" + getUgly2(n));
		// 结束时间
		stopTime = System.currentTimeMillis();
		System.out.println("耗时:" + (stopTime - startTime) + "ms");

		// 释放资源
		scanner.close();
	}

	/**
	 * 寻找指定的丑数
	 * 
	 * @param index
	 *            第几个索引
	 * @return 返回寻找到的丑数
	 */
	public static int getUgly1(int index) {
		if (index <= 0)
			return 0;
		// 丑数
		int number = 0;
		// 第几个丑数
		int uglyFound = 0;
		// 从小到大挨个寻找丑数
		while (uglyFound < index) {
			number++;
			if (isUgly(number))
				uglyFound++;
		}
		// 返回第index个丑数
		return number;
	}

	/**
	 * 判断一个数是否为丑数
	 * 
	 * @param number
	 *            待判定的数
	 * @return 如果是丑数就返回true;否则就返回false
	 */
	public static boolean isUgly(int number) {
		while (number % 2 == 0)
			number >>= 1;
		while (number % 3 == 0)
			number /= 3;
		while (number % 5 == 0)
			number /= 5;
		return (number == 1) ? true : false;
	}

	/**
	 * 寻找指定的丑数
	 * 
	 * @param index
	 *            第几个索引
	 * @return 返回寻找到的丑数
	 */
	public static int getUgly2(int index) {
		if (index <= 0)
			return 0;
		int[] uglyNumbers = new int[index];
		// 默认1是第1个丑数
		uglyNumbers[0] = 1;
		// 下一个丑数的索引
		int nextUglyIndex = 1;

		// 乘以2、3、5刚好大于等于已经查找的的最大丑数的临界点
		int multiply2 = 0;
		int multiply3 = 0;
		int multiply5 = 0;

		// 循环填充数组
		while (nextUglyIndex < index) {
			// 将最小值作为下一个丑数
			int min = Math.min(Math.min(uglyNumbers[multiply2] * 2, uglyNumbers[multiply3] * 3),
					uglyNumbers[multiply5] * 5);
			uglyNumbers[nextUglyIndex] = min;

			// 继续寻找临界点
			while (uglyNumbers[multiply2] * 2 <= uglyNumbers[nextUglyIndex])
				multiply2++;
			while (uglyNumbers[multiply3] * 3 <= uglyNumbers[nextUglyIndex])
				multiply3++;
			while (uglyNumbers[multiply5] * 5 <= uglyNumbers[nextUglyIndex])
				multiply5++;

			nextUglyIndex++;
		}

		// 保存需要的丑数，并释放资源
		int ugly = uglyNumbers[index - 1];
		uglyNumbers = null;
		// 返回找到的丑数
		return ugly;
	}
}
