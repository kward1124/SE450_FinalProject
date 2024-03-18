# Project Overview

Welcome to this software development project, designed to solidify your understanding of object-oriented design principles and patterns through the creation of a comprehensive shopping system. This project offers a practical application of the concepts taught in the course, including SOLID principles and the Singleton, Factory, and Builder design patterns. The aim is to provide an experience in building a robust and maintainable software application from the ground up, demonstrating the application of these concepts in a real-world scenario.

## Key Features

- **User Authentication**: A secure login system where customers' cart data is linked to their accounts.
- **Product Catalog**: A dynamic catalog of products, which can be stored in memory or persisted in a database, showcasing essential product information.
- **Order Processing**: Utilization of design patterns such as Singleton and Factory for efficient cart management and product instantiation.
- **Payment Processing**: A simulated payment system to handle transactions, ensuring it operates independently from the core shopping functionalities.
- **Logging**: A logging mechanism to record significant events and transactions throughout the application lifecycle.

## Learning Objectives

- **SOLID Principles**: Ensuring that each component of the system adheres to SOLID principles, promoting a clean, scalable, and manageable codebase.
- **Design Patterns**: The implementation of key design patterns (Singleton, Factory, Builder) to solve common design problems effectively.
- **Practical Experience**: Gaining hands-on experience with application development, from user authentication to payment processing.
- **Tooling**: Introduction to essential development tools and practices, including GitHub for version control and unit testing for ensuring code quality.

# Application User Guide

This guide provides a comprehensive overview of the user journey and functionalities within the shopping application. It outlines the process from the initial welcome message to navigating through various features such as browsing products, viewing the cart, checking out, and logging out.

## Welcome and Authentication Process

Upon launching the application, users are greeted and navigated through the authentication process:

1. **Welcome Message**: "Welcome to the store! Have you shopped with us before? (Yes/No)"

2. **For Returning Users**:
   - Users who answer "Yes" are prompted to log in with their username and password.

3. **For New Users**:
   - Users answering "No" are guided through creating a new account, including setting up a username and password.

## Main Menu Features

After logging in, the main menu offers four key options:

- **Browse Products**
- **View Cart**
- **Checkout**
- **Log Out**

### Browse Products

- Users can explore the store's offerings by department: Appliance, Building Materials, or Tools.
- After selecting a department, a list of products is displayed.
- Products can be added to the cart, with the option to specify quantities.
- Users can continue shopping in the current department or switch to a different one.

### View Cart

- This option displays the items in the user's cart, including details like item names, quantities, and total price.
- Users can review and adjust their cart as needed.

### Checkout

- Users are shown the total price and prompted to confirm payment.
- Upon confirmation, the payment process is initiated:
  - A successful transaction clears the cart and notifies the user of the success.
  - A failed transaction prompts the user to retry or contact support.

### Log Out

- Selecting this option logs the user out of the system and displays a thank-you message.

## User Experience Flow

- The application is designed to provide an intuitive and seamless shopping experience.
- Users can easily navigate between shopping, cart management, and checkout processes.
- The application ensures informative feedback is provided throughout the user's journey.

## Technical Implementation Notes

- Secure session management and dynamic product listings are key technical considerations.
- The shopping cart's persistence is crucial for a seamless shopping experience.
- Payment processing can be simulated or integrated with a payment gateway for real transactions.
- Security measures should be in place for user authentication and session termination.

