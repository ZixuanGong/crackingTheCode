#!/bin/bash
# split src file into java code and test cases
awk '
    BEGIN       { f = 1 }
    NR == 1     { name = $2 }
    /HISTORY/   { f = 0 }
    /TEST/      { f = 0; t = 1; next }
    /NOTES/     { exit 0 }

    {
        if (f)
            print >"_function.txt";

        if (t) {
            if ($0 == "")
                next;

            rhs = $NF;
            $NF = "";
            print "assert " name "(" $0 ") == " rhs ";" \
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

javac Run.java
java -ea Run