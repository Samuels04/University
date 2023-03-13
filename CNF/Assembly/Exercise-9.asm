movl r1, 00h
movh r1, 05h

mov r2, [r1]; point r2 to b

cmp r0, r2

brnc condition2
jmp continue
condition2:
	brnz consequent
	jmp continue

consequent:
	xor r0, r0, r0

continue:
