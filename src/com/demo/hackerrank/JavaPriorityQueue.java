package com.demo.hackerrank;

import java.util.*;

class Student2{
    private int id;
    private String name;
    private double CGPA;

    Student2(String name, double CGPA, int id){
        this.name = name;
        this.CGPA = CGPA;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getCGPA() {
        return CGPA;
    }
}
class Priorities{
    private List<Student2> lstStudentQueue = new ArrayList<>();
    public List<Student2> getStudents(List<String> events){
        for(String e : events){
            String [] arrEvent = e.split(" ");
            if(arrEvent[0].equals("ENTER")){
                String name = arrEvent[1];
                double cgpa = Double.parseDouble(arrEvent[2]);
                int id = Integer.parseInt(arrEvent[3]);
                Student2 s = new Student2(name, cgpa, id);
                lstStudentQueue.add(s);
                lstStudentQueue.sort(new Comparator<Student2>() {
                    @Override
                    public int compare(Student2 o1, Student2 o2) {
                       if(o1.getCGPA() > o2.getCGPA()){
                           return -1;
                       }
                       else if (o1.getCGPA() < o2.getCGPA()){
                           return 1;
                       }
                       else{
                           if(!o1.getName().equals(o2.getName())){
                               return  o1.getName().compareTo(o2.getName());
                           }
                           else{
                               return o1.getId() - o2.getId();
                           }
                       }
                    }
                });
            }
            else if (arrEvent[0].equals("SERVED")){
                if (lstStudentQueue.size() > 0) {
                    lstStudentQueue.remove(0);
                }
            }
        }
        return  lstStudentQueue;
    }
}
public class JavaPriorityQueue {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student2> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student2 st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
