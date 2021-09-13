package lv.nixx.poc.ps;

import lombok.Data;
import lombok.experimental.Accessors;
import lv.nixx.poc.ps.configuration.Permission;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Response {
    private Date timestamp;
    private String propertyFromValue;
    private String propertyFromApplicationConfig;
    private String propertyFromEnvironment;
    private int pageSize;
    private int totalRecordCount;
    private Permission permission;
}
