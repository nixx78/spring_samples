package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.config.IllegalViewAccessException;
import lv.nixx.poc.security.model.ViewName;
import lv.nixx.poc.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static lv.nixx.poc.security.model.ViewName.*;

@RestController
public class ViewDataController {

    private static final Logger LOG = LoggerFactory.getLogger(ViewDataController.class);

    @Autowired
    private LoginService loginService;

    private final Map<ViewName, String> viewData = Map.of(
            View1, "View1 Data",
            View2, "View2 Data",
            ViewX, "ViewX Data"
    );

    @GetMapping("/view/data")
    public String getDataForView(@RequestParam ViewName viewName) {
        LOG.info("getDataForView [{}]", viewName);
        return viewData.get(viewName);
    }

    @GetMapping("/view/dataCheckInController/{viewName}")
    public String getDataForViewCheckInController(@PathVariable ViewName viewName) {
        String userName = loginService.getUserName();
        if (!loginService.isViewIsAllowed(viewName)) {
            throw new IllegalViewAccessException(userName, viewName);
        }
        return viewData.get(viewName);
    }

}
