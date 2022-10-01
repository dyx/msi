package com.lhd.msi.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.msi.base.BasePageQuery;
import com.lhd.msi.exception.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lhd
 */
public class CommonConvertUtil {

    /**
     * 分页查询参数转换为mybatis plus分页参数
     * @param query
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Page<T> pageQuery2Page(BasePageQuery query, Class<T> clazz) {

        if (query == null) {
            return null;
        }

        Page<T> page = new Page<>(query.getCurrent(), query.getSize());
        if (StrUtil.isBlank(query.getOrders())) {
            return page;
        }

        String[] orderItems = query.getOrders().split(",");
        if (ArrayUtil.isEmpty(orderItems)) {
            return page;
        }

        Map<String, TableFieldInfo> propNameMap = new HashMap<>(16);
        if (clazz != null) {
            List<TableFieldInfo> tableFieldList = TableInfoHelper.getTableInfo(clazz).getFieldList();
            for (TableFieldInfo tableFieldInfo : tableFieldList) {
                propNameMap.put(tableFieldInfo.getProperty(), tableFieldInfo);
            }
        }

        for (String orderItem : orderItems) {

            String[] props = orderItem.split("\\.");
            if (props.length != 2) {
                throw new BusinessException("排序参数格式正确");
            }

            String propName = props[0];
            String orderRuleName = props[1];
            if (StrUtil.isNotBlank(propName) && StrUtil.isNotBlank(orderRuleName)) {

                String columnName;
                if (propNameMap.size() > 0) {
                    if (propNameMap.get(propName) == null) {
                        throw new BusinessException("排序字段不正确");
                    }
                    columnName = propNameMap.get(propName).getColumn();
                } else {
                    columnName = StrUtil.toUnderlineCase(propName);
                }

                orderRuleName = orderRuleName.toLowerCase();
                if (!BasePageQuery.ORDER_RULE_ASC.equals(orderRuleName)
                        && !BasePageQuery.ORDER_RULE_DESC.equals(orderRuleName)) {
                    throw new BusinessException("排序规则不正确");
                }

                page.addOrder(new OrderItem(columnName, BasePageQuery.ORDER_RULE_ASC.equals(orderRuleName)));
            }
        }

        return page;
    }
}
