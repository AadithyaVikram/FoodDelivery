package com.epam.training.food.model;

import java.net.URI;
import java.util.Objects;
import com.epam.training.food.model.FoodModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * OrderItemModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-20T16:29:12.622199800+05:30[Asia/Calcutta]")
public class OrderItemModel {

  private Long id;

  private FoodModel foodModel;

  private Integer pieces;

  private BigDecimal price;

  public OrderItemModel id(Long id) {
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

  public OrderItemModel foodModel(FoodModel foodModel) {
    this.foodModel = foodModel;
    return this;
  }

  /**
   * Get foodModel
   * @return foodModel
  */
  @Valid 
  @Schema(name = "foodModel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("foodModel")
  public FoodModel getFoodModel() {
    return foodModel;
  }

  public void setFoodModel(FoodModel foodModel) {
    this.foodModel = foodModel;
  }

  public OrderItemModel pieces(Integer pieces) {
    this.pieces = pieces;
    return this;
  }

  /**
   * Get pieces
   * @return pieces
  */
  
  @Schema(name = "pieces", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pieces")
  public Integer getPieces() {
    return pieces;
  }

  public void setPieces(Integer pieces) {
    this.pieces = pieces;
  }

  public OrderItemModel price(BigDecimal price) {
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
    OrderItemModel orderItemModel = (OrderItemModel) o;
    return Objects.equals(this.id, orderItemModel.id) &&
        Objects.equals(this.foodModel, orderItemModel.foodModel) &&
        Objects.equals(this.pieces, orderItemModel.pieces) &&
        Objects.equals(this.price, orderItemModel.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, foodModel, pieces, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderItemModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    foodModel: ").append(toIndentedString(foodModel)).append("\n");
    sb.append("    pieces: ").append(toIndentedString(pieces)).append("\n");
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

