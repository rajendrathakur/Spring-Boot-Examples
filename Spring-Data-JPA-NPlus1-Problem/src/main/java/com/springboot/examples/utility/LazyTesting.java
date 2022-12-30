package com.springboot.examples.utility;

import com.springboot.examples.entity.Department;
import com.springboot.examples.entity.Employee;
import com.springboot.examples.entity.Manager;
import com.springboot.examples.entity.Project;
import com.springboot.examples.repository.DepartmentRepository;
import com.springboot.examples.repository.EmployeeRepository;
import com.springboot.examples.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class LazyTesting {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;


    @Transactional
    public void fetchDepartmentRecords() {
        List<Department> departments = departmentRepository.findAll();
        departments.stream().collect(Collectors.toMap(Department::getName, Department::getEmployees))
                .forEach((k, v) -> System.out.println(k + ":" + v.stream().map(Employee::getName).collect(Collectors.toList())));
    }

    @Transactional
    public void findWithJoinFetch() {
        Set<Department> departments = departmentRepository.findWithJoinFetch();
        departments.stream().collect(Collectors.toMap(Department::getName, Department::getEmployees))
                .forEach((k, v) -> System.out.println(k + ":" + v.stream().map(Employee::getName).collect(Collectors.toList())));
    }

    @Transactional
    public void findWithEntityGraph() {
        List<Department> departments = departmentRepository.findAll();
        departments.stream().collect(Collectors.toMap(Department::getName, Department::getEmployees))
                .forEach((k, v) -> System.out.println(k + ":" + v.stream().map(Employee::getName).collect(Collectors.toList())));
    }

    @Transactional
    public void insertAllRecords() {

        //Create manager recordsS
        Manager krishna = new Manager("Krishna");
        Manager ashok = new Manager("Ashok");
        Manager rajendra = new Manager("Rajendra");

        //create project records
        Project hrProject = new Project("HRProject", krishna);
        Project salesProject = new Project("SalesProject", ashok);
        Project itProject = new Project("ITProject", rajendra);

        //create department records
        Department hr = new Department("HR");
        Department sales = new Department("Sales");
        Department it = new Department("IT");

        //create employee records
        Employee ajay = new Employee("Ajay", 55000, hr);
        Employee ravi = new Employee("Ravi", 27000, hr);

        Employee manisha = new Employee("Manisha", 45000, sales);
        Employee nikitha = new Employee("Nikitha", 28000, sales);

        Employee anil = new Employee("Anil", 15000, it);
        Employee jayanth = new Employee("Jayanth", 36000, it);
        Employee mohan = new Employee("Mohan", 32000, it);

        //Tieing employees to department
        hr.getEmployees().add(ajay);
        hr.getEmployees().add(ravi);

        sales.getEmployees().add(manisha);
        sales.getEmployees().add(nikitha);

        it.getEmployees().add(anil);
        it.getEmployees().add(jayanth);
        it.getEmployees().add(mohan);

        //Tieing projects to employee
        ajay.getProjects().add(hrProject);
        ravi.getProjects().add(hrProject);

        manisha.getProjects().add(salesProject);
        nikitha.getProjects().add(salesProject);

        anil.getProjects().add(itProject);
        jayanth.getProjects().add(itProject);
        mohan.getProjects().add(itProject);

        //save department records
        departmentRepository.save(hr);
        departmentRepository.save(sales);
        departmentRepository.save(it);
    }

    @Transactional
    public void fetchAllRecords() {
        List<Department> departments = departmentRepository.findAll();
        departments.stream().collect(Collectors.toMap(Department::getName, Department::getEmployees))
                .forEach((k, v) -> System.out.println(k + ":" + v.stream()
                        .map(emp -> "Employee Name: " + emp.getName() +
                                 emp.getProjects().stream().map(project -> " Project Name: "+project.getName()
                                 + " Project Manager Name: "+ project.getManager().getName()).findFirst())
                        .collect(Collectors.toList())));
    }

    @Transactional
    public void fetchprojects(){
        List<Project> projects = projectRepository.findAll();
        projects.stream().forEach(p -> p.getManager().getName());
    }

    @Transactional
    public void fetchEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getProjects))
                .forEach((k, v) -> System.out.println(k + ":" + v.stream().map(Project::getName).collect(Collectors.toList())));
    }
}
