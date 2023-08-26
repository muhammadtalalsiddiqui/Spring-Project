package com.dataproviderservice.Entity;

import com.dataproviderservice.DTO.EmployeeRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "employee")
public class Employee  implements UserDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")

private int id;
    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable (name="employee_role", joinColumns =@JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<role> roles=new ArrayList<>();


    public Employee() {
    }

    public Employee(int id, String name, String contact, boolean enabled,String emailAddress) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.enabled = enabled;
        this.emailAddress=emailAddress;
    }

    public Employee(EmployeeRequestDTO employeeRequestDTO, String password, Department dept,List<role> roles) {
        this.id = employeeRequestDTO.getId();
        this.name = employeeRequestDTO.getName();
        this.contact = employeeRequestDTO.getContact();
        this.enabled = employeeRequestDTO.isEnabled();
        this.emailAddress = employeeRequestDTO.getEmailAddress();
        this.password =password;
        this.department = dept;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        roles.stream().forEach(r->{
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(r.getName());
            list.add(simpleGrantedAuthority);
        });
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }




}
