package com.sharedingjdbc.demo.repository;
import java.util.Date;
import com.sharedingjdbc.demo.repository.entity.Shardingjdbc;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * (Shardingjdbc)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-12 09:03:42
 */
public interface ShardingjdbcRepository extends JpaRepository<Shardingjdbc , Integer>, JpaSpecificationExecutor<Shardingjdbc> {
  
}