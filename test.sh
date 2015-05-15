# split src file into java code and test cases
awk '
    /FUNCTION/  { next }
    /TEST/      { stub_test = 1; next }
    /NOTES/     { exit 0 }

    {
        if (!found_name) {
            if ($0 == "") {
                next;
            } else {
                name = $2;
                found_name = 1;
            }
        }

        if (stub_test == 0) {
            print >"_function.txt";
        } else {
            if ($0 == "")
                next;

            print "assert " name "(" $1 ") == " $2 ";" \
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