package com.alibaba.calcite;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;

/**
 * @author sier.pys 2019/12/16
 */
public class CsvSchemaFactory implements SchemaFactory {

    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> map) {
        System.out.println(s);
        return new CsvSchema(String.valueOf(map.get("dataFile")));
    }
}
