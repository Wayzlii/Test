package animal.config;

import java.util.Properties;

public class TableName {
    public static String tableName;
    public static void setTableName(Properties properties) {
        tableName = properties.getProperty("name");
    }
}
