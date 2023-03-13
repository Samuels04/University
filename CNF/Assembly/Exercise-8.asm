cmp r0, r1

brz consequent
movl r2, 02h
movh r2, 00h
jmp continue

consequent:
	movl r2, 01h
	movh r2, 00h
continue:
	mov r3, r2