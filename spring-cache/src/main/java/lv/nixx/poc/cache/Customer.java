package lv.nixx.poc.cache;

        import lombok.Data;
        import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Customer {

    private Long id;
    private String name;

}
