package lv.nixx.poc.ps.configuration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

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
