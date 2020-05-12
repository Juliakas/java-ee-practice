package com.github.juliakas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "EMPLOYEES")
@NamedQueries({
        @NamedQuery(name = "Employees.getAll", query = "SELECT e FROM Employee e"),
})
@Getter @Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private long empId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "COMP_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMP_ID")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private Collection<Employee> subordinates;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private long version;
}
