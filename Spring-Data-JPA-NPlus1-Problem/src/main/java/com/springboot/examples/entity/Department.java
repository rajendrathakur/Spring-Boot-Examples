package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@NamedEntityGraph(name = Department.Employees_Projects,
        attributeNodes = {
                @NamedAttributeNode(
                        value = "employees",
                        subgraph = "projects-subgraph"
                )
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "projects-subgraph",
                        attributeNodes =
                                {
                                        @NamedAttributeNode("projects")
                                }
                )
        }
)
public class Department {

    public static final String Employees_Projects = "department.employees.projects";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();

}
