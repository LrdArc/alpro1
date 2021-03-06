import java.util.*;

public class MergeSort {
	public int arr[] = new int[50];

	public static void main(String[] args) {
		new MergeSort();
	}

	public MergeSort() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukkan jumlah data : ");
		int n = sc.nextInt(), i;
		for(i=1; i<=n; i++) {
			System.out.print("Data ke-" +i+ " : ");
			arr[i] = sc.nextInt();
		} divide(1, n); // Manggil fungsi divide()
		System.out.print("Ingin diurutkan (A)scending atau (D)escending : ");
		String opt = sc.next().toLowerCase();
		if(opt.equals("d")) descending(n); // Buat Descending
		System.out.print("Data setelah diurutkan : ");
		for(i=1; i<=n; i++) System.out.print(arr[i]+ " "); // Print hasil
		System.out.println(); // New line
	}

	public void divide(int n1,int n2) {
		int mid;
		if(n1<n2) { // Jika masih bisa dibagi
			mid = (n1+n2)/2; // Nilai tengah
			divide(n1, mid); // Nilai kiri bro
			divide(mid+1, n2); // Iki  yg kanan
			merge(n1, mid, n2); // Digabung lagi
		}
	}

	public void merge(int low,int mid,int high) {
		int h = low, i = low, j = mid+1, k; // ora ngerti pisan
		int tmp[] = new int[arr.length]; // ojo tonyo tonyo
		while((h<=mid) && (j<=high)) { // iki logika ne
			if(arr[h]<=arr[j]) { tmp[i]=arr[h]; h++; }
			else { tmp[i]=arr[j]; j++; }
			i++;
		}
		if(h>mid) for(k=j;k<=high;k++) { tmp[i]=arr[k]; i++; }
		else for(k=h;k<=mid;k++) { tmp[i]=arr[k]; i++; }
		for(k=low;k<=high;k++) arr[k]=tmp[k]; // sampek sini
	}

	public void descending(int n) {
		int input[] = new int[50], m = n; // Inisialisasi
		for(int i=1; i<=n; i++) input[i] = arr[i]; // Copy array ke variabel baru
		for(int i=1; i<=m; i++) {
			//System.out.println("arr[" +i+ "] => " +arr[i]+ " ,  input[" +n+ "] => " +input[n]); // Biar ngerti logaritma nya
			arr[i] = input[n]; // Revert
			n--;
		}
	}
}
