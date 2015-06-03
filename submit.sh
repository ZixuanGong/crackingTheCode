#!/bin/bash
# split src file into java code and test cases
awk '
    BEGIN       { f = 1; FS = "[( ]" }
    /TODO/      { print "TODO: " FILENAME; exit 0 }
    NR == 1     {
        if (f) {
            rettype = $1;
            name = $2;

            if (rettype == "boolean")
                comp = " == ";
            else if (rettype == "String")
                comp = ".equals";

            FS = " ";
        }
    }
    /HISTORY/   { f = 0 }
    /manual/    { m = 1 }
    /TESTS/     { f = 0; t = 1; FS = "|"; next }
    /NOTES/     { exit 0 }

    {
        if (f)
            print >"_function.txt";

        if (t) {
            if ($0 == "")
                next;

            if (!m) {
                funccall = name "(" $1 ")";
                expected = $2;

                gsub(/\"/, "\\\"");
                printable = $1;
                print "System.err.println(\"" printable "\"+\" -> \"+" funccall "+\" | \" +" expected ");" \
                    >"_tests.txt";
                print "assert " funccall comp "(" expected ");\n" \
                    >"_tests.txt";
            } else {
                print >"_tests.txt";
            }
        }
    }
' $1

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

>_report.txt
javac Node.java H.java
javac Run.java
java -ea Run >> _report.txt 2>&1

if [[ -n $(grep Exception _report.txt) ]]; then
    cat _report.txt
    open Run.java
fi