import sys
from heapq import *
sys.setrecursionlimit(2000)

X={}

class BT:
	def __init__(self, left = None, right = None, value = None):
		self.value = value
		self.left = BT(None, None, left) if left else None
		self.right = BT(None, None, right) if right else None
		X[self.value] = self

	def getLength(self, fn):
		if not (self.left and self.right):
			return 0

		leftLength = self.left.getLength(fn) if self.left else 0
		rightLength = self.right.getLength(fn) if self.right else 0

		return 1 + fn(leftLength, rightLength)

def huff(alpha):
	if len(alpha)==2:
		return BT(alpha[0][1],alpha[1][1])
	(ap,a)=heappop(alpha)
	(bp,b)=heappop(alpha)
	ba=str(a)+'_'+str(b)
	heappush(alpha,(ap+bp,ba))
	T=huff(alpha)
	
	X[ba].__dict__.update(BT(a,b).__dict__)
	return T

alpha=[]
with open('huffman.txt')as f:
	f.readline()
	charn=0
	for line in f:
		charn+=1
		heappush(alpha, (int(line.rstrip()), charn))
tree=huff(alpha)

print(tree.getLength(max))
print(tree.getLength(min))