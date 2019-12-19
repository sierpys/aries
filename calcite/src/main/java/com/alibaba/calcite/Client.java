package com.alibaba.calcite;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;

import java.net.URL;
import java.net.URLDecoder;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.apache.calcite.sql.parser.SqlParser.configBuilder;

/**
 * @author sier.pys 2019/12/16
 */
public class Client {

    public static void main(String[] args) throws Exception {
        // 用文件的方式
        URL url = Client.class.getResource("/model.json");
        String str = URLDecoder.decode(url.toString(), "UTF-8");
        Properties info = new Properties();
        info.put("model", str.replace("file:", ""));
        System.out.println(info);
        Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
        Statement statement = connection.createStatement();
        test1(statement);


        SchemaPlus schemaPlus = Frameworks.createRootSchema(true);

        //给schema T中添加表
        Frameworks.ConfigBuilder configBuilder = Frameworks.newConfigBuilder();
        //设置默认schema
        configBuilder.defaultSchema(schemaPlus);

        FrameworkConfig frameworkConfig = configBuilder.build();

        SqlParser.ConfigBuilder paresrConfig = configBuilder(frameworkConfig.getParserConfig());
        //SQL 大小写不敏感
        paresrConfig.setCaseSensitive(false).setConfig(paresrConfig.build());
        Planner planner = Frameworks.getPlanner(frameworkConfig);

    }


    public static void test1(Statement statement) throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from test_csv.TEST01");
        System.out.println(JSON.toJSONString(getData(resultSet)));
    }

    public static List<Map<String,Object>> getData(ResultSet resultSet)throws Exception{
        List<Map<String,Object>> list = Lists.newArrayList();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnSize = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> map = Maps.newLinkedHashMap();
            for (int i = 1; i < columnSize + 1; i++) {
                map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }
            list.add(map);
        }
        return list;
    }
}
