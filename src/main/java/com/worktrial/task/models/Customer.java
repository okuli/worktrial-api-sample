package com.worktrial.task.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.worktrial.task.enums.CustomerCategory;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birthdate")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthdate;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CustomerCategory category;

    @Column(name = "email_address")
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "customer_address_id", referencedColumnName = "id")
    private CustomerAddress customerAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder().append(id, customer.id)
                .append(lastName, customer.lastName).append(firstName, customer.firstName)
                .append(birthdate, customer.birthdate).append(jobTitle, customer.jobTitle)
                .append(category, customer.category).append(emailAddress, customer.emailAddress)
                .append(customerAddress, customer.customerAddress).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(lastName)
                .append(firstName).append(birthdate).append(jobTitle).append(category)
                .append(emailAddress).append(customerAddress).toHashCode();
    }
}
