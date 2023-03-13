movl r1, 05h
movh r1, 00h

movl r2, 09h
movh r2, 00h

movl r3, 00h
movh r3, 05h

movl r4, 01h
movh r4, 05h

push r4
push r3
push r2
push r1

call MaxMin

inc r7
inc r7
inc r7
inc r7

nop

MaxMin:
	push r6
	mov r6, r7
	
	push r1
	push r2
	push r3
	push r4
	
	inc r6
	inc r6
	
	mov r1, [r6]
	inc r6
	mov r2, [r6]
	inc r6
	mov r3, [r6]
	inc r6
	mov r4, [r6]
	
	cmp r1, r2
	brnc check_zero
	jmp continue
	
	check_zero:
		brnz consequent
		jmp continue
		
	consequent:
		mov [r3], r1
		jmp next
	continue:
		mov [r3], r2
		mov [r4], r1
		jmp next
		
	next:
		pop r4
		pop r3
		pop r2
		pop r1
		
		
		pop r6
		
		ret
	