**Mobile Development 2022/23 Portfolio**
# API description

Student ID: `21049134`

I decided to use Bottom Navigation bar to navigate through the app as this type of navigation settles the most essential parts of the application within the reach of the users’ thumb, therefore making it more user friendly (Kaushik, 2021). Furthermore, due to the nature of the application using a different navigation (Hamburger menu), would not be suitable because of the simplicity of the application. 

I decided to use Room database to store the data for the application for serval reasons; one reason is that Room supports offline data persistence, meaning that the application can function even when it is not connected to the internet. Another reason is that Room is type-safe, which means you will get compile-time checks of the SQL statements, reducing the likelihood of runtime SQL errors. On the other hand, if the user used the application over a long period of time, the application size would increase which is inefficient. Other databases like Firebase have some advantages over Room, for example, Firebase provides a NoSQL cloud database for storing and sync data in real-time, however, this is particularly beneficial for applications that require multi-user collaboration or real-time updates. This was unnecessary for my application. 

I decided to use GraphView API to produce the graphs needed for my application due to its simplicity; GraphView is easy to use and integrates well with Android application, it has a simple API, which makes it a good choice for projects with straightforward graphing needs. Another reason is the customizability, GraphView provides a lot of flexibility and control over the appearance of the graphs produced, for example, GraphView allows the developer to customize colours, labels, gridlines and much more. Furthermore, GraphView supports a variety of graph types, which means the application can be further developed into showing different graph types.

# References

- Kaushik, V. (2021) How to design a Better Bottom Navbar (Tab Bar)?, Medium. Available at: https://uxplanet.org/how-to-design-a-better-bottom-navbar-5127c8b8102f (Accessed: 16 May 2023). 
