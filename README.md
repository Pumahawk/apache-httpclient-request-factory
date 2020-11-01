# ApacheClientHttpRequestFactory

New implementation of ClientHttpRequestFactory usign HttpClient of Apache library.

* [HttpClient Apache](https://hc.apache.org/)
* [Spring web library](https://mvnrepository.com/artifact/org.springframework/spring-web)


## Exemple

```java
RestTemplate rest = new RestTemplate();
rest.setRequestFactory(new ApacheClientHttpRequestFactory());
ResponseEntity<String> response = rest.exchange("https://www.google.com", HttpMethod.GET, null, String.class);
```
