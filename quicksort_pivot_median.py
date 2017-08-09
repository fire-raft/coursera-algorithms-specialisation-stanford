def mid(x):
    if len(x)%2==0:
        middle=len(x)/2 - 1
    else:
        middle=len(x)/2
    return middle

def median(x,i,j,k):
    if (x[int(i)]-x[int(j)])*(x[int(i)]-x[int(k)])<0:
        return i
    else:
		if (x[int(j)]-x[int(i)])*(x[int(j)]-x[int(k)])<0:
			return j
	else:
		return k

def quicksort(x)
	global count
	if len(x)==1 or len(x)==0:
		return x
	else:
		count=count+len(x)-1
		medan=median(x,0,mid(x),-1)
		if int(k)!=0:
			x[0],x[int(medan)]=x[int(medan)],x[0]
		i=0
		for j in range(len(x)-1):
			if x[j+1]<x[0]:
				x[j+1],x[i+1]=x[i+1],x[j+1]
				i=i+1
		x[0],x[i]=x[i],x[0]
		a1=quicksort(x[:i])
		a2=quicksort(x[i+1:])
		a1.append(x[i])
		return a1+a2
count=0

fread=open("QuickSort_List.txt","r")
with fread as f:x=[int(integers.strip()) for integers in fread.readlines()]
quicksort(x)
print(count)