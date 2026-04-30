class Product{
	private String productName;
	private double price;
	private String category;

	Product(){
		productName="";
		price=0.0;
		category="";
	}

	Product(String productName,double price,String category){
		this.productName=productName;
		this.price=price;
		this.category=category;
	}

	public void setProductName(String productName){
		this.productName=productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setPrice(double price){
		this.price=price;
	}

	public double getPrice(){
		return price;
	}

	public void setCategory(String category){
		this.category=category;
	}

	public String getCategory(){
		return category;
	}

	public void showProductInfo(){
		System.out.println("Product Name: "+productName);
		System.out.println("Price: "+price);
		System.out.println("Category: "+category);
	}
}

class DiscountedProduct extends Product{
	private double discountPercent;
	private int stock;

	DiscountedProduct(){
		super();
		discountPercent=0.0;
		stock=0;
	}

	DiscountedProduct(String productName,double price,String category,double discountPercent,int stock){
		super(productName,price,category);
		this.discountPercent=discountPercent;
		this.stock=stock;
	}

	public void setDiscountPercent(double discountPercent){
		this.discountPercent=discountPercent;
	}

	public double getDiscountPercent(){
		return discountPercent;
	}

	public void setStock(int stock){
		this.stock=stock;
	}

	public int getStock(){
		return stock;
	}

	public double calculateDiscountPrice(){
		return getPrice()-(getPrice()*discountPercent/100);
	}

	public void showDiscountedProductInfo(){
		showProductInfo();
		System.out.println("Discount Percent: "+discountPercent);
		System.out.println("Stock: "+stock);
		System.out.println("Discounted Price: "+calculateDiscountPrice());
		System.out.println("------------------------");
	}
}

public class ProductManagement{
	public static void main(String[] args){
		DiscountedProduct p1=new DiscountedProduct("Laptop",1000,"Electronics",10,5);
		p1.showDiscountedProductInfo();

		DiscountedProduct p2=new DiscountedProduct();
		p2.setProductName("Phone");
		p2.setPrice(500);
		p2.setCategory("Electronics");
		p2.setDiscountPercent(15);
		p2.setStock(10);

		p2.showDiscountedProductInfo();

		System.out.println("Product Name: "+p2.getProductName());
		System.out.println("Price: "+p2.getPrice());
		System.out.println("Category: "+p2.getCategory());
		System.out.println("Discount Percent: "+p2.getDiscountPercent());
		System.out.println("Stock: "+p2.getStock());
	}
}