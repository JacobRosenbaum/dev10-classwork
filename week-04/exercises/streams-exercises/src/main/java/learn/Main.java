package learn;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();
        // 0. Print all students
        // iteration solution
//        for (Student student : students) {
//            System.out.println(student);
//        }
//        // stream solution
//        students.stream().forEach(System.out::println);
        // 1. Print students from Argentina
//        students.stream()
//                .filter(student -> student.getCountry().equals("Argentina"))
//                .forEach(System.out::println);

        // 2. Print students whose last names starts with 'T'.
//        students.stream()
//                .filter(student -> student.getLastName().charAt(0) == 'T')
//                .forEach(System.out::println);

        // 3. Print students from Argentina, ordered by GPA
//        students.stream()
//                .sorted(Comparator.comparing(Student::getGpa).reversed())
//                .forEach(System.out::println);

        // 4. Print the bottom 10% (100 students) ranked by GPA.
//        students.stream()
//                .sorted(Comparator.comparing(Student::getGpa))
//                .limit(100)
//                .forEach(System.out::println);

        // 5. Print the 4th - 6th ranked students by GPA from Argentina
//        students.stream()
//                .filter(student -> student.getCountry().equals("Argentina"))
//                .sorted(Comparator.comparing(Student::getGpa).reversed())
//                .skip(3)
//                .limit(3)
//                .forEach(System.out::println);

        // 6. Is anyone from Maldives?
//        System.out.println(students.stream()
//                .filter(student -> student.getCountry().equals("Maldives"))
//                .count());

        // 7. Does everyone have a non-null, non-empty email address?
//        boolean allStudentsHaveEmailAddresses = students.stream()
//              .allMatch(student -> !student.getEmailAddress().isEmpty() && !student.getEmailAddress().isBlank());
//
//        System.out.println("All students have email addresses: " + allStudentsHaveEmailAddresses);


        // 8. Print students who are currently registered for 5 courses.
//        students.stream()
//                .filter(student -> student.getRegistrations().size() == 5)
//                .forEach(System.out::println);


        // 9. Print students who are registered for the course "Literary Genres".
//        students.stream()
//                .filter(s -> s.getRegistrations().stream()
//                .anyMatch(r -> r.getCourse().equals("Literary Genres")))
//                .forEach(System.out::println);


        // 10. Who has the latest birthday? Who is the youngest?
//        System.out.println(
//                students.stream()
//                .max(Comparator.comparing(Student::getBirthDate))
//        );

        // 11. Who has the highest GPA? There may be a tie.
//        BigDecimal maxGpa = students.stream()
//                .map(i -> i.getGpa())
//                .sorted(Comparator.reverseOrder())
//                .findFirst()
//                .orElse(BigDecimal.ZERO);
//
//        students.stream()
//                .filter(j -> j.getGpa().equals(maxGpa))
//                .forEach(System.out::println);


        // 12. Print every course students are registered for, including repeats.
//        students.stream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .filter(registration -> !registration.getCourse().isEmpty())
//                .forEach(System.out::println);

        // 13. Print a distinct list of courses students are registered for.
        //HELP
//        students.stream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .filter(registration -> !registration.getCourse().isEmpty())
//                .distinct()
//                .forEach(System.out::println);
        // 14. Print a distinct list of courses students are registered for, ordered by name.
        //HELP
//        students.stream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .filter(registration -> !registration.getCourse().isEmpty())
//                .distinct()
//                .sorted(Comparator.comparing(Registration::getCourse))
//                .forEach(System.out::println);

        // 15. Count students per country.
//        Map<String, Long> studentsByCountry = students.stream()
//                .collect(Collectors.groupingBy(Student::getCountry,
//                        Collectors.counting()));
//
//        for (String country : studentsByCountry.keySet()) {
//            System.out.println(country + ": " + studentsByCountry.get(country));
//        }

        // 16. Count students per country. Order by most to fewest students.

//       students.stream()
//                .collect(Collectors.groupingBy(Student::getCountry,
//                        Collectors.counting()))
//                .entrySet().stream()
//               //reversed sorting
//                .sorted((es1, es2) -> es2.getValue().compareTo(es1.getValue()))
//                .forEach(System.out::println);

        // 17. Count registrations per course.
//        Map<String, Long> registrationsPerCourse = students.stream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .collect(Collectors.groupingBy(Registration::getCourse,
//                        Collectors.counting()));
//
//        for (String registration : registrationsPerCourse.keySet()) {
//            System.out.println(registration + ": " + registrationsPerCourse.get(registration));
//        }

        // 18. How many registrations are not graded (GradeType == AUDIT)?
//        System.out.println(students.stream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .filter(registration -> registration.getGradType().equals(GradeType.AUDIT))
//                .count()
//        );

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).

        //Why doesn't this work...?
//        students.stream()
//                .map(student -> new StudentSummary(student.getCountry(), student.getMajor(), student.getIq()))
//                .sorted(Comparator.comparingDouble(StudentSummary::getIq).reversed())
//                .limit(100)
//                .forEach(System.out::println);

        // 20. What is the average GPA per country (remember, it's random fictional data).
//          HELP
//        Map<String, IntSummaryStatistics> average = students.stream()
//                .collect(Collectors.groupingBy(Student::getCountry,
//                        Collectors.averagingInt(student -> student.getGpa().intValue())));
//                .sorted(Comparator.comparing(Student::getCountry))
//                .collect(Collectors.summarizingInt(student -> student.getGpa().intValue()))
//                .getAverage();
//
//        for (String a : average.keySet()) {
//            System.out.println(a + ": " + average.get(average));
//        }

        // 21. What is the maximum GPA per country?
        // 22. Print average IQ per Major ordered by IQ ascending.
        // 23. STRETCH GOAL!
        // Who has the highest pointPercent in "Sacred Writing"?
    }
}