package com.worktrial.task.repositories.specification;

import com.worktrial.task.enums.CustomerCategory;
import com.worktrial.task.models.Customer;
import com.worktrial.task.models.CustomerAddress_;
import com.worktrial.task.models.Customer_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecification {
    public Specification<Customer> allCustomer() {
        return (root, criteriaQuery, criteriaBuilder) -> root.get(Customer_.id).isNotNull();
    }

    public Specification<Customer> customerByLastName(String lastName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.lastName), lastName);
    }
    public Specification<Customer> customerByFirstName(String firstName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.firstName), firstName);
    }

    public Specification<Customer> customerByEmailAddress(String emailAddress) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.emailAddress), emailAddress);
    }

    public Specification<Customer> customerByCategory(CustomerCategory customerCategory) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.category), customerCategory);
    }
    public Specification<Customer> customerByCity(String city) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.customerAddress).get(CustomerAddress_.city), city);
    }
    public Specification<Customer> customerByState(String state) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.customerAddress).get(CustomerAddress_.state), state);
    }
    public Specification<Customer> customerByCountry(String country) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.customerAddress).get(CustomerAddress_.country), country);
    }
}
