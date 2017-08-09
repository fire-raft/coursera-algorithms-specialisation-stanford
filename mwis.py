def mwis(w):
	n=len(w)-1
	a=[0, w[1]]
	
	for i in range(2, n+1):
		a.append(max(a[i-1],a[i-2]+w[i]))
	i=n
	s=set()
	
	while i>=1:
		if a[i-1]>=a[i-2]+w[i]:
			i-=1
		else:
			s.add(i)
			i-=2
	return s
w=[None]
with open("mwis.txt")as f:
	f.readline()
	for line in f:
		w.append(int(line.rstrip()))
x=mwis(w)

z=[1,2,3,4,17,117,517,997]
print("".join(["1"if i in x else "0" for i in z]))