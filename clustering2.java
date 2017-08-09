import java.io.*;
import java.util.*;

public class clustering2
{
	static int n,numB;
	static HashMap<BitSet, BitSet> clust=new HashMap<BitSet, BitSet>();
	
	public static BitSet getBitSet(String str)
	{
		String st1[]=str.split(" ");
		BitSet b=new BitSet(numB);
		int i=numB-1;
		b.clear();
		for(int j=0;j<st1.length;j++)
		{
			if(Integer.parseInt(st1[j])==1)
			{
				b.flip(i);
			}
			i--;
		}
		return b;
	}
	
	public static BitSet find(BitSet b)
	{
		while(!b.equals(clust.get(b)))
		{
			b=clust.get(b);
		}
		return b;
	}
	
	public static void union(BitSet a,BitSet b)
	{
		BitSet fa=find(a);
		BitSet fb=find(b);
		if(!fa.equals(fb))
		{
			clust.put(fa,fb);
		}
	}
	
	public static ArrayList<BitSet> getMembers(BitSet s)
	{
		BitSet sb = (BitSet)s.clone();  
        ArrayList<BitSet> r = new ArrayList<BitSet>(); 
		for(int j=0;j<=numB-1;j++)
		{
			BitSet b1=new BitSet();
			b1.clear();
			b1=(BitSet)sb.clone();
			b1.flip(j);
			if(clust.containsKey(b1))
			{
				r.add(b1);
			}
		}
		for(int j=0;j<=numB-1;j++)
		{
			BitSet b1=new BitSet();
			b1.clear();
			b1=(BitSet)sb.clone();
			b1.flip(j);
			for(int i=j+1;i<=numB-1;i++)
			{
				BitSet b2=new BitSet();
				b2=(BitSet) b1.clone();
				b2.flip(i);
				if(clust.containsKey(b2))
					r.add(b2);
			}
		}
		return r;
	}
	public static void main(String[] args) throws IOException
	{
		FileInputStream f=new FileInputStream("clustering2.txt");
		DataInputStream dsr=new DataInputStream(f);
		BufferedReader br=new BufferedReader(new InputStreamReader(dsr));
		String st=br.readLine();
		n=Integer.parseInt(st.split(" ")[0]);
		numB=Integer.parseInt(st.split(" ")[1]);
		int counter=0;
		while((st=br.readLine())!=null)
		{
			BitSet b2=getBitSet(st);
			clust.put(b2,b2);
		}
		for(BitSet s:clust.keySet())
		{
			ArrayList<BitSet> members=getMembers(s);
			if(members.size()==0)
				counter++;
			for(BitSet m:members)
			{
				union(s,m);
			}
		}
		int count1=0;
		for(Entry<BitSet, BitSet> e : clust.entrySet())
		{
			if(e.getKey().equals(e.getValue()))
			{
				count1++;
			}
		}
		System.out.print(count1);
	}
}