document.addEventListener("DOMContentLoaded", function() {
    // Get a reference to the product list container
    var productList = document.getElementById("product-list");

    // Make a GET request to the /api/products/ endpoint
    fetch("/api/products/")
        .then(function(response) {
            return response.json(); // Parse the response as JSON
        })
        .then(function(data) {
            // Iterate through the product data and display it
            data.forEach(function(product) {
                var productItem = document.createElement("div");
                productItem.innerHTML = `
                    <h3>${product.name}</h3>
                    <p>Price: $${product.price}</p>
                    <p>Description: ${product.description}</p>
                    <img src="${product.imageUrl}" alt="${product.name}" width="100">
                `;
                productList.appendChild(productItem);
            });
        })
        .catch(function(error) {
            console.error("Error fetching product data: " + error);
        });
});
