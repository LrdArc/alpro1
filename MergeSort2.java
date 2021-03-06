import java.util.*;

class MergeSort2 {
	public int arr[] = new int[50];
	public void merge_sort(int low,int high) {
		int mid;
		if(low<high) {
			mid = (low+high)/2;
			merge_sort(low, mid);
			merge_sort(mid+1, high);
			merge(low, mid, high);
		}
	}

	public void merge(int low,int mid,int high) {
		int h,i,j,k;
		int b[]=new int[50];
		h = low;
		i = low;
		j = mid+1;
		while((h<=mid)&&(j<=high)) {
			if(arr[h]<=arr[j]) {
				b[i]=arr[h];
				h++;
			} else {
				b[i]=arr[j];
				j++;
			}
			i++;
		}
		if(h>mid) {
			for(k=j;k<=high;k++) {
				b[i]=arr[k];
				i++;
			}
		} else {
			for(k=h;k<=mid;k++) {
				b[i]=arr[k];
				i++;
			}
		}
		for(k=low;k<=high;k++) arr[k]=b[k];
	}

	public MergeSort2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Masukkan jumlah data : ");
		int n = sc.nextInt(), i;
		for(i=1; i<=n; i++) {
			System.out.print("Data ke-" +i+ " : ");
			arr[i] = sc.nextInt();
		}

		merge_sort(1,n);
		System.out.print("Data setelah diurutkan : ");
		for(i=1; i<=n; i++) System.out.print(arr[i]+ " ");
		System.out.println();
	}

	public static void main(String[] args) {
		new MergeSort2();
	}
}

