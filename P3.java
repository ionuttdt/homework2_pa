import java.io.*;
import java.util.*;

public class P3 {

	public static class Edge {
		public int node;
		public int cost;
		public int tip;

		Edge(int _node, int _cost, int _tip) {
			node = _node;
			cost = _cost;
			tip = _tip;
		}
	}

	public static int getResult(ArrayList<Edge> adj[], int n, int[][] pen) {

		int s = 1;
		int ans = -1;
		ArrayList<Integer> d = new ArrayList<>();

		for (int i = 0; i <= n; i++)
				d.add(Integer.MAX_VALUE);
		d.set(s,0);

		PriorityQueue<Edge> q = new PriorityQueue<>(201,new Comparator<Edge>(){
															public int compare(Edge a,Edge b){
																return a.cost - b.cost;
															}});
		boolean[] viz = new boolean[n+1];

		q.add(new Edge(s, 0, -1));

		while(q.size() > 0) {

			Edge u_ = q.remove();

			if(u_.cost <= d.get(u_.node)) {

				viz[u_.node] = true;
				for(Edge i : adj[u_.node]){
					if(!viz[i.node] ) {
						if(u_.tip == -1) {
							d.set(i.node, d.get(u_.node) + i.cost);
							q.add(new Edge(i.node,d.get(i.node),i.tip ) );
						}
						else if(d.get(i.node) > d.get(u_.node) + i.cost + pen[u_.tip - 1][i.tip - 1] ) {
							d.set(i.node, d.get(u_.node) + i.cost + pen[u_.tip - 1][i.tip - 1] );
							q.add(new Edge(i.node,d.get(i.node),i.tip ) );
						}
					}
				}
			}

		}
		for(int i:d)
			System.out.print(i +" ");
		ans = (d.get(n) == Integer.MAX_VALUE) ? -1 : d.get(n);
		return ans;

	}

	public static void main(String[] args) throws Exception {

		// citirea din fisier
		FileReader file = new FileReader("p3.in");
		BufferedReader br = new BufferedReader(file);

		// scriere in fisier
		BufferedWriter writer = new BufferedWriter(new FileWriter("p3.out", true));

		// datele citite din fisier
		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[201];
		

		String input = br.readLine();
		String[] vaux =  input.split(" ");

		int n = Integer.parseInt(vaux[0]);		// numar noduri
		int m = Integer.parseInt(vaux[1]);		// numar muchii
		int t = Integer.parseInt(vaux[2]);		// tipuri de muchii
		int[][] pen = new int[t][t]; 			// matricea de penalizari

		// aloc spatiu pentru
		for (int i = 1; i <= n; i++)
			adj[i] = new ArrayList<>();

		// graful propriu-zis
		for(int i = 0; i < m; i++) {
			int u_, v_;
			int c_, t_;
			input = br.readLine();
			vaux =  input.split(" ");
			u_ = Integer.parseInt(vaux[0]);
			v_ = Integer.parseInt(vaux[1]);
			c_ = Integer.parseInt(vaux[2]);
			t_ = Integer.parseInt(vaux[3]);
			adj[u_].add(new Edge(v_, c_, t_));
			adj[v_].add(new Edge(u_, c_, t_));
		}

		// matricea de penalizari
		for(int i = 0; i < t; i++) {
			input = br.readLine();
			vaux =  input.split(" ");
			for(int j = 0; j < t; j++) {
				pen[i][j] = Integer.parseInt(vaux[j]);
			}
		}
		
/*		System.out.println(n +" "+m+" "+t);
		for(int i =0; i < t ; i ++) {
			for(int j = 0; j < t; j++) {
				System.out.print(pen[i][j]+" ");
			}System.out.println();
		}*/

		//raspunsul optim
		int ans = getResult(adj, n, pen);
		writer.write(String.valueOf(ans));

		System.out.println("Raspuns: " + ans);

		//inchiderea bufferelor
		br.close();
		writer.close();
	}

}