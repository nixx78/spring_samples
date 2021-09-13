package lv.nixx.poc.ps.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "app")
@Validated
@Setter
@Getter
public class ConfigurationPropertiesConfig {

    @Min(1)
    @Max(200)
    private int pageSize;

    @Max(10)
    @NotNull
    private int totalRecordCount;

    @NotNull
    private Permission permission;

    public String customApplicationProperty;


}
