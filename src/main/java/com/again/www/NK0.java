package com.again.www;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class NK0 {

	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		long min = Math.min(a, b);
		long max = Math.max(a, b);

		long left = max;
		long right = (long) a * (long) b;
		long result = right;
		while (left <= right) {
			left += max;
			if (left % min == 0)
				result = result > left ? left : result;

			right -= max;
			if (right % min == 0)
				result = result > right ? right : result;

		}
		System.out.println(result);
	}

	static class NK1 {

		private static Scanner sc;

		public static void main(String[] args) {

			sc = new Scanner(System.in);
			double a = sc.nextDouble();
			double left = 0d;
			double mid = a / 2;
			double right = a;
			double tmp = mid * mid * mid;
			double tmp1 = mid + 0.001d;
			tmp1 = tmp1 * tmp1 * tmp1;

			while (!(tmp == a || tmp1 == a) && !(tmp < a && tmp1 > a)) {
				if (tmp > a) {
					right = mid;
					mid = (right + left) / 2;
				} else {
					left = mid + 0.001d;
					mid = (right + left) / 2;
				}
				tmp = mid * mid * mid;
				tmp1 = mid + 0.001d;
				tmp1 = tmp1 * tmp1 * tmp1;

			}
			double result = 0;
			if (tmp1 == a)
				result = mid + 0.001d;
			else
				result = mid;

			DecimalFormat df = new DecimalFormat("0.00");
			DecimalFormat df1 = new DecimalFormat("#.0");
			if ((result + 0.001d) * (result + 0.001d) * (result + 0.001d) < a) {
				System.out.println(df1.format(result + 0.001d));
			} else {
				System.out.println(df1.format(result));

			}
		}

		static class TT {
			public static void main(String args[]) {
				double x = 23.5455;
				NumberFormat ddf1 = NumberFormat.getNumberInstance();

				ddf1.setMaximumFractionDigits(2);
				String s = ddf1.format(x);
				System.out.print(s);
			}
		}

		static class NK2 {

			private static Scanner sc = new Scanner(System.in);

			public static void main(String[] args) {
				if (sc.hasNext())
					System.out.println(sc.nextLine());

			}
		}

		static class NK3 {

			private static Scanner sc = new Scanner(System.in);

			public static void main(String[] args) {
				int i = 0;
				int iCount = 0;
				double iCount1 = 0;
				double sum = 0;
				String str;
				while (sc.hasNext()) {
					str = sc.nextLine();
					String[] s = str.split(" ");
					for (int j = 0; j < s.length; j++) {

						i = Integer.valueOf(s[j]);

						if (i < 0)
							iCount++;
						else {
							sum += (double) i;
							iCount1++;
						}
					}
					DecimalFormat df = new DecimalFormat("#.0");
					System.out.println(iCount);
					double result = iCount1 == 0d ? 0d : sum / iCount1;
					System.out.println(df.format(result));
				}
			}
		}
	}
}
