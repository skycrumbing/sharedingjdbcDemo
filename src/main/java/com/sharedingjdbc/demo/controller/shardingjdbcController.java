package com.sharedingjdbc.demo.controller;

import com.sharedingjdbc.demo.repository.ShardingjdbcRepository;
import com.sharedingjdbc.demo.repository.entity.Shardingjdbc;
import com.sharedingjdbc.demo.service.ShardingjdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class shardingjdbcController {

    @Autowired
    ShardingjdbcService shardingjdbcService;

    @Autowired
    ShardingjdbcRepository shardingjdbcRepository;

    @RequestMapping("insert")
    public void insert(@RequestBody Shardingjdbc shardingjdbc){
        shardingjdbcService.save(shardingjdbc);
    }

    @RequestMapping("findList")
    public List<Shardingjdbc> findOne(@RequestBody Shardingjdbc shardingjdbc){
        Specification<Shardingjdbc> specification = (Specification<Shardingjdbc>) (root, criteriaQuery, cb) -> {
            List<javax.persistence.criteria.Predicate> predicatesList = new ArrayList<>();
            if (shardingjdbc.getId() != null) {
                predicatesList.add(cb.equal(root.get("id"), shardingjdbc.getId()));
            }

            if (shardingjdbc.getStartDate() != null) {
                predicatesList.add(cb.greaterThan(root.get("createDate"), shardingjdbc.getStartDate()));
            }
            if (shardingjdbc.getEndDate() != null) {
                predicatesList.add(cb.lessThan(root.get("createDate"), shardingjdbc.getEndDate()));
            }
//            predicatesList.add(cb.between(root.get("createDate"), shardingjdbc.getStartDate(), shardingjdbc.getEndDate()));
            return cb.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        List<Shardingjdbc> all = shardingjdbcRepository.findAll(specification);
        return all;
    }
}
