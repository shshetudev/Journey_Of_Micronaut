## Micronaut 2.5.5 Documentation

- [User Guide](https://docs.micronaut.io/2.5.5/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.5/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.5/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

### To run the appliction
* In gradle: `./gradlew runFunction`
* In maven: `./mvnw function:run`

### To deploy the function
`gcloud functions deploy hello \
--region europe-west1 \
--trigger-http --allow-unauthenticated \
--runtime java11 --memory 512MB \
--entry-point io.micronaut.gcp.function.http.HttpFunction`