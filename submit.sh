#!/bin/bash
# split src file into java code and test cases
awk '
    BEGIN       { f = 1 }
    NR == 1     {
        rettype = $1;
        name = $2;

        if (rettype == "boolean")
            comp = " == ";
        else if (rettype == "String")
            comp = ".equals";
    }
    /HISTORY/   { f = 0 }
    /TESTS/     { f = 0; t = 1; FS = "|"; next }
    /NOTES/     { exit 0 }

    {
        if (f)
            print >"_function.txt";

        if (t) {
            if ($0 == "")
                next;

            print "System.err.println(" $1 "+\" -> \"+" name "(" $1 ")+\" | \" +" $2 ");" \
                >"_tests.txt";
            print "assert " name "(" $1 ")" comp "(" $2 ");\n" \
                >"_tests.txt";
        }
    }
' <$1

# process test cases
# awk '
#     {
#         print "assert " $1 " == "
#     }
# ' <"_tests_tmp.txt"

awk '
    /function/ {
        printf("\tpublic static ");
        while (getline x <"_function.txt" > 0) {
                printf("%s\n\t", x);
        }
        next;
    }

    /main/ {
        print;
        while (getline y <"_tests.txt" > 0) {
            print "\t\t" y;
        }
        next;
    }
    { print }

' _framework.java > Run.java

rm -f _report.txt
javac Run.java
java -ea Run 2> _report.txt

if [[ -n $(grep Exception _report.txt) ]]; then
    cat _report.txt
    open Run.java
fi