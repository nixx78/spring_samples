package lv.nixx.poc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ViewDataController {

    private final Map<String, String> viewData = Map.of(
            "view1", "View1 Data",
            "view2", "View2 Data",
            "viewX", "ViewX Data"
    );

    @GetMapping("/view/data")
    public String getDataForView(@RequestParam String viewName) {
        return viewData.get(viewName);
    }

}
