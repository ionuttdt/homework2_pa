import java.io.*;
import java.util.*;

public class P1 {

	static class Pair implements Comparable<Pair> {
    	public final int index;
    	public final int cost;

    	public Pair(int index, int cost) {
     	   this.index = index;
     	   this.cost = cost;
    	}

	    @Override
	    public int compareTo(Pair other) {
	        return Integer.valueOf(this.cost).compareTo(other.cost);
	    }
    };

	public static int getResult(ArrayList<Pair> v, int n, int[][] ans) {
		int node = 1;
		int cost_ = 1;

		if(v.get(0).cost != 0)
			return -1;

		for(int i = 1; i < n; i++) {
			if(v.get(i).cost != cost_)
				return -1;
			else {
				ans[i-1][0] = node;
				ans[i-1][1] = v.get(i).index;
			}

			if(i == n-1)
				break;

			if(v.get(i+1).cost == cost_ + 1) {
				cost_++;
				node = v.get(i).index;
			}
			else if(v.get(i+1).cost > cost_ + 1)
				return -1;

		}
		return n-1;
	}

	public static void main(String[] args) throws Exception {
//		long startTime = System.currentTimeMillis();
		//fisierul cu datele de intrare
		FileReader file = new FileReader("p1.in");

		//Buffer pentru scriere si citire
		BufferedReader br = new BufferedReader(file);
		BufferedWriter writer = new BufferedWriter(new FileWriter("p1.out", true));
		PrintWriter wFile = new PrintWriter(writer);

		//numarul de elemente din vector
		int n = Integer.parseInt(br.readLine());

		//elementele din vector
		String input = br.readLine();
		String[] vaux =  input.split(" ");
		ArrayList<Pair> v = new ArrayList<Pair>();

		for(int i = 0; i < n; i++) {
			Pair aux = new Pair(i+1, Integer.parseInt(vaux[i]));
			v.add(aux);
		}

		//sortarea elementelor
		Collections.sort(v);
		//raspunsul optim
		int[][] ans = new int[n-1][2];
		int test = getResult(v, n, ans);
		if (test == -1) {
			wFile.println(String.valueOf(test));
		}
		else
			wFile.println(String.valueOf(test));

		for(int i = 0; i < n-1; i++) {
			String auxWriter = String.valueOf(ans[i][0]) + " " + String.valueOf(ans[i][1]);
		//	System.out.println(auxWriter);
			wFile.println(auxWriter);
		}

		//inchiderea bufferelor
		br.close();
		wFile.close();
		writer.close();
//			long stopTime = System.currentTimeMillis();
//      	long elapsedTime = stopTime - startTime;
//      	System.out.println(elapsedTime);

	}

}