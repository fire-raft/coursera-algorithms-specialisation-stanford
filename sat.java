import java.io.*;
import java.util.*;

public class sat
{
	public static void main(String[] args) throws IOException
	{
		ArrayList<Clause> c1=readFromFile("2sat1.txt");
		if(twoSAT(c1))
		{
			System.out.println("2sat1.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat1.txt=0 /// doesn't satisfy");
		}
		ArrayList<Clause> c2=readFromFile("2sat2.txt");
		if(twoSAT(c2))
		{
			System.out.println("2sat2.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat2.txt=0 /// doesn't satisfy");
		}
		ArrayList<Clause> c3=readFromFile("2sat3.txt");
		if(twoSAT(c3))
		{
			System.out.println("2sat3.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat3.txt=0 /// doesn't satisfy");
		}
		ArrayList<Clause> c4=readFromFile("2sat4.txt");
		if(twoSAT(c4))
		{
			System.out.println("2sat4.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat4.txt=0 /// doesn't satisfy");
		}
		ArrayList<Clause> c5=readFromFile("2sat5.txt");
		if(twoSAT(c5))
		{
			System.out.println("2sat5.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat5.txt=0 /// doesn't satisfy");
		}
		ArrayList<Clause> c6=readFromFile("2sat6.txt");
		if(twoSAT(c6))
		{
			System.out.println("2sat6.txt = 1 /// satisfies");
		}
		else
		{
			System.out.println("2sat6.txt=0 /// doesn't satisfy");
		}
	}
	
	private static boolean twoSAT(ArrayList<Clause> clauses)
	{
		ArrayList<Clause> fclauses=new ArrayList<Clause>();
		ArrayList<Boolean> carray=new ArrayList<Boolean>();
		
		for(int i=0; i<clauses.size();i++)
		{
			carray.add(true);
		}
		
		int j=clauses.size();
		int counter=0;
		boolean status=false;
		
		while(counter<=Math.log(j)/Math.log(2))
		{
			int counternew=1;
			Collections.shuffle(carray);
			
			while(counternew<= (double)2*j*j);
			{
				Clause t=getRfalse(clauses, fclauses,carray);
				
				if(t==null)
				{
					status=true;
					break;
				}
				else
				{
					int k=new Random().nextInt(2)+1;
					
					if(k==1)
					{
						Boolean b=carray.get(Math.abs(t.getA()));
						carray.set(Math.abs(t.getA()), !b);
					}
					else
					{
						Boolean b=carray.get(Math.abs(t.getB()));
						carray.set(Math.abs(t.getB()), !b);
					}
				}
				
				counternew++;
			}
			if(status)
			{
				break;
			}
			counter++;
		}
		
		return status;
	}
	
	private static Clause getRfalse(ArrayList<Clause> clauses, ArrayList<Clause> fclauses, ArrayList<Boolean> carray)
	{
		Clause randClause=null;
		fclauses.clear();
		for(Clause a:clauses)
		{
			if(!a.evaluate(carray.get(Math.abs(a.getA())), carray.get(Math.abs(a.getB()))))
			{
				fclauses.add(a);
			}
		}
		
		if(!fclauses.isEmpty())
		{
			Collections.shuffle(fclauses);
			randClause=fclauses.get(0);
		}
		return randClause;
	}
	
	private static ArrayList<Clause> readFromFile(String file) throws IOException
	{
		ArrayList<Clause> clauses=null;
		
		BufferedReader br=new BufferedReader(new FileReader(file));
		int item=Integer.parseInt(br.readLine());
		clauses=new ArrayList<Clause>(item);
		String s1;
		while((s1=br.readLine())!=null)
		{
			int l=Integer.parseInt(s1.split(" ")[0]);
			if(l<0)
			{
				l=l+1;
			}
			else{
				l=l-1;
			}
			int m=Integer.parseInt(s1.split(" ")[1]);
			if(m<0)
			{
				m=m+1;
			}
			else{
				m=m-1;
			}
			clauses.add(new Clause(l, m));
		}
		br.close();
		return clauses;
	}
}

class Clause{
	int x,y;
	
	public Clause(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getB()
	{
		return y;
	}
	public int getA()
	{
		return x;
	}
	
	public boolean evaluate(boolean i, boolean j)
	{
		if(x<0)
		{
			i=!i;
		}
		if(y<0)
		{
			j=!j;
		}
		return i || j;
	}
}