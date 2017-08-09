file=open('dijkstraData.txt','r')
vert=dict()
for line in file.readlines():
	temp=line.split()
	try:
		vert[int(temp[0])]={int(x.split(',')[0]): int(x.split(',')[1]) for x in temp[1:]}
	except:
		print('error in creating dictionary at'+temp)

l=200
exp=[1]
noexp=list(range(2,l+1))
dist={x:0 for x in range(1,l+1)}

while len(exp)<l:
	maxlim=1000000
	tempdist=maxlim
	lenv=tempdist
	for v in exp:
		for w in noexp:
			if w in vert[v].keys():
				lenv=dist[v]+vert[v][w]
				if lenv<tempdist:
					tempdist=lenv
					tempv=v
					tempw=w
	if tempdist==maxlim:break
	exp.append(tempw)
	noexp.remove(tempw)
	dist[tempw]=tempdist
	
for i in [7,37,59,82,99,115,133,165,188,197]:
	print(str(dist[i]))