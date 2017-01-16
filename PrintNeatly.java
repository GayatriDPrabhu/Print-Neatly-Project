import java.util.*;
import java.io.*;

public class PrintNeatly{
	static int nsum[][];
	public static void main(String args[]){
		String fn="";
		int m = 80;
		if(args.length > 0)
			fn = args[0];
		if(args.length > 1)
			m = Integer.parseInt(args[1]);
		
		File f = new File(args[0]);
		String[] w = getWords(f);	
		System.out.println();		
		int J[] = printNeat(w,m);
		nicePrint(w,J,m);
	}

	static int[] printNeat(String w[],int m){
		int n = w.length;
		nsum = new int[n][n];
		int J[] = new int[n];
		int pn[] = new int[n];
		//base
		int mcube = m*m*m;
		n--;
		pn[n] = 0;
		//sub-problems
		for(int i = n-1;i >= 0;i--){
			
		int min=mcube;
		int minj = 0;
			for(int j=i;j <= n;j++){
				
				int tmp = sum(w,i,j) + j - i;
				nsum[i][j] = tmp;
				if(tmp <= m){
					if(j == n){
						min = 0;
					}
					else{
						if(min > (pn[j+1] + cube(m - tmp))){
						min = pn[j+1] + cube(m - tmp);
						minj = j;
						}
					}
				}
			}
			pn[i] = min;
			J[i] = minj;
			// System.out.println("min for "+ i+" : "+J[i]);
		}
		System.out.println(pn[0]);
		return J;
		
	}

	static int sum(String a[],int p,int q){
		int sum = 0;
		for(int i=p;i<=q;i++){
			sum += a[i].length();
		}
		
		return sum;
	}

	static int cube(int k){
		return k*k*k;
	}
	
	static String[] getWords(File f){
		try{
			String s = new Scanner(f).useDelimiter("\\Z").next();
			String w[] = s.split("\\s+");
			for(int i=0;i<w.length;i++)
				
			return w;
		}
		catch(Exception e){
			
		}
		return (new String[1]);
	}

	static void nicePrint(String w[],int J[],int limit){
		int q = w.length;
		for(int i=1;i < q;i++){
			i--;
			int j = J[i];
			int jump;
			int unu = limit - nsum[i][j];
			int now = j-i;
			if(now >= unu && j!= q){
				//print ONE space after every now/unu words
				if(unu == 0)
					jump = limit;
				else{
					jump = now / unu;
					
				}

				int wc = 0;
				for(int k = i;k <= j;k++){
					System.out.print(w[k] + " ");
					if(wc % jump == 0 && unu>0) {
						System.out.print(" ");
						unu--;
					}
					wc++;
				}
			}
			else{
				//print unu/now spaces after EACH word
				for(int k = i;k <= j;k++)
					System.out.print(w[k] + " ");
				
			}
			i = j+1;
			if(i == q-1){
				System.out.println(w[q-1]);
			}
			else
				System.out.println();
		}
	}
}
