public class Task1 {
    public static void main(String[] args) {
        boolean op1 = false; // op1 will be initialized as a boolean with the value false
        boolean op2 = false; // op2 will be initialized as a boolean with the value false

        System.out.println(" &&   | " + op2 + " | " + !op2); // print "&&   | false | true"
        System.out.println("------|-------|------"); // print "------|-------|------"
        System.out.println(op1 + " | " + (op1 && op2) + " | " + (op1 && !op2)); // print "false | false | false"
        op1 = !op1; // revert the value of op1
        System.out.println(op1 + "  | " + (op1 && op2) + " | " + (op1 && !op2)); // print "true  | false | true"

        // task 1.1
        op1 = false; // op1 will be set to false
        op2 = false; // op2 will be set to false
        System.out.println(" ||   | " + op2 + " | " + !op2); // print "&&   | false | true"
        System.out.println("------|-------|------"); // print "------|-------|------
        System.out.println(op1 + " | " + (op1 || op2) + " | " + (op1 || !op2)); // print "false | false | true"
        op2 = !op2; // revert the value of op1
        System.out.println(op1 + " | " + (op1 || op2) + "  | " + (op1 || !op2)); // print "false  | true | false"
        System.out.println("======|======|======"); // print "======|======|======


        op1 = false; // op1 will be set to false
        op2 = false; // op2 will be set to false
        System.out.println(" |    | " + op2 + " | " + !op2); // print "&&   | false | true"
        System.out.println("------|-------|------"); // print "------|-------|------"
        System.out.println(op1 + " | " + (op1 | op2) + " | " + (op1 | !op2)); // print "false | false | true"
        op2 = !op2; // revert the value of op1
        System.out.println(op1 + " | " + (op1 | op2) + " | " + (op1 | !op2)); // print "false  | true | false"
        System.out.println("======|======|======"); // print "======|======|======

        op1 = false; // op1 will be set to false
        op2 = false; // op2 will be set to false
        System.out.println(" &    | " + op2 + " | " + !op2); // print "&&   | false | true"
        System.out.println("------|-------|------"); // print "------|-------|------"
        System.out.println(op1 + " | " + (op1 & op2) + " | " + (op1 & !op2)); // print "false | false | false"
        op1 = !op1; // revert the value of op1
        System.out.println(op1 + " | " + (op1 & op2) + " | " + (op1 & !op2)); // print "true  | false | true"
        System.out.println("======|======|======"); // print "======|======|======

        op1 = false; // op1 will be set to false
        op2 = false; // op2 will be set to false
        System.out.println(" ^    | " + op2 + " | " + !op2); // print "&&   | false | true"
        System.out.println("------|-------|------"); // print "------|-------|------"
        System.out.println(op1 + " | " + (op1 ^ op2) + " | " + (op1 ^ !op2)); // print "false | false | true"
        op1 = !op1; // revert the value of op1
        System.out.println(op1 + " | " + (op1 ^ op2) + " | " + (op1 ^ !op2)); // print "true  | true | false"
        System.out.println("======|======|======"); // print "======|======|======
    }
}
