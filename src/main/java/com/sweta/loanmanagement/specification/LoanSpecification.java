package com.sweta.loanmanagement.specification;

import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.enums.LoanStatus;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanSpecification {
    public static Specification<Loan> search(
            LoanStatus status,
            Double amount,
            Integer tenureMonths,
            String fullName,
            LocalDate startDate


    ) {
        return (root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if(amount!=null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("amount"),amount));
            }
            if(tenureMonths!=null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("tenureMonths"),tenureMonths));
            }

            if(fullName!=null && !fullName.isBlank()){
                Join<Loan, Customer>customerJoin=root.join("customer");
                predicates.add(
                        cb.like(cb.lower(customerJoin.get("fullName")),
                                "%"+fullName.toLowerCase()+"%")
                );
            }
            if(startDate!=null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"),startDate));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
