import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DemoExceptions {

    public static void main(String[] args) throws FileNotFoundException, InvalidGradeException {

        demoRuntimeExceptions();
        demoCheckExceptionsThrows();
        demoCheckExceptionsTryCatch();
        demoOurCheckedExceptionsThrows();
        demoOurCheckedExceptionsTryCatch();
    }

    // === (1) Unchecked exceptions ===
    
    public static void demoRuntimeExceptions() {

        int[] myArray = new int[10];

        myArray[12] = 8;  // A runtime exception could occur.

        // We don't have to catch unchecked exceptions, and we usually
        // don't, but it is allowed.
        // try {
        //     myArray[12] = 8;
        // } catch (ArrayIndexOutOfBoundsException e) {
        //     System.out.println("Program exits without error.");
        // }
    }

    // === (2) Checked exceptions generated by Java ===
    
    public static void demoCheckExceptionsThrows() throws FileNotFoundException {

        // A checked exception: must be thrown or caught.
        // In this method, we declare that it may be thrown.
        Scanner sc = new Scanner(new File("myfile.txt"));
        sc.close();

    }

    public static void demoCheckExceptionsTryCatch() {

        // In this method, we catch it instead.
        Scanner sc;
        try {
            sc = new Scanner(new File("myfile.txt"));
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file. Program exiting.");
        }    
    }

    // === (2) Checked exceptions generated by us === 
    
    public static void demoOurCheckedExceptionsThrows() throws InvalidGradeException {

        Student bob = new Student(new String[] {"Bob"},
                "01012001", "M", "1234");

        bob.addGrade("csc207", 120); 
    }

    public static void demoOurCheckedExceptionsTryCatch() {

        Student bob = new Student(new String[] {"Bob"},
                "01012001", "M", "1234");

        Scanner input = new Scanner(System.in);
        String course, grade;

        System.out.println("Course number: ");
        course = input.next();

        System.out.println("Course grade: ");    
        while (input.hasNext()) {

            // To exit, type Ctrl-D on Mac; Ctrl-Z on Windows.
            grade = input.next();    

            try {

                /**
                 * We'll talk more about regular expressions later this term
                 * This code produces true iff grade is made up of one or
                 * more digits: Pattern.matches("\\d", grade)
                 */

                if (Pattern.matches("\\d+", grade)) {
                    bob.addGrade(course, Integer.parseInt(grade));
                } else {
                    bob.addGrade(course, grade);
                }
            } catch (InvalidGradeException e) {
                System.out.println(e.getMessage());
                System.out.println("Course grade: ");
            }
        }
        System.out.println("Grade entry complete.");        
    }
}

