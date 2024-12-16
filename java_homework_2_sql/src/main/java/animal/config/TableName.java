package animal.config;

import java.util.Properties;

public class TableName {
    public static String TableName;
    public static void setTableName(Properties properties) {
        TableName = properties.getProperty("name");
    }
}
