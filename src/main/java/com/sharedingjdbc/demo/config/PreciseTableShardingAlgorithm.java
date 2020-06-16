package com.sharedingjdbc.demo.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

/**
 * 根据当前的月份进行分表查询
 * @author tantao
 */
@Component
public class PreciseTableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        String queryDate = preciseShardingValue.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
        String NowDate = LocalDateTime.now().format(formatter);
        if (queryDate.equals(NowDate)) {
            return preciseShardingValue.getLogicTableName();
        }
        // 基本的表名_年份月份  base_199001
        String targetTable = preciseShardingValue.getLogicTableName() + "_" + queryDate;
        if (availableTargetNames.contains(targetTable)){
            return targetTable;
        }
        throw new UnsupportedOperationException("无效的表名称: " + targetTable);
    }
}
