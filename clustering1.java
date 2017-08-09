import java.io.*;
import java.util.*;

public class clustering1
{
	static int[] parent;
	static class edge implements Comparable<edge>
	{
		int i,j,cst;
		public edge(int i,int j,int cst)
		{
			this.i=i;
			this.j=j;
			this.cst=cst;
		}
		@Override
		public int compareTo(edge arg)
		{
			final int bf=1;
			final int af=-1;
			if(this.cst>=arg.cst)
				return bf;
			else
				return af;
		}
	}
	
	public static int sea(int x)
	{
		while(parent[x]>=1)
		{
			x=parent[x];
		}
		return x;
	}
	
	public static void union(int i,int j)
	{
		int ipre=sea(i);
		int jpre=sea(j);
		if(ipre==jpre)
		{
			parent[ipre]+=-1;
		}
		else
		{
			if(parent[ipre]<parent[jpre])
			{
				parent[ipre]=parent[jpre];
				parent[jpre]=ipre;
			}
			else
			{
				parent[jpre]+=parent[ipre];
				parent[ipre]=jpre;
			}
		}
	}
	public static int nclus()
	{
		int c=0;
		for(int i=0;i<parent.length;i++)
		{
			if(parent[i]<0)
				c++;
			
		}
		return c;
	}
	
	public static void main(String[] args) throws IOException
	{
		ArrayList<edge> e1=new ArrayList<edge>();
		ArrayList<ArrayList<Integer>> c1=new ArrayList<ArrayList<Integer>>();
		int n=0,k=4;
		FileInputStream f=new FileInputStream("clustering1.txt");
		DataInputStream dsr=new DataInputStream(f);
		BufferedReader br=new BufferedReader(new InputStreamReader(dsr));
		n=Integer.parseInt(br.readLine());
		parent=new int[n];
		
		for(int i=0;i<n;i++)
		{
			parent[i]=-1;
		}
		int i,j,v;
		String s1;
		while((s1=br.readLine())!=null)
		{
			i=Integer.parseInt(s1.split(" ")[0]);
			j=Integer.parseInt(s1.split(" ")[1]);
			v=Integer.parseInt(s1.split(" ")[2]);
			e1.add(new edge(i-1,j-1,v));
		}
		Collections.sort(e1);
		for(edge e:e1)
		{
			union(e.i,e.j);
			if(nclus()==k)
				break;
		}
		int min=Integer.MAX_VALUE;
		for(edge e:e1)
		{
			if(sea(e.i)!=sea(e.j))
				min=Math.min(min,e.cst);
		}
		System.out.print(min);
	}
}