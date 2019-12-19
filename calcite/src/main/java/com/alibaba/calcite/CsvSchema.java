package com.alibaba.calcite;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;

import java.net.URL;
import java.util.Map;

/**
 * @author sier.pys 2019/12/16
 */
public class CsvSchema extends AbstractSchema {
    private Map<String, Table> tableMap;
    private String dataFile;

    public CsvSchema(String dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    protected Map<String, Table> getTableMap() {
        URL url = Resources.getResource(dataFile);
        Source source = Sources.of(url);
        if (tableMap == null) {
            final ImmutableMap.Builder<String, Table> builder = ImmutableMap.builder();
            builder.put(this.dataFile.split("\\.")[0], new CsvTable(source));
            tableMap = builder.build();
        }
        return tableMap;
    }
}
