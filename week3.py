import heapq
import sys
x=[int(i) for i in open("Median.txt")]
low=[]
high=[]
sum=0

for k in x:
	if len(low)>0:
		if k>-low[0]:
			heapq.heappush(high,k)
		else:
			heapq.heappush(low,-k)
	else:
		heapq.heappush(low,-k)
	if len(low)>len(high)+1:
		heapq.heappush(high,-(heapq.heappop(low)))
	elif len(high)>len(low):
		heapq.heappush(low,-(heapq.heappop(high)))
	
	sum+=-low[0]
print(sum)
print(sum%10000)