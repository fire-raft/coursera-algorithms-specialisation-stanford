def kratsuba(x,y):
	if x.bit_length()<=64 or y.bit_length()<=64:
		return x*y
	else:
		maxlength=max(y.bit_length(), x.bit_length())
		half=(maxlength+32)//64*32
		shifter=(1<<half)-1
		xleft=x & shifter
		yleft=y & shifter
		xright=x >> half
		yright=y>>half
		l=kratsuba(xright,yright)
		m=kratsuba(xleft+xright,yleft+yright)
		n=kratsuba(xleft,yleft)
		d=m-l-n
		return (((l << half)+d ) << half)+n
print (kratsuba(5,10))
print (kratsuba(11,7))
print (kratsuba(3141592653589793238462643383279502884197169399375105820974944592,2718281828459045235360287471352662497757247093699959574966967627))