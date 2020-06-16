package com.sharedingjdbc.demo.service.impl;

import com.sharedingjdbc.demo.repository.ShardingjdbcRepository;
import com.sharedingjdbc.demo.repository.entity.Shardingjdbc;
import com.sharedingjdbc.demo.service.ShardingjdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShardingjdbcServiceImpl implements ShardingjdbcService {

    @Autowired
    ShardingjdbcRepository shardingjdbcRepository;

    @Override
    public Shardingjdbc queryById(Integer id) {
        shardingjdbcRepository.getOne(id);
        return null;
    }

    @Override
    public Shardingjdbc save(Shardingjdbc shardingjdbc) {
        shardingjdbcRepository.save(shardingjdbc);
        return shardingjdbc;
    }

    @Override
    public Shardingjdbc update(Shardingjdbc shardingjdbc) {
        shardingjdbcRepository.save(shardingjdbc);
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        shardingjdbcRepository.deleteById(id);
    }

    @Override
    public Page<Shardingjdbc> page(Pageable pageable) {
        return null;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        return false;
    }
}
