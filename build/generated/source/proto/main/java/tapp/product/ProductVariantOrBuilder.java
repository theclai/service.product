// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

package tapp.product;

public interface ProductVariantOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tapp.product.ProductVariant)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * ID of the form: //product.tapp/ProductVariant/9a0e4932-44be-11eb-b378-0242ac130002
   * </pre>
   *
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <pre>
   * ID of the form: //product.tapp/ProductVariant/9a0e4932-44be-11eb-b378-0242ac130002
   * </pre>
   *
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>.google.protobuf.Timestamp transaction_time = 2;</code>
   * @return Whether the transactionTime field is set.
   */
  boolean hasTransactionTime();
  /**
   * <code>.google.protobuf.Timestamp transaction_time = 2;</code>
   * @return The transactionTime.
   */
  com.google.protobuf.Timestamp getTransactionTime();
  /**
   * <code>.google.protobuf.Timestamp transaction_time = 2;</code>
   */
  com.google.protobuf.TimestampOrBuilder getTransactionTimeOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp valid_time = 3;</code>
   * @return Whether the validTime field is set.
   */
  boolean hasValidTime();
  /**
   * <code>.google.protobuf.Timestamp valid_time = 3;</code>
   * @return The validTime.
   */
  com.google.protobuf.Timestamp getValidTime();
  /**
   * <code>.google.protobuf.Timestamp valid_time = 3;</code>
   */
  com.google.protobuf.TimestampOrBuilder getValidTimeOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp created_time = 4;</code>
   * @return Whether the createdTime field is set.
   */
  boolean hasCreatedTime();
  /**
   * <code>.google.protobuf.Timestamp created_time = 4;</code>
   * @return The createdTime.
   */
  com.google.protobuf.Timestamp getCreatedTime();
  /**
   * <code>.google.protobuf.Timestamp created_time = 4;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCreatedTimeOrBuilder();

  /**
   * <code>string title = 5;</code>
   * @return The title.
   */
  java.lang.String getTitle();
  /**
   * <code>string title = 5;</code>
   * @return The bytes for title.
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <code>string subtitle = 6;</code>
   * @return The subtitle.
   */
  java.lang.String getSubtitle();
  /**
   * <code>string subtitle = 6;</code>
   * @return The bytes for subtitle.
   */
  com.google.protobuf.ByteString
      getSubtitleBytes();

  /**
   * <code>string description = 7;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>string description = 7;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>.tapp.product.ProductVariant.Form form = 8;</code>
   * @return The enum numeric value on the wire for form.
   */
  int getFormValue();
  /**
   * <code>.tapp.product.ProductVariant.Form form = 8;</code>
   * @return The form.
   */
  tapp.product.ProductVariant.Form getForm();

  /**
   * <code>string sku = 10;</code>
   * @return The sku.
   */
  java.lang.String getSku();
  /**
   * <code>string sku = 10;</code>
   * @return The bytes for sku.
   */
  com.google.protobuf.ByteString
      getSkuBytes();

  /**
   * <code>.google.type.Money price = 11;</code>
   * @return Whether the price field is set.
   */
  boolean hasPrice();
  /**
   * <code>.google.type.Money price = 11;</code>
   * @return The price.
   */
  com.google.type.Money getPrice();
  /**
   * <code>.google.type.Money price = 11;</code>
   */
  com.google.type.MoneyOrBuilder getPriceOrBuilder();

  /**
   * <pre>
   * Image IDs
   * </pre>
   *
   * <code>repeated string images = 12;</code>
   * @return A list containing the images.
   */
  java.util.List<java.lang.String>
      getImagesList();
  /**
   * <pre>
   * Image IDs
   * </pre>
   *
   * <code>repeated string images = 12;</code>
   * @return The count of images.
   */
  int getImagesCount();
  /**
   * <pre>
   * Image IDs
   * </pre>
   *
   * <code>repeated string images = 12;</code>
   * @param index The index of the element to return.
   * @return The images at the given index.
   */
  java.lang.String getImages(int index);
  /**
   * <pre>
   * Image IDs
   * </pre>
   *
   * <code>repeated string images = 12;</code>
   * @param index The index of the value to return.
   * @return The bytes of the images at the given index.
   */
  com.google.protobuf.ByteString
      getImagesBytes(int index);

  /**
   * <pre>
   * Product ID
   * </pre>
   *
   * <code>string product = 13;</code>
   * @return The product.
   */
  java.lang.String getProduct();
  /**
   * <pre>
   * Product ID
   * </pre>
   *
   * <code>string product = 13;</code>
   * @return The bytes for product.
   */
  com.google.protobuf.ByteString
      getProductBytes();

  /**
   * <pre>
   * Stock quantity
   * </pre>
   *
   * <code>int32 quantity = 14;</code>
   * @return The quantity.
   */
  int getQuantity();

  /**
   * <pre>
   * Ordered key value properties of the product
   * </pre>
   *
   * <code>repeated .tapp.product.ProductVariant.Property properties = 15;</code>
   */
  java.util.List<tapp.product.ProductVariant.Property> 
      getPropertiesList();
  /**
   * <pre>
   * Ordered key value properties of the product
   * </pre>
   *
   * <code>repeated .tapp.product.ProductVariant.Property properties = 15;</code>
   */
  tapp.product.ProductVariant.Property getProperties(int index);
  /**
   * <pre>
   * Ordered key value properties of the product
   * </pre>
   *
   * <code>repeated .tapp.product.ProductVariant.Property properties = 15;</code>
   */
  int getPropertiesCount();
  /**
   * <pre>
   * Ordered key value properties of the product
   * </pre>
   *
   * <code>repeated .tapp.product.ProductVariant.Property properties = 15;</code>
   */
  java.util.List<? extends tapp.product.ProductVariant.PropertyOrBuilder> 
      getPropertiesOrBuilderList();
  /**
   * <pre>
   * Ordered key value properties of the product
   * </pre>
   *
   * <code>repeated .tapp.product.ProductVariant.Property properties = 15;</code>
   */
  tapp.product.ProductVariant.PropertyOrBuilder getPropertiesOrBuilder(
      int index);

  /**
   * <pre>
   * Options that differentiate this variant SKU from another
   * i.e. colour, size, design
   * </pre>
   *
   * <code>map&lt;string, string&gt; options = 16;</code>
   */
  int getOptionsCount();
  /**
   * <pre>
   * Options that differentiate this variant SKU from another
   * i.e. colour, size, design
   * </pre>
   *
   * <code>map&lt;string, string&gt; options = 16;</code>
   */
  boolean containsOptions(
      java.lang.String key);
  /**
   * Use {@link #getOptionsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getOptions();
  /**
   * <pre>
   * Options that differentiate this variant SKU from another
   * i.e. colour, size, design
   * </pre>
   *
   * <code>map&lt;string, string&gt; options = 16;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getOptionsMap();
  /**
   * <pre>
   * Options that differentiate this variant SKU from another
   * i.e. colour, size, design
   * </pre>
   *
   * <code>map&lt;string, string&gt; options = 16;</code>
   */

  java.lang.String getOptionsOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <pre>
   * Options that differentiate this variant SKU from another
   * i.e. colour, size, design
   * </pre>
   *
   * <code>map&lt;string, string&gt; options = 16;</code>
   */

  java.lang.String getOptionsOrThrow(
      java.lang.String key);

  /**
   * <code>.tapp.product.Measurements package_measurements = 17;</code>
   * @return Whether the packageMeasurements field is set.
   */
  boolean hasPackageMeasurements();
  /**
   * <code>.tapp.product.Measurements package_measurements = 17;</code>
   * @return The packageMeasurements.
   */
  tapp.product.Measurements getPackageMeasurements();
  /**
   * <code>.tapp.product.Measurements package_measurements = 17;</code>
   */
  tapp.product.MeasurementsOrBuilder getPackageMeasurementsOrBuilder();
}
