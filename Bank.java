/**
 * Bank class
 * Author : Harun Rizal
 * Created November 28th, 2013
*/

import java.util.Scanner;

class Bank {
	public int isirekening = 0;
	public static void main(String[] args) {
		System.out.println("\nHalo, selamat datang di Bank SI");
		new Bank();
	}

	public Bank() {
		Scanner sc = new Scanner(System.in);
		System.out.print(
			"\nSekarang, apa yang ingin Anda lakukan?" +
			"\n1. Buka rekening baru" +
			"\n2. Tutup rekening lama" +
			"\n3. Masuk ke rekening" +
			"\n4. Lihat saldo dari seluruh rekening" +
			"\nMasukkan angka dari pilihan diatas dan tekan [enter]: "
		);

		int opt1 = sc.nextInt();
		if(opt1 == 1) bukaRekening();
		else if(opt1 == 2) tutupRekening();
		else if(opt1 == 3) masukRekening();
	}

	public void bukaRekening() {
		isirekening = isirekening+1;
		System.out.println("\nAnda berhasil membuat rekening dengan saldo Rp 0.");
		new Bank();		
	}

	public void tutupRekening() {
		isirekening = isirekening-1;
		System.out.println("\nAnda baru saja menutup rekening yang lalu.");
		new Bank();		
	}

	public void masukRekening() {
		new Rekening();
		new Bank();		
	}
}

class Rekening {
	public int Saldo, SaldoMinimum = 50000;
	public String Kurs;
	public static void main(String[] args) {
		new Rekening();
	}

	public Rekening() {
		Scanner sc = new Scanner(System.in);
		System.out.print(
			"\nSekarang, apa yang ingin Anda lakukan?" +
			"\n1. Setor saldo" +
			"\n2. Tarik saldo" +
			"\n3. Lihat saldo" +
			"\nMasukkan angka dari pilihan diatas dan tekan [enter]: "
		);

		int opt1 = sc.nextInt();
		if(opt1 == 1) {
			System.out.print("Masukkan jumlah saldo yang ingin anda setor : ");
			int disetor = sc.nextInt(); menyetor(disetor);
			System.out.print("Dalam kurs apa : "); String kurss = sc.next();
			disetor = konversi(disetor, kurss);
			System.out.print("Anda berhasil menyetor sejumlah " +disetor+ " " +kurss);
		}
	}

	public void Rekening() {
		Saldo = 0;
		Kurs = "Rupiah";
	}

	public void Rekening(int i) {
		Saldo = i;
	}

	public void Rekening(int duid, String curr) {
		Saldo = duid;
		Kurs = curr;
	}

	public void menyetor(int setor) {
		Saldo = Saldo + setor; 
	}

	public void menyetor(int setor, String curr) {
		setor = konversi(setor, curr);
		Saldo = Saldo + setor;
	}

	public void menarik(int tarik) {
		if(Saldo<SaldoMinimum) System.out.print("Saldo minimum tidak memenuhi.");
		else if((Saldo-tarik)<SaldoMinimum) System.out.print("Saldo tidak mencukupi.");
		else  Saldo = Saldo - tarik;
	}

	public void menarik(int tarik, String curr) {
		tarik = konversi(tarik, curr);
		if(Saldo<SaldoMinimum) System.out.print("Saldo minimum tidak memenuhi.");
		else if((Saldo-tarik)<SaldoMinimum) System.out.print("Saldo tidak mencukupi.");
		else  Saldo = Saldo - tarik;
	}

	public int konversi(int duid, String curr) {
		if(curr.equalsIgnoreCase("USD")) duid = duid*9500;
		else if(curr.equalsIgnoreCase("JPY")) duid = duid*100;
		else if(curr.equalsIgnoreCase("GBP")) duid = duid*15000;
		else if(curr.equalsIgnoreCase("MYR")) duid = duid*3000;
		else if(curr.equalsIgnoreCase("AUD")) duid = duid*8000;
		else if(curr.equalsIgnoreCase("SGD")) duid = duid*7000;
		return duid;
	}
}
