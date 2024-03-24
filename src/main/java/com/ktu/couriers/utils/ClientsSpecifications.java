package com.ktu.couriers.utils;

import com.ktu.couriers.models.Client;
import com.ktu.couriers.models.ClientSearch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientsSpecifications {

    public static Specification<Client> withCriteria(ClientSearch search) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (search.getName() != null && !search.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.getName().toLowerCase() + "%"));
            }
            if (search.getCompany() != null && !search.getCompany().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("company")), "%" + search.getCompany().toLowerCase() + "%"));
            }
            if (search.getAddress() != null && !search.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + search.getAddress().toLowerCase() + "%"));
            }
            if (search.getPhoneNumber() != null && !search.getPhoneNumber().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), search.getPhoneNumber()));
            }
            if (search.getEmail() != null && !search.getEmail().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + search.getEmail().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
