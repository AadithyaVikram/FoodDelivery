package com.epam.training.food;

import com.epam.training.food.model.FoodModel;
import com.epam.training.food.model.OrderModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FoodDeliveryRestServiceTest {
    private static final String USERNAME = "Smith";
    private static final String PASSWORD = "SmithSecret";
    private static final String OTHER_USERNAME = "Jane";
    private static final String INVALID_USER = "invalid-user";
    private static final String WRONG_PASSWORD = "wrong-password";

    private static final String ORDERS_PATH = "/api/orders";
    private static final String FOODS_PATH = "/api/foods";

    private static final long ORDER_ID = 1L;
    private static final int NO_OF_FOODS = 5;
    private static final long NOT_EXISTING_ORDER_ID = 1000L;

    private static final String VALID_CART_FILE_NAME = "ValidCartExample.json";
    private static final String EMPTY_CART_FILE_NAME = "EmptyCartExample.json";

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Login should authenticate when user/pwd is valid: " + USERNAME + "/" + PASSWORD)
    public void testLoginShouldAuthenticateWhenValidUserAndPassword() throws Exception {
        MvcResult result = mockMvc
                .perform(formLogin()
                        .user(USERNAME)
                        .password(PASSWORD))
                .andExpect(authenticated()
                        .withUsername(USERNAME))
                .andReturn();
    }

    @Test
    @DisplayName("Login should not authenticate when password is wrong: " + USERNAME + "/" + WRONG_PASSWORD)
    public void testLoginShouldFailWhenValidUserAndIncorrectPassword() throws Exception {
        MvcResult result = mockMvc
                .perform(formLogin()
                        .user(USERNAME)
                        .password(WRONG_PASSWORD))
                .andExpect(unauthenticated())
                .andReturn();
    }

    @Test
    @DisplayName("Login should not authenticate when user is invalid: " + INVALID_USER + "/" + PASSWORD)
    public void testLoginShouldFailWhenInvalidUser() throws Exception {
        MvcResult result = mockMvc
                .perform(formLogin()
                        .user(INVALID_USER)
                        .password(PASSWORD))
                .andExpect(unauthenticated())
                .andReturn();
    }

    @Test
    @DisplayName("get " + FOODS_PATH + " should respond with 401 when not authenticated")
    public void testGetFoodsShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        mockMvc
                .perform(get(FOODS_PATH))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @DisplayName("get " + ORDERS_PATH + " should respond with 401 when not authenticated")
    public void testGetOrdersShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        mockMvc
                .perform(get(ORDERS_PATH))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @DisplayName("post " + ORDERS_PATH + " should respond with 401 when not authenticated")
    public void testPostOrdersShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        mockMvc
                .perform(post(ORDERS_PATH))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @DisplayName("get " + ORDERS_PATH + "/" + ORDER_ID + " should respond with 401 when not authenticated")
    public void testGetOrderByOrderIdShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        mockMvc
                .perform(get(ORDERS_PATH + "/" + ORDER_ID))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("get " + FOODS_PATH + " should respond " + NO_OF_FOODS)
    public void testGetFoodsShouldReturnFoodsCollectionAndSizeCorrect() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(FOODS_PATH))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        FoodModel[] actual = objectMapper.readValue(responseBody, FoodModel[].class);
        Assertions.assertEquals(NO_OF_FOODS, actual.length, "The number of returned foods is incorrect.");
    }

    @Test
    @WithMockUser(username = OTHER_USERNAME)
    @DisplayName("get " + ORDERS_PATH
            + " should respond with empty array when authenticated customer doesn't have any order")
    public void testGetOrdersShouldReturnEmptyArrayWhenAuthenticatedCustomerDoesNotHaveOrder() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ORDERS_PATH))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        OrderModel[] actual = objectMapper.readValue(responseBody, OrderModel[].class);
        Assertions.assertEquals(0, actual.length, OTHER_USERNAME + " should not have any orders.");
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("get " + ORDERS_PATH
            + " should respond array of orders when authenticated customer has")
    public void testGetOrdersShouldReturnOrdersWhenAuthenticatedCustomerHasOrders() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ORDERS_PATH))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        OrderModel[] actual = objectMapper.readValue(responseBody, OrderModel[].class);
        Assertions.assertEquals(2, actual.length, " number of orders owned by " + USERNAME);
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("get " + ORDERS_PATH + "/" + ORDER_ID
            + " should respond the orders when authenticated customer owns the order")
    public void testGetOrderByIdShouldReturnTheOrderWhenAuthenticatedCustomerOwnsIt() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ORDERS_PATH + "/" + ORDER_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        OrderModel order = objectMapper.readValue(responseBody, OrderModel.class);
        Assertions.assertEquals(new BigDecimal("20.00"), order.getPrice(), "order price");
    }

    @Test
    @WithMockUser(username = OTHER_USERNAME)
    @DisplayName("get " + ORDERS_PATH + "/" + ORDER_ID
            + " should return FORBIDDEN if accessed by other than owner customer")
    public void testGetOrderByOrderIdShouldReturnForbiddenWhenOrderIsNotOwnedByAuthenticatedCustomer() throws Exception {
        mockMvc
                .perform(get(ORDERS_PATH + "/" + ORDER_ID))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("get " + ORDERS_PATH + "/" + NOT_EXISTING_ORDER_ID
            + " should return FORBIDDEN if accessed by other than owner customer")
    public void testGetOrderByOrderIdShouldReturnNotFoundWhenOrderDoesNotExists() throws Exception {
        mockMvc
                .perform(get(ORDERS_PATH + "/" + NOT_EXISTING_ORDER_ID))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("post " + ORDERS_PATH + " should respond with 400 when cart is empty")
    void testPostOrderServiceSlashOrdersShouldReturnWithBadRequestWhenCartIsEmpty() throws Exception {
        mockMvc
                .perform(post(ORDERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fromFile(EMPTY_CART_FILE_NAME)))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @WithMockUser(username = USERNAME)
    @DisplayName("post " + ORDERS_PATH
            + " should respond 201 and the created order")
    void testCreateOrderShouldReturnOrderWhenOrderCreated() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(post(ORDERS_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(fromFile(VALID_CART_FILE_NAME)))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        OrderModel actual = objectMapper.readValue(responseBody, OrderModel.class);

        Assertions.assertEquals(new BigDecimal("45"), actual.getPrice(), "order price");
        Assertions.assertEquals(1, actual.getOrderItemModels().size(), "number of order items");
        Assertions.assertEquals(1, actual.getOrderItemModels().get(0).getFoodModel().getId(),
                "food id");
    }

    private byte[] fromFile(String path) throws IOException {
        return new ClassPathResource(path).getInputStream().readAllBytes();
    }
}
