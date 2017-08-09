import java.io.*;

public class edge
{
static int nNodes;
static int[] tree;
static int[][] graph;

public static void prims()
{
int w=0;
boolean[] status=new boolean[nNodes];
for(boolean i:status)
	i=false;
status[0]=true;
int imin=0,jmin=0;
int wmin=Integer.MAX_VALUE;

while(!comp(status))
{
for(int i=0;i<nNodes;i++)
{
if(status[i])
	continue;
for(int j=0;j<nNodes;j++)
{
if(!status[j])
	continue;
if(wmin>graph[i][j])
{
wmin=graph[i][j];
imin=i;
jmin=j;
}
}
}
status[imin]=true;
tree[jmin]=imin;
w+=graph[imin][jmin];
wmin=Integer.MAX_VALUE;
}
System.out.print(w);
}

public static boolean comp(boolean[] status)
{
	for(boolean f:status)
	{
		if(f==false)
			return false;
	}
	return true;
}

public static void main(String[] args) throws IOException
{
	FileInputStream f=new FileInputStream("edges.txt");
	DataInputStream dsr=new DataInputStream(f);
	BufferedReader br=new BufferedReader(new InputStreamReader(f));
	nNodes=Integer.parseInt(br.readLine().split(" ")[0]);
	graph=new int[nNodes][nNodes];
	for(int i=0;i<nNodes;i++)
	{
		for(int j=0; j<nNodes;j++)
			graph[i][j]=Integer.MAX_VALUE;
	}
	String s1;
	while((s1=br.readLine())!=null)
	{
		int i=Integer.parseInt(s1.split(" ")[0])-1;
		int j=Integer.parseInt(s1.split(" ")[1])-1;
		graph[i][j]=Integer.parseInt(s1.split(" ")[2]);
		graph[j][i]=Integer.parseInt(s1.split(" ")[2]);
	}
	tree=new int[nNodes];
	for(int i:tree)
		i=-1;
	prims();
}
}