import random as rndm
import copy as cpy

def cut(md,edge):
	while len(md)>2:
		[u,v]=edge.pop(rndm.randrange(0,len(edge)-1))
		while [v,u] in edge:
			edge.remove([v,u])
		while [u,v] in edge:
			edge.remove([u,v])
		for d in range(0,len(edge)-1):
			if edge[d][0]==v:edge[d][0]=u
			if edge[d][1]==v:edge[d][1]=u
		md[u]=md[u]-{v}
		md[v]=md[v]-{u}
		for [x,y] in md.items():
			if v in y:
				md[x]=(md[x]|{u})-{v}
		md[u]=md[u]|md[v]
		del md[v]
	return len(edge)/2
	
fread=open("kargerMinCut.txt","r")
mapD={}
for line in fread.readlines():
	temp=[int(x) for x in line.split()]
	mapD[temp[0]]=set(temp[1:])
edge=[]
for [x,y] in mapD.items():
		edge.extend([[x,v]for v in y])
lst=[]
for i in range(1000):
	cpmd=cpy.deepcopy(mapD)
	cpedge=cpy.deepcopy(edge)
	n=cut(cpmd,cpedge)
	lst.append(n)
	lst.sort()
	print(n)
	i=i+1
print(lst)