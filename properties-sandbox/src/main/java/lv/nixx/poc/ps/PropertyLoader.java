package lv.nixx.poc.ps;

public class PropertyLoader {

    public static void loadProperties() {
        System.setProperty("spring.config.additional-location",
                "C:\\tmp\\configuration\\rest-sandbox\\external.properties");
        System.setProperty("spring.application.json", "{\"json.property\":\"json.property.value\"}");
    }
}
