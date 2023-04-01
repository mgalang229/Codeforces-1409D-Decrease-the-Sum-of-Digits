import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

- process each digit
- every power of 10, we need to check and modify

------------------------------------------------

s = 10
217871987498122

 000000000000000

 217871987498122
+
   2128012501878
----------------
 220000000000000 (sum = 4)

s = 10
217871987498122
???????????????

= min(min, (n / div) * div) 

217871987498122

2 -> 3 * div - n
21 -> 22 * div - n
217 -> 218 * div - n
2178 -> 2189 * div - n
21787 -> 21788 * div - n
217871 -> 217872 * div - n
2178719 -> 217872 (skipped)
21787198 -> 21787199 * div - n

every multiple of 10

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long n = fs.nextLong();
			int s = fs.nextInt();
			int pow = (int) Math.log10(n);
			long div = fastPow(10, pow);
//			System.out.println(ten);
			
			long min = fastPow(10, pow + 1) - n, sumDigits = 0;
			while (div > 0) {
//				System.out.println(n / div % 10);
//				System.out.println(n / div * div);
				
				sumDigits += n / div % 10;
				if (n / div * div == n && sumDigits <= s) {
					min = 0;
				}
				if (n / div % 10 != 9 && sumDigits + 1 <= s) {
//					System.out.println("here = " + (n / div % 10));
					min = Math.min(min, (n / div + 1) * div - n);
				}
				div /= 10;
			}
			System.out.println(min);
		}
		out.close();
	}
	
	static long fastPow(long base, long e) {
		if (e == 0) {
			return 1;
		}
		long half = fastPow(base, e / 2);
		if (e % 2 == 0) {
			return half * half;
		}
		return half * half * base;
	}
	
	static final Random rnd = new Random();
	static void shuffleSortArr(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
