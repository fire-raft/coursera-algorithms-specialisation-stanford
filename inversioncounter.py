fread=open("IntegerArray.txt","r")
a=[]
for line in fin:
	a.append(int(line.strip()))
def merge(a1,a2):
	q=[]
	global count,count2
	len_a1=len(a1)
	len_a2=len(a2)
	i=0
	j=0
	while i<len_a1 and j<len_a2:
		if a1[i]<=a2[j]:
			q.append(a1[i])
			i=i+1
		else:
			count=count+len(a1)-i
			q.append(a2[j])
			j=j+1
	if i==len_a1:
		q.extend(a2[j:])
	else:
		q.extend(a1[i:])
	return q
def mergesort(arr):
	z=len(arr)
	if z>1:
		s1=mergesort(arr[0:int(z/2)])
		s2=mergesort(arr[int(z/2):])
		return merge(s1,s2)
	else:
		return arr
count=0
count2=0
mergesort(a)
print(count)