# Product Management System

## Overview

The **Product Management System** is a web-based application designed to manage products and categories efficiently. It allows users to create, update, list, and delete products in an intuitive interface. This system uses **Spring Framework** on the backend and **Thymeleaf** for rendering dynamic HTML pages on the frontend. The system also integrates with a **PostgreSQL** database to store product and category data.

### Technologies Used

- **Java Version:** 17
- **Spring Boot Version:** 3.0.x
- **Spring Security:** Used for authentication and login functionality.
- **Spring Data JPA:** For database interaction.
- **PostgreSQL**: The relational database used for persisting products and categories.
- **Thymeleaf:** The template engine used for rendering HTML views.
- **Spring Validation (Jakarta Validation)**: For validating user inputs.
- **Bootstrap CSS**: For basic styling and layout.
  
## Features

- **User Authentication**: Login functionality with Spring Security.
- **Product Management**:
  - Add new products.
  - Edit existing products.
  - Delete products.
  - View all products in a tabular format.
  - Sort products by name or price by clicking on the table headers.
- **Category Management**:
  - Add and assign categories to products.
  - View categories in product listings.
  
### Sorting by Name and Price

Users can click on the "Name" or "Price" headers of the product table to sort the products in ascending or descending order based on these fields. This feature improves the ease of managing large inventories.

### Folder Structure

```bash
src
├── main
│   ├── java
│   │   └── com
│   │       └── qima
│   │           └── product_management_system
│   │               ├── config           # Configuration files (Spring Security)
│   │               ├── controller       # Contains controllers handling user requests
│   │               ├── dto              # Data Transfer Objects (ProductDTO, CategoryDTO)
│   │               ├── model            # Entity models (Product, Category)
│   │               ├── repository       # Spring Data JPA Repositories
│   │               ├── service          # Service layer for business logic
│   ├── resources
│   │   ├── static                       # Static resources (CSS, JavaScript)
│   │   ├── templates                    # Thymeleaf HTML templates
│   │   │   ├── login.html               # Login page template
│   │   │   ├── product-list.html        # Template for listing products
│   │   └── application.properties       # Application configuration (DB connection, etc.)
└── test
```

### Installation & Setup
- Clone the Repository:

```bash
git clone https://github.com/yourusername/product-management-system.git
```

- Setup PostgreSQL Database:
Make sure you have a PostgreSQL database installed and running. Create a new database for the project.

Update the `application.properties` with your database credentials:
```bash
properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/product_management_db
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

- Run the Application:
```bash
./mvnw spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

### How to Use

Login
- Use the default login credentials: admin/password.
- Once logged in, you can access the product management system.

Adding and Editing Products
- Fill in the fields such as Name, Description, Price, Category, and Availability.
- Click Add Product to save the product.

Sorting Products
- The table of products can be sorted by Name or Price. Simply click on the column header (either Name or Price) to toggle the sorting order between ascending and descending.

Deleting Products
- Each product has an Edit and Delete link under the Actions column. Click on Delete to remove a product from the system.

Screenshots

Login Page
![image](https://github.com/user-attachments/assets/6bb5719f-cee4-44db-9851-eb2f960cc437)

Product Management Page
![image](https://github.com/user-attachments/assets/bace9edc-f6b4-4f43-b050-1d4da213bf0c)
