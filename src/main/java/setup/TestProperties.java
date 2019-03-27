package setup;


import enums.PropertyFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    private String propertyFileName;

    private Properties currentProps = new Properties();

    protected void setPropertyFile(PropertyFiles propertyFiles) {
        propertyFileName = propertyFiles.getAppType();
    }

    Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(System.getProperty("user.dir")
                + propertyFileName);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) currentProps = getCurrentProps();
        //"default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
