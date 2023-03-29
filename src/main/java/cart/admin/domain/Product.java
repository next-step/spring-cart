package cart.admin.domain;

public class Product {

    private Integer productId;
    private String productName;
    private String productPath;
    private Integer productPrice;

    public Product(Integer productId, String productName, String productPath, Integer productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPath = productPath;
        this.productPrice = productPrice;
    }

    public Product(String productName, String productPath, Integer productPrice) {
        this.productName = productName;
        this.productPath = productPath;
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPath() {
        return productPath;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
}
