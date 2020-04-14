package com.github.juliakas.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "COMPANIES")
@NamedQueries({
        @NamedQuery(name = "Companies.getAll", query = "SELECT c FROM Company c"),
        @NamedQuery(name = "Companies.filterByName",
                query = "SELECT c FROM Company c WHERE c.name LIKE CONCAT('%',:name,'%')")
})
@Getter @Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMP_ID")
    private long compId;

    @Column(name = "COMP_REG_NO", nullable = false, unique = true)
    private long compRegNo;

    @Column(name = "NAME", nullable = false, length = 512)
    private String name;

    @OneToMany(mappedBy = "company")
    private Collection<Employee> employees;
}
