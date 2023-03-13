xor r0, r0, r0; a = 0

xor r1, r1, r1; i = 0

movl r2, 64h
movh r2, 00h

jmp check_condition
check_condition:
	cmp r1, r2
	brc for_loop
	jmp continue

for_loop:
	add r0, r0, r1
	inc r1
	jmp check_condition
	
continue:
