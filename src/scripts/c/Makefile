#borrowewd from CG_DESCENT.

EXPORT_ALL_VARIABLES:

PRINTF_EXPONENT_DIGITS := 2

all: numbers-c.txt

# for debug:
#CC = gcc -lm -W -Wall -pedantic -Wmissing-prototypes \
#	-Wredundant-decls -Wnested-externs -Wdisabled-optimization \
#	-ansi -g -fexceptions -Wno-parentheses -Wshadow -Wcast-align \
#	-Winline -Wstrict-prototypes -Wno-unknown-pragmas -g -lpthread

# for blas:
# CC = gcc -lm -O3 -lpthread

# for speed:
#CC = gcc -lm -O3 -posix

# try for Java campatibility
#CC = gcc -lm -posix 
#CC = gcc -O0 -lm -posix -ffloat-store -fwrapv -fno-builtin -mtune=native -mfpmath=sse -fexcess-precision=standard -ffp-contract=on -mfma 
#CC = gcc -lm -posix -march=native -mtune=native -ffp-contract=off -fexcess-precision=standard -mfpmath=sse  -msse2 -ffloat-store
#CC = gcc -posix -std=c99 -O2 -msse3
CC = gcc -lm -O0 -posix -ffloat-store -march=native -ffp-contract=off -std=c99 -mfpmath=sse -frounding-math

# OBJ = cg_descent.o

RM=del

# uncomment this line if using, for example, goto2 blas
# LINK = -lgoto2

$(OBJ): $(INCLUDE)

numbers.exe: $(OBJ) numbers.c
	$(CC) -o  numbers numbers.c $(OBJ) $(LINK)

numbers-c.txt: numbers
	numbers.exe > numbers-c.txt

clean:
	$(RM) *.o

purge:
	$(RM) *.o numbers.exe
