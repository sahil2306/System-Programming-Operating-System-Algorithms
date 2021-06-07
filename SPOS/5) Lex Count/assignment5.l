%{
    #include <stdio.h>
    FILE* yyin;
    int nlines,nwords,nchars;
%}

%%

\n {nlines++;nchars--;}
[^ \n\t]+ {nwords++, nchars=nchars+yyleng;}
. {nchars++;}

%%


int yywrap() {
    return 1;
}

int main(int argc,char* argv[]) {
    yyin = fopen(argv[1],"r");
    yylex();

    printf("Lines = %d\nChars = %d\nWords = %d",nlines,nchars,nwords);

    fclose(yyin);

    return 0;
}
