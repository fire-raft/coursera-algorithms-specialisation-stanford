import java.io.*;
import java.lang.*;

public class shortestpath
{
	static int[][] tree;
	static int[][][] a;
	static int i;
	
	public static void main(String[] args) throws IOException
	{
			FileInputStream f=new FileInputStream("g1.txt");
			DataInputStream dsr=new DataInputStream(f);
			BufferedReader br=new BufferedReader(new InputStreamReader(dsr));
			i=Integer.parseInt(br.readLine().split(" ")[0]);
			tree=new int[i][i];
			a=new int[i][i][2];
			for(int n=0;n<i;n++)
			{
				for(int m=0;m<i;m++)
				{
					if(n==m)
					{
						tree[n][m]=0;
					}
					else
					{
						tree[n][m]=999999;
					}
				}
			}
			String st1;
			int p,q,r;
			while((st1=br.readLine())!=null)
			{
				p=Integer.parseInt(st1.split(" ")[0]);
				q=Integer.parseInt(st1.split(" ")[1]);
				r=Integer.parseInt(st1.split(" ")[2]);
				tree[p-1][q-1]=r;
			}
			for(int j=0;j<i;j++)
			{
				for(int k=0;k<i;k++)
				{
					if(j==k)
					{
						a[j][k][0]=0;
					}
					else
					{
						a[j][k][0]=tree[j][k];
					}
				}
			}
			for(int l=0; l<i;l++)
			{
				for(int m=0;m<i;m++)
				{
					for(int n=0;n<i;n++)
					{
						a[m][n][1]=Math.min(a[m][n][0],a[m][l][0]+a[l][n][0]);
					}
				}
				for(int m=0;m<i;m++)
				{
					for(int n=0;n<i;n++)
					{
						a[m][n][0]=a[m][n][1];
					}
				}
			}
			int n;
			for(n=0;n<i;n++)
			{
				if(a[n][n][0]<0)
				{
					System.out.println("Graph has a negative cycle");
					break;
				}
			}
			if(n==i)
			{
				System.out.println("Graph doesn't have negative cycle");
				int minimum=0;
				for(int s=0;s<i;s++)
				{
					for(int t=0;t<i;t++)
					{
						minimum=Math.min(minimum,a[s][t][0]);
					}
				}
				System.out.println("minimum cost is"+ minimum);
			}
	}
}