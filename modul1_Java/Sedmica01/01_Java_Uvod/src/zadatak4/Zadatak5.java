package zadatak4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Zadatak5 {

    public static void main(String args[]){
        Function<Employee, String> funcEmpToString0= new Function<Employee, String>() {
            @Override
            public String apply(Employee employee) {
                return employee.getName();
            }
        };

        Function<Employee, String> funcEmpToString1= (Employee e)-> {return e.getName();};

        Function<Employee, String> funcEmpToString2= e-> e.getName();
        Function<Employee, String> funcEmpToString3= Employee::getName;


        List<Employee> employeeList=
                Arrays.asList(new Employee("Tom Jones", 45),
                        new Employee("Harry Major", 25),
                        new Employee("Ethan Hardy", 65),
                        new Employee("Nancy Smith", 15),
                        new Employee("Deborah Sprightly", 29));
        List<String> empNameList=convertEmpListToNamesList(employeeList, funcEmpToString3);
        empNameList.forEach(System.out::println);
    }
    public static List<String> convertEmpListToNamesList(List<Employee> employeeList, Function<Employee, String> funcEmpToString){
        List<String> empNameList=new ArrayList<String>();
        for(Employee emp:employeeList){
            empNameList.add(funcEmpToString.apply(emp));
        }
        return empNameList;
    }

    private static class Employee {
        private String name;
        private int i;

        public Employee(String name, int i) {

            this.name = name;
            this.i = i;
        }

        public String getName() {
            return name;
        }
    }
}
