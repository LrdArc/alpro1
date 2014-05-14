import java.util.*;
class MyFormatter {

	public static void main (String args[]) {

		Scanner sc = new Scanner(System.in);
		Formatter f = new Formatter();

		System.out.print("Masukkan bilangan bulat : ");
		long a = sc.nextLong();
		f.format("%1$d dalam desimal : %1$,10d \ndalam hexadesimal : %1$x \ndalam oktal : %1$o",a);
		System.out.println(f.toString());

/*
		long a = 1286;
		f.format("%1$d dalam desimal : %1d \ndalam hexadesimal : %1$x \ndalam oktal : %1$o",a);

		String a = "Joko";
		String b = "Budi";
		String c = "Wahyu";
		String d = "Anto";
*/

//		Calendar c = new GregorianCalendar();
//		c.set(1995, 5 , 23);


//		f.format("hari ulang tahun saya: %1$s %4$s %2$s %3$s", a, b, c, d);
//		System.out.println(f.toString());

		// %[argument_index$][flags][width][.precision]conversion // Syntax formatter, di dalam [] optional
		// %[argument_index$][flags][width]conversion // Syntax formatter dalam date
		// %[flags][width]conversion // Syntax formatter non argument

	}

}
