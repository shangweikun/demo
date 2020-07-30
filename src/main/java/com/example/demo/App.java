package com.example.demo;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import net.sf.cglib.proxy.Enhancer;
import sun.misc.Unsafe;

public class App {


	class lombokTest{

	}


	static class test {

		static Unsafe unsafe = getUnsafe();
		static final boolean is64bit = true; // auto detect if possible.

		@SuppressWarnings("deprecation")
		public static void printAddresses(String label, Object... objects) {
			System.out.print(label + ":         0x");
			long last = 0;
			int offset = unsafe.arrayBaseOffset(objects.getClass());
			int scale = unsafe.arrayIndexScale(objects.getClass());
			switch (scale) {
			case 4:
				long factor = is64bit ? 8 : 1;
				final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
				System.out.print(Long.toHexString(i1));
				last = i1;
				for (int i = 1; i < objects.length; i++) {
					final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
					if (i2 > last)
						System.out.print(", +" + Long.toHexString(i2 - last));
					else
						System.out.print(", -" + Long.toHexString(last - i2));
					last = i2;
				}
				break;
			case 8:
				throw new AssertionError("Not supported");
			}
			System.out.println();
		}

		private static Unsafe getUnsafe() {
			try {
				Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
				theUnsafe.setAccessible(true);
				return (Unsafe) theUnsafe.get(null);
			} catch (Exception e) {
				throw new AssertionError(e);
			}
		}

		public static void main(String[] args) {
			String a = "hello";
			String b = "hello";
//			b = "abc";
			GraphLayout graphA;
			GraphLayout graphB;
			synchronized (App.class) {

				graphA = GraphLayout.parseInstance(a);
				graphB = GraphLayout.parseInstance(b);
			}

			System.out.println("gc before ---------");
			print(a, graphA);
			print(b, graphB);

			System.gc(); // gc后，地址变化了

			System.out.println("gc after ---------");
			print(a, graphA);
			print(b, graphB);

			/*
			 * printAddresses("Address:",a); System.out.println("b Address:");
			 * printAddresses("Address:",b);
			 */

		}

		private synchronized static void print(String object, GraphLayout graph) {
			System.err.println("Address:");
			System.out.println(graph.toPrintable());
			System.out.println("Current address: " + VM.current().addressOf(object));
		}
	}

	public final static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) throws InterruptedException {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(MyExeption.class);
		enhancer.setCallback(new MyMethodInterceptor());
		Exception exception = (MyExeption) enhancer.create();

		while (true) {
			try {
				throw exception;
			} catch (Exception e) {
				log.error("ERROR:", e);
			}
			Thread.sleep(10000l);
		}

	}

}
