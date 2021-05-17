# Technical Report #
## Joe Lynch – 20075072 ##
## Creative Computing #
## Mobile Application Development
- - - -
## Personal Statement

I have learned many new skills by completing the mobile application assignments. The main skill I learned was the Kotlin language. I have never used Kotlin before completing these assignments and I found programming with Kotlin to be very informative and easier to pick up compared to Java. Kotlin allows you to create custom functions that can be used within your application, the code is easier to compile and appears lightweight. I also learned how to debug properly and understand the error messages that are displayed. I will be able to use this skill with any type of programming language in the future. I really enjoyed the module and thought it was structed well. I hope I will be able to develop more applications and create new material in the future using Kotlin.


![picture alt](https://i.imgur.com/YYP8N7z.png)

I decided to create an application that users could post their old clothing for sale. I named this application Wear It and developed and maintained this application for both of my assignments in this module. Below is a list of the functionality that the application provides.
* Firebase Register/Sign in
* Google Register/Sign in
* Add Products
* Edit Products
* Delete Products
* About us information
- - - -
## Functionality 

### Firebase Auth

I setup the ability to store user’s data within Google Firebase. Users were able to register a unique account through the application and have the data stored within Firebase. If another user tried to register using the same email, then they it would display an error, making the user’s account unique. I added limits to the password field when registering to having at least 8 characters. The user would have to enter an email and password before having their account register successfully. Once signed up the user would be able to login into their account and proceed into application. If the users did not want to use their email, then they have the option of using their Google account instead, making it more accessible.

![picture alt](https://i.imgur.com/rAfMvYP.png)

### Products

I gave the user the ability to add a new product to the listings. Since it was a clothing product certain inputs needed to be filled out. The user needs to enter the Brand, the type of product, description, price, size and an image of the product their selling.
Once the product is made the user can delete or edit their product through the listings screen. 

![picture alt](https://i.imgur.com/JylDqju.png)
![picture alt](https://i.imgur.com/rV04psu.png)


### Navigation

I decided to use a few different ways of navigating through the application. When the user is in the main menu screen, they will have the option of using the bottom navigation or the navigation drawer. The user can add or view products using the bottom navigation and the user can view products, add products, view the about us section and logout using the navigation drawer.

![picture alt](https://i.imgur.com/UCJkbJK.png)
![picture alt](https://i.imgur.com/I08QYA8.png)

## Use Case

![picture alt](https://i.imgur.com/4AeYa62.png)

## Design

It took many different variations to try find a colour scheme that I liked. I decided to go with a blue scheme. I made all my text either white or baby blue and my backgrounds a darker blue. I thought this worked well when the finished product was done. 

![picture alt](https://i.imgur.com/pesccGo.png)

## Git Approach

I used GitHub with my application from the very start. I always use GitHub when developing and programming as it I can monitor my commit history, easily download the project or edit a certain branch. 

I had over 50 unique commits with this project which made it much easier when reverting to a specific point in my project. I had one or two issues with commit conflicts, but I was able to revert to a previous point and fix the issues. 

I created a new branch when developing a new function or creating a new element in my application. This made it easier to view the changes I made for that specific function or element. In total I have 11 branches to date and plan on developing the application and adding more in the future.

I created two releases, one when completing the first assignment of the module and the second when completing the second assignment.

[Project Repo](https://github.com/JLynch101/Wear-It/tree/main/Wear%20it%20App)
