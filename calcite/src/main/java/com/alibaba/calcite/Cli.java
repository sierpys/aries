package com.alibaba.calcite;

import com.google.common.collect.Lists;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.config.Lex;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;
import org.apache.calcite.util.Pair;

import java.util.List;

/**
 * @author sier.pys 2019/12/17
 */
public class Cli {


    public static void main(String[] args) throws Exception {

        final SqlParser.Config PARSER_CONFIG = SqlParser.configBuilder()
                .setQuoting(Quoting.BACK_TICK)
                .setQuotedCasing(Casing.UNCHANGED)
                .setUnquotedCasing(Casing.UNCHANGED)
                .setIdentifierMaxLength(256)
                .setCaseSensitive(false)
                .setLex(Lex.JAVA)
                .build();

        final SchemaPlus ROOT_SCHEMA = Frameworks.createRootSchema(true);
        ROOT_SCHEMA.add("odps_source", new AbstractTable() {
            @Override
            public RelDataType getRowType(RelDataTypeFactory relDataTypeFactory) {
                JavaTypeFactory typeFactory = (JavaTypeFactory) relDataTypeFactory;
                List<String> names = Lists.newLinkedList();
                List<RelDataType> types = Lists.newLinkedList();
                names.add("cid");
                names.add("rt");
                types.add(typeFactory.createSqlType(SqlTypeName.get("VARCHAR")));
                types.add(typeFactory.createSqlType(SqlTypeName.get("DOUBLE")));
                return typeFactory.createStructType(Pair.zip(names, types));
            }
        });
        String sql = "CREATE TABLE odps_source (\n" +
                "    cid varchar,\n" +
                "    rt double,\n" +
                ") with (\n" +
                "    type = 'odps', \n" +
                "    endPoint = 'your_end_point_name', \n" +
                "    project = 'your_project_name',\n" +
                "    tableName = 'your_table_name',\n" +
                "    accessId = 'xxxx',\n" +
                "    accessKey = 'xxxx',\n" +
                "    partition = 'ds=20190712'\n" +
                ");";

        final FrameworkConfig FRAMEWORK_CONFIG = Frameworks
                .newConfigBuilder()
                .defaultSchema(ROOT_SCHEMA)
                .parserConfig(PARSER_CONFIG)
                .typeSystem(RelDataTypeSystem.DEFAULT)
                .build();

        Planner planner = Frameworks.getPlanner(FRAMEWORK_CONFIG);
        SqlNode sqlNode = planner.parse(sql);
        planner.validate(sqlNode);
    }
}
