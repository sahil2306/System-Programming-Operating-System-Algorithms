# SPOS-Practicals
TE Comp 

*Assignment 1* : 
Pass1 of Assembler

**Example Input**
  
  START	100<br />
A	  &nbsp;&nbsp;	DS	3<br />
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	MOVER CREG, ='5'<br />
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	MOVER DREG, ='9'<br />
L1	&nbsp;&nbsp;	MOVER	AREG,	B<br />
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	ADD	AREG,	C<br />
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	MOVEM	AREG,	D<br />
D	  &nbsp;&nbsp;EQU	A+1<br />
L2	&nbsp;&nbsp;PRINT	D<br />
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	ORIGIN	L2+5<br />
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	STOP<br />
B  	&nbsp;&nbsp;DC	'19<br />
C	  &nbsp;&nbsp;DC	'17<br />
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	END<br />

**Output**<br /><br />
(AD,01) (C,100) <br />
(S,0) (DL,01) (C,3) <br />
(IS,05) (RG,03) (L,1) <br />
(IS,05) (RG,04) (L,2) <br />
(S,1) (IS,05) (RG,01) (S,3) <br />
(IS,02) (RG,01) (S,4) <br />
(IS,06) (RG,01) (S,5) <br />
(S,5) (AD,04) (S,0)+1<br />
(S,6) (IS,11) (S,5) <br />
(AD,03) (C,114) <br />
(IS,01) <br />
(S,3) (DL,02) (C,19) <br />
(S,4) (DL,02) (C,17) <br />
(AD,05) (DL,02) (C,5)<br />
(AD,05) (DL,02) (C,9)<br />
(AD,02) <br />
<br />

-------------- SYMBOL TABLE ---------------<br />
<br />
Index	Symbol	Address<br />
0	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	A	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	100<br />
1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  L1	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	105<br />
3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  B	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	115<br />
4	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  C	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	116<br />
5 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  D	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	101<br />
6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  L2	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	109<br />
<br />
-------------------------------------------<br />

<br />
-------------- LITERAL TABLE --------------<br />

Index	Symbol	Address<br />
1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  5	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	117<br />
2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  9	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	118<br />

-------------------------------------------<br />

<br />
-------------- POOL TABLE ---------------<br />
<br />
Index<br />
0<br />
2<br />
<br />
-----------------------------------------<br />

*Assignment 2* : 
Pass2 of Assembler

**Output**<br />
____ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; ____<br />
10 &nbsp;&nbsp;&nbsp; 00 &nbsp;&nbsp;&nbsp; 1011<br />
05 &nbsp;&nbsp;&nbsp; 02 &nbsp;&nbsp;&nbsp; 1008<br />
06 &nbsp;&nbsp;&nbsp; 02 &nbsp;&nbsp;&nbsp; 1032<br />
04 &nbsp;&nbsp;&nbsp; 02 &nbsp;&nbsp;&nbsp; 1032<br />
05 &nbsp;&nbsp;&nbsp; 03 &nbsp;&nbsp;&nbsp; 1032<br />
07 &nbsp;&nbsp;&nbsp; 03 &nbsp;&nbsp;&nbsp; 1011<br />
08 &nbsp;&nbsp;&nbsp; 04 &nbsp;&nbsp;&nbsp; 1003<br />
06 &nbsp;&nbsp;&nbsp; 02 &nbsp;&nbsp;&nbsp; 1012<br />
00 &nbsp;&nbsp;&nbsp; 00 &nbsp;&nbsp;&nbsp; 1<br />
11 &nbsp;&nbsp;&nbsp; 00 &nbsp;&nbsp;&nbsp; 1012<br />
01 &nbsp;&nbsp;&nbsp; 00 &nbsp;&nbsp;&nbsp; 0000<br />
____ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; ____<br />
____ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; ____<br />
____ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; ____<br />
____ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; __ &nbsp;&nbsp;&nbsp; ____<br />

