import java.io.*;
import java.lang.*;
import java.util.*;

public class tsp
{
	static int ncity;
	static Node fnode=new Node();
	static double[][] costmat;
	static double[][] costmatnew;
	
	public static void main(String[] args) throws IOException
	{
		List<city> cityarray=readcityfile();
		createDistancefromcity(cityarray);
		System.out.println("output"+ solve());
	}
	
	private static void createDistancefromcity(List<city> cityarray)
	{
		costmat=new double[ncity][ncity];
		
		for(int i=0;i<ncity;i++)
		{
			city c=cityarray.get(i);
			for(int j=0; j<ncity;j++)
			{
				city c2=cityarray.get(j);
				costmat[i][j] = c.getDisttocity(c2);
			}
		}
	}
	
	private static List<city> readcityfile() throws IOException
	{
		ArrayList<city> cityarray=new ArrayList<city>();
		FileInputStream fsr=new FileInputStream("tsp.txt");
		DataInputStream dsr=new DataInputStream(fsr);
		InputStreamReader isr=new InputStreamReader(dsr);
		BufferedReader br=new BufferedReader(isr);
		
		ncity=Integer.parseInt(br.readLine());
		
		for(int i=0; i<ncity;i++)
		{
			String l=br.readLine();
			double x=Double.valueOf(l.split(" ")[0]);
			double y=Double.valueOf(l.split(" ")[1]);
			cityarray.add(new city(x,y));
		}
		
		return cityarray;
	}
	
	private static double solve()
	{
		fnode.lb=Double.MAX_VALUE;
		Node cnode=new Node();
		cnode.excluded=new boolean[ncity][ncity];
		costmatnew=new double[ncity][ncity];
		cnode.hkarp();
		
		PriorityQueue<Node> que = new PriorityQueue<Node>(ncity, new NodeComparator());
		do
		{
			do
			{
				int i=-1;
				for(int j=0;j<ncity;j++)
				{
					if(cnode.deg[j]>2 && (i<0||cnode.deg[j]<cnode.deg[i]))
					{
						i=j;
					}
				}
				if(i<0)
				{
					if(cnode.lb<fnode.lb)
					{
						fnode=cnode;
					}
					break;
				}
				PriorityQueue<Node> child=new PriorityQueue<Node>(ncity, new NodeComparator());
				child.add(cnode.exclude(i,cnode.prev[i]));
				
				for(int j=0;j<ncity; j++)
				{
					if(cnode.prev[j]==i)
					{
						child.add(cnode.exclude(i,j));
					}
				}
				
				cnode=child.poll();
				que.addAll(child);
			}
			while(cnode.lb<fnode.lb);
			cnode=que.poll();
		}
		while(cnode!=null && cnode.lb<fnode.lb);
		return fnode.lb;
	}
	
	static class Node
	{
		boolean[][] excluded;
		int[] deg;
		int[] prev;
		double[] mp;
		double lb;
		double ub;
		public void hkarp()
		{
			this.mp=new double[ncity];
			this.lb=Double.MIN_VALUE;
			this.deg=new int[ncity];
			this.prev=new int[ncity];
			
			double la=0.1;
			while(la>1e-06)
			{
				double prevlb=this.lb;
				getTree();
				if(!(this.lb<fnode.lb))
				{
					return;
				}
				if(!(this.lb<prevlb))
				{
					la=la*0.9;
				}
				
				int lower=0;
				for(int i=1; i<ncity;i++)
				{
					int d =this.deg[i]-2;
					lower+=d*d;
				}
				if(lower==0)
				{
					return;
				}
				double t=la*this.lb/lower;
				for(int i=1;i<ncity;i++)
				{
					this.mp[i]+=t*(this.deg[i]-2);
				}
			}
		}
		
		private Node exclude(int i, int j)
		{
			Node next=new Node();
			next.excluded=this.excluded.clone();
			next.excluded[i]=this.excluded[i].clone();
			next.excluded[j]=this.excluded[j].clone();
			next.excluded[i][j]=true;
			next.excluded[j][i]=true;
			
			next.hkarp();
			return next;
		}
		
		private void addEdge(int i, int j)
		{
			double a=this.lb;
			this.lb+=costmatnew[i][j];
			this.deg[i]++;
			this.deg[j]++;
		}
		
		private void getTree()
		{
			this.lb=0.0;
			Arrays.fill(this.deg, 0);
			for(int i=0; i<ncity; i++)
			{
				for(int j=0; j<ncity;j++)
				{
					costmatnew[i][j]=this.excluded[i][j]?Double.MAX_VALUE:costmat[i][j]+this.mp[i]+this.mp[j];
				}
			}
			
			int neigh1,neigh2;
			if(costmatnew[0][2]<costmatnew[0][1])
			{
				neigh1=2;
				neigh2=1;
			}
			else
			{
				neigh1=1;
				neigh2=2;
			}
			
			for(int j=3; j<ncity;j++)
			{
				if(costmatnew[0][j]<costmatnew[0][neigh2])
				{
					if(costmatnew[0][j]<costmatnew[0][neigh1])
					{
						neigh2=neigh1;
						neigh1=j;
					}
					else
					{
						neigh2=j;
					}
				}
			}
			
			addEdge(0,neigh1);
			Arrays.fill(this.prev, neigh1);
			this.prev[neigh1]=0;
			
			double[] costmin=costmatnew[neigh1].clone();
			
			for(int k=2;k<ncity;k++)
			{
				int i;
				for(i=1;i<ncity;i++)
				{
					if(this.deg[i]==0)
					{
						break;
					}
				}
				for(int j=i+1; j <ncity; j++)
				{
					if(this.deg[j]==0 && costmin[j]<costmin[i])
					{
						i=j;
					}
				}
				
				addEdge(this.prev[i],i);
					for(int j =1; j<ncity;j++)
					{
						if(this.deg[j]==0&& costmatnew[i][j]<costmin[j])
						{
							costmin[j]=costmatnew[i][j];
							this.prev[j]=i;
						}
					}
				
			}
			addEdge(0, neigh2);
			this.prev[0]=neigh2;
		}
	}
	
	static class NodeComparator implements Comparator<Node>
	{
		@Override
        public int compare(Node a, Node b) 
		{
			return Double.compare(a.lb, b.lb);
		}
	}
	
	static class city 
	{
		double x,y;
		public city(double x, double y)
		{
			this.x=x;
			this.y=y;
		}
		
		public double getDisttocity(city c2)
		{
			return Math.sqrt(Math.pow(x-c2.x, 2)+ Math.pow(y-c2.y, 2));
		}
	}
}