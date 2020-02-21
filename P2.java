import java.io.*;
import java.util.*;

public class P2 {

	public static int getResult(int[][] g, int n, int m, int k) {
		int[][] vis = new int[n][m];
		int ans = 1;

		for(int i = 0; i < n; i++) {

			for(int j = 0; j < m; j++) {

				if(vis[i][j] == 1)
					continue;

				int[][] vis_aux = new int[n][m];
				if(vis[i][j] == 0) {
				//	System.out.println(i + " " + j);
					int ans2 = getResultAux(vis ,vis_aux, g, g[i][j], k, i, j, 1);
					ans = Math.max(ans, ans2);
				}


		}
		return ans;

	}
								   // global  // local     // graf    // elementul de plecare
	public static int getResultAux(int[][] v, int[][] vis, int[][] g, int min, int k, int i, int j, int ans) {
		
//System.out.println(ans);

		if(min == g[i][j]) {
			vis[i][j] = 1;
			v[i][j] = 1;
		}
		else 
			vis[i][j] = 1;

		if(i != 0 && vis[i-1][j] == 0 && g[i-1][j] >= min && g[i-1][j] <= min + k) {
			ans = Math.max(ans,getResultAux(v, vis, g, min, k, i-1, j, ans+1));
		}
		if(i < g.length-1 && vis[i+1][j] == 0 && g[i+1][j] >= min && g[i+1][j] <= min + k) {
			ans = Math.max(ans,getResultAux(v, vis, g, min, k, i+1, j, ans+1));
		}
		if(j != 0 && vis[i][j-1] == 0 && g[i][j-1] >= min && g[i][j-1] <= min + k) {
			ans = Math.max(ans,getResultAux(v, vis, g, min, k, i, j-1, ans+1));
		}
		if(j < g[0].length-1 && vis[i][j+1] == 0 && g[i][j+1] >= min && g[i][j+1] <= min + k) {
			ans = Math.max(ans,getResultAux(v, vis, g, min, k, i, j+1, ans+1));
		}



		return ans;

	}

	public static void main(String[] args) throws Exception {

		FileReader file = new FileReader("p2.in");

		//Buffer pentru scriere si citire
		BufferedReader br = new BufferedReader(file);
		BufferedWriter writer = new BufferedWriter(new FileWriter("p2.out", true));

		//numarul de elemente din vector
		String input = br.readLine();
		String[] vaux =  input.split(" ");

		int n = Integer.parseInt(vaux[0]);
		int m = Integer.parseInt(vaux[1]);
		int k = Integer.parseInt(vaux[2]);

		int[][] graph = new int[n][m];

		for(int i = 0; i < n; i++) {
			input = br.readLine();
			vaux =  input.split(" ");
			for (int j = 0; j < m ; j++) {
				graph[i][j] = Integer.parseInt(vaux[j]);
			}
		}

		//raspunsul optim
		int ans = getResult(graph , n, m, k);
		writer.write(String.valueOf(ans));

		System.out.println("Raspuns: " + ans);

		//inchiderea bufferelor
		br.close();
		writer.close();
	}

}