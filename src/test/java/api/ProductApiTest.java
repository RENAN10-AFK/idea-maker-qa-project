package api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductApiTest {

    @Test
    public void createProduct() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Produto QA\", \"price\": 150}")
        .when()
                .post("https://dummyjson.com/products/add")
        .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/productSchema.json"))
                .body("title", equalTo("Produto QA"))
                .body("price", equalTo(150));
    }

    @Test
    public void listProducts() {
        when()
                .get("https://dummyjson.com/products")
        .then()
                .statusCode(200)
                .body("products.size()", greaterThan(0));
    }

    @Test
    public void updateProduct() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Produto Atualizado\"}")
        .when()
                .put("https://dummyjson.com/products/1")
        .then()
                .statusCode(200)
                .body("title", equalTo("Produto Atualizado"));
    }

    @Test
    public void deleteProduct() {
        when()
                .delete("https://dummyjson.com/products/1")
        .then()
                .statusCode(anyOf(is(200), is(204)));
    }
}
