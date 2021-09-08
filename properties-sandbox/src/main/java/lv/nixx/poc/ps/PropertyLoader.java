package lv.nixx.poc.ps;

public class PropertyLoader {

    public static void loadProperties() {

//        System.setProperty("spring.config.additional-location",
//                "C:\\tmp\\configuration\\rest-sandbox\\external.properties");
// также, можно использовать параметр: spring.config.import - он может находится в application.properties

        System.setProperty("spring.application.json", "{\"json.property\":\"json.property.value\"}");
    }
}
