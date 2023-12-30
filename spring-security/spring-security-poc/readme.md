Login page:  `http://localhost:8080/login_page`

## Примеры управления доступом

### На уровне метода - ServiceWithMethodLevelPermissions
https://en.wikipedia.org/wiki/JSR_250

##Filter samples

### PaymentRequestFilter - фильтр по содержимому запроса
### ViewNameFilter - фильтр по параметру запроса

# Тестирование REST endpoints

### Тестирование при помощи MockMvc
* PaymentControllerTest
* ViewDataControllerTest
* SampleControllerTest
* MethodLevelPermissionsControllerTest

### Тестирование по помощи RestTemplate
* ControllerTest

### Получение username в Handler
* EndpointWithHandlerController
