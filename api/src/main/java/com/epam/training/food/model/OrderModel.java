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
 * OrderModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-20T16:29:12.622199800+05:30[Asia/Calcutta]")
public class OrderModel {

  private Long id;

  private BigDecimal price;

  private String timestampCreated;

  @Valid
  private List<@Valid OrderItemModel> orderItemModels;

  public OrderModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderModel price(BigDecimal price) {
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

  public OrderModel timestampCreated(String timestampCreated) {
    this.timestampCreated = timestampCreated;
    return this;
  }

  /**
   * Get timestampCreated
   * @return timestampCreated
  */
  
  @Schema(name = "timestampCreated", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestampCreated")
  public String getTimestampCreated() {
    return timestampCreated;
  }

  public void setTimestampCreated(String timestampCreated) {
    this.timestampCreated = timestampCreated;
  }

  public OrderModel orderItemModels(List<@Valid OrderItemModel> orderItemModels) {
    this.orderItemModels = orderItemModels;
    return this;
  }

  public OrderModel addOrderItemModelsItem(OrderItemModel orderItemModelsItem) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderModel orderModel = (OrderModel) o;
    return Objects.equals(this.id, orderModel.id) &&
        Objects.equals(this.price, orderModel.price) &&
        Objects.equals(this.timestampCreated, orderModel.timestampCreated) &&
        Objects.equals(this.orderItemModels, orderModel.orderItemModels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, timestampCreated, orderItemModels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    timestampCreated: ").append(toIndentedString(timestampCreated)).append("\n");
    sb.append("    orderItemModels: ").append(toIndentedString(orderItemModels)).append("\n");
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

