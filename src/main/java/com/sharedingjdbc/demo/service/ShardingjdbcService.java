package com.sharedingjdbc.demo.service;
import java.util.Date;
import com.sharedingjdbc.demo.repository.entity.Shardingjdbc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Shardingjdbc)表服务接口
 *
 * @author makejava
 * @since 2020-06-12 09:03:42
 */
public interface ShardingjdbcService {
    Shardingjdbc queryById(Integer id);
    
    Shardingjdbc save(Shardingjdbc shardingjdbc);
    Shardingjdbc update(Shardingjdbc shardingjdbc);

    void deleteById(Integer id);
    
    Page<Shardingjdbc> page(Pageable pageable);
    
    boolean deleteByIds(List<Integer> ids);
}