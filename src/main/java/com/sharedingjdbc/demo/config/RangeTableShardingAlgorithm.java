package com.sharedingjdbc.demo.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据当前的月份进行分表查询
 * @author tantao
 */
@Component
public class RangeTableShardingAlgorithm implements RangeShardingAlgorithm<Date> {


    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Long lower = 0L;
        Long upper = Long.MAX_VALUE;
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        if (valueRange.hasLowerBound()) {
            Date lowerEndpoint = valueRange.lowerEndpoint();
            lower = Long.valueOf(simpleDateFormat.format(lowerEndpoint));
        }
        if (valueRange.hasUpperBound()) {
            Date upperEndpoint = valueRange.upperEndpoint();
            upper = Long.valueOf(simpleDateFormat.format(upperEndpoint));
        }
        Collection<String> result = new LinkedHashSet<>(collection.size());
        for (String tablename : collection) {
            Long tableValue = Long.valueOf(tablename.split("_")[1]);
            if (tableValue >= lower && tableValue <= upper) {
                result.add(tablename);
            }
        }
        return result;
    }
}
