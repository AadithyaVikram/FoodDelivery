package com.epam.training.food.model;

import java.net.URI;
import java.util.Objects;
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
 * FoodModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-20T16:29:12.622199800+05:30[Asia/Calcutta]")
public class FoodModel {

  private Long id;

  private String name;

  private BigDecimal calorie;

  private String description;

  private BigDecimal price;

  public FoodModel() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FoodModel(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public FoodModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public FoodModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FoodModel calorie(BigDecimal calorie) {
    this.calorie = calorie;
    return this;
  }

  /**
   * Get calorie
   * @return calorie
  */
  @Valid 
  @Schema(name = "calorie", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calorie")
  public BigDecimal getCalorie() {
    return calorie;
  }

  public void setCalorie(BigDecimal calorie) {
    this.calorie = calorie;
  }

  public FoodModel description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public FoodModel price(BigDecimal price) {
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
    FoodModel foodModel = (FoodModel) o;
    return Objects.equals(this.id, foodModel.id) &&
        Objects.equals(this.name, foodModel.name) &&
        Objects.equals(this.calorie, foodModel.calorie) &&
        Objects.equals(this.description, foodModel.description) &&
        Objects.equals(this.price, foodModel.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, calorie, description, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FoodModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    calorie: ").append(toIndentedString(calorie)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

