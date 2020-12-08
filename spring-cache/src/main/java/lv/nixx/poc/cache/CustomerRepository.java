package lv.nixx.poc.cache;

public interface CustomerRepository {
    Customer getById(Long id);
    void clearCache();
}
