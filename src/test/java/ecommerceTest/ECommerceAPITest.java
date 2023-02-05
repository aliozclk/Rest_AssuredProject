package ecommerceTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("ozclkali@gmail.com");
        loginRequest.setUserPassword("@Li123456");

        RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
        LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        //Add Product
        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName","Laptop")
                .param("productAddedBy",userId).param("productCategory","fashion")
                .param("productSubCategory","shirts").param("productPrice","11500")
                .param("productDescription","Addias Originals").param("productFor","women")
                .multiPart("productImage",new File("C:\\Users\\alioz\\Pictures\\DCIM\\WhatsApp Images\\Private\\IMG-20220812-WA0004.jpg"));

        AddProductResponse addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then()
                .extract().response().as(AddProductResponse.class);

        String productId = addProductResponse.getProductId();


        //Create Order
        RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON).build();

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setCountry("Antarctica");
        ordersItem.setProductOrderedId(productId);

        List<OrdersItem> orderDetailList = new ArrayList<>();
        orderDetailList.add(ordersItem);
        createOrderRequest.setOrders(orderDetailList);


        RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(createOrderRequest);
        String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then()
                .log().all().extract().response().asString();

        System.out.println(responseAddOrder);

        //Delete Product
        RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON).build();

       RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBaseReq).pathParam("productId",productId);

       String deleteProductResp = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then()
               .log().all().extract().response().asString();

       JsonPath jsonDel = new JsonPath(deleteProductResp);
        Assert.assertEquals("Product Deleted Successfully",jsonDel.get("message"));
    }
}
