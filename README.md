# Art-Shopping-Application
This application aims to help customers to find a larger variety of art pieces from around the world and sellers to find interested buyers more easily.

## Tools
* [Java 16]
* [JavaFX]
* [Gradle]
* [Nitrite Java]

## Registration
The user needs to first register into the application by selecting one of the 2 roles: seller or customer. Both roles require a unique username, a password and the basic information like full name, contact information. For the seller, a code is required to create the account.

## Seller
After the seller logs in, he can see a list of all items registered in the application, he can add, edit or delete an item. 
Also, after logging in, he will see a list of orders from customers. 
From the list of orders, the seller can accept or reject a request, specifying a rejection reason.
A logged in seller can also act as a customer. 

## Customer
A customer needs to login into the application where he will be able to see a list with all the items registered into the application. The list should be searchable by the item’s name and can be filtered. From the list he can add an item to the shopping cart and place an order.
A logged in customer can also see a list with all the orders placed by him and their status. From this list he can cancel an order if the request wasn't already accepted by the seller.
