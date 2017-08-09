f=open('jobs.txt','r')
line=f.readlines()[1:]
job=[]
l=0
w=0

for l in line:
	w=int(l.split()[0])
	l=int(l.split()[1])
	job.append([w,l,float(w)/float(l)])

job=sorted(job,key=lambda X:X[2])
job=job[-1::-1]
tsum,lsum=0,0
for j in  job:
	lsum+=j[1]
	tsum+=j[0]*lsum
print(tsum)