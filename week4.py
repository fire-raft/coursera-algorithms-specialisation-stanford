import sys
num=[int(i) for i in open("2sum.txt")]
tosearch=range(-10000,10000)
ans={}
a={}
for k in num:
	a[k]=True
for k in num:
	for j in tosearch:
		if j-k in a:
			if k==j-k:
				continue
			if j not in ans:
				ans[j]=set([tuple(sorted([k,j-k]))])
			else:
				ans[j].add(tuple(sorted([k,j-k])))
print(len(ans))