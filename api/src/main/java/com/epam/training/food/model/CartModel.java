package com.epam.training.food.model;

import java.net.URI;
import java.util.Objects;
import com.epam.training.food.model.OrderItemModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CartModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-20T16:29:12.622199800+05:30[Asia/Calcutta]")
public class CartModel {

  @Valid
  private List<@Valid OrderItemModel> orderItemModels = new ArrayList<>();

  private BigDecimal price = new BigDecimal("0");

  public CartModel orderItemModels(List<@Valid OrderItemModel> orderItemModels) {
    this.orderItemModels = orderItemModels;
    return this;
  }

  public CartModel addOrderItemModelsItem(OrderItemModel orderItemModelsItem) {
    if (this.orderItemModels == null) {
      this.orderItemModels = new ArrayList<>();
    }
    this.orderItemModels.add(orderItemModelsItem);
    return this;
  }

  /**
   * Get orderItemModels
   * @return orderItemModels
  */
  @Valid 
  @Schema(name = "orderItemModels", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("orderItemModels")
  public List<@Valid OrderItemModel> getOrderItemModels() {
    return orderItemModels;
  }

  public void setOrderItemModels(List<@Valid OrderItemModel> orderItemModels) {
    this.orderItemModels = orderItemModels;
  }

  public CartModel price(BigDecimal price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @Valid 
  @Schema(name = "price", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartModel cartModel = (CartModel) o;
    return Objects.equals(this.orderItemModels, cartModel.orderItemModels) &&
        Objects.equals(this.price, cartModel.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderItemModels, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartModel {\n");
    sb.append("    orderItemModels: ").append(toIndentedString(orderItemModels)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

