movl r1, 05h
movh r1, 00h

movl r2, 09h
movh r2, 00h

push r2
push r1

movl r3, 00h
movh r3, 05h


call Max

inc r7
inc r7
		
mov [r3], r0

nop

Max:
	push r6
	mov r6, r7
	
	push r1
	push r2
	
	inc r6
	inc r6
	
	mov r1, [r6]
	inc r6
	mov r2, [r6] 
	
	cmp r1, r2
	brnc check_zero
	jmp continue
	check_zero:
		brnz consequent
		jmp continue
	
	consequent:
		mov r0, r1
		jmp next
		
	continue:
		mov r0, r2
		jmp next
	
	next:
		pop r2
		pop r1
		
		
		pop r6
		
		ret
		
