package com.worktrial.task.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customer_address")
public class CustomerAddress implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CustomerAddress that = (CustomerAddress) o;

        return new EqualsBuilder().append(id, that.id).append(street, that.street).append(houseNumber, that.houseNumber).append(state, that.state).append(country, that.country).append(city, that.city).append(zipCode, that.zipCode).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(street).append(houseNumber).append(state).append(country).append(city).append(zipCode).toHashCode();
    }
}
