def knap(item, w):
	n=len(item)
	wrange=range(0,W+1)
	lstrow=[0 for x in wrange]
	for i in range(1, n+1):
		row=[]
		(j,k)=item[i-1]
		for x in wrange:
			if x<k:
				row.append(lstrow[x])
			else:
				row.append(max(lstrow[x],lstrow[x-k]+j))
		lstrow=row
	return lstrow
W=0
item=[]
with open('knapsack2.txt')as f:
	W=int(f.readline().rstrip().split(' ')[0])
	
	for line in f:
		[v,w]=[int(k) for k in line.rstrip().split(' ')]
		item.append((v,w))
opt=knap(item, W)
print(opt)
