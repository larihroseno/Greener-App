# Greener1
NEW GREENER APP - still working on this


GREENER APP
Technologies used:
IDE platform: ANDROID STUDIO – compile Sdk Version 29

Emulator: Physical device = HTC M8 phone (android)

Code language: JAVA
Database: Firebase FireStore – cloud-based database

At our first meeting with the supervisor, it was decided that we would make a website and an application for android mobile. After showing it as a possibility to the Supervisor he agreed with our idea. After researching the platforms used to develop an application, we decided to use Android Studio. 
Android Studio is an IDE for development on the Android platform based on the version of the InteliJ community. With the same goal of Eclipse + ADT (Android Developer Tools), it provides a development, debugging, testing and multiplatform profile environment for Android.
As Android studio is a heavy platform with many tools, it was not viable for everyone in the group to download it. My laptop has a better development and a bigger memory, so I decided to be responsible for this part of our project.

Bellow I’ll give more information on how the first part of the project was developed:

Greener App first part: FRONT-END

At first, a research was done on what is android studio and how it works. The first part of the project was based on collecting data to create it. To achieve it we made app colours research, diagrams, questionnaires to possible users.

This first part of the project would be based on deciding how the project's FRONT-END would look like and the technologies used to achieve this.
But before starting creating pages on android studio, I created html pages to help on visualize how would be the flow of the app.

 
After visualizing the flow of the project, I created my first page in Android Studio using the emulator of the application itself to view it.
First page:
 


How to create pages in android studio?
Simple!!!
1. Create a project:
Click on file -> new -> new project - Choose a name for the project.

 

2. Create activities (pages):
Click on app -> new -> new activity -> choose the desired type of activity
 

2 files will be created. 1 xml file for design and 1 Java file for development.
 

 
3. Design the page using buttons, texts, etc., that the application itself offers.

 

4. Create emulators so that you can view these pages. The application itself offers options for emulators.
 
You ready to start developing!!!
Greener APP second part of the project: BACK-END
The second part of the project would be the development of the application back-end.
How would it have functionality?

Android Studio can be developed in 3 different languages: Java, Kotlin and C +.

Java was the language we decided to use. Java was the best option for this project because we have been studying java since the first semester so I would be easier to develop.

Android Studio provides a series of tools that facilitate the development of the Back-end. Among them there are Java classes and methods that make it possible to start coding without major problems. As in the following example:

This part of the java code is provided by the Android Studio when you first create an activity.

 

Following I’ll demonstrate how I developed this part of the project:
By this time I have created the Main Page Login Page, Register Page, About the project Page, Green Tech professionals Page, Users Testimonials of the project Page.









Main page:
To create the Main Page, I chose an image written "#Greener APP- Sustainable Life Habits" that I created in the CANVA application to serve as the background of the page.
I created a Text view saying: “Greener the environmentally friendly app”
2 buttons: Login and Register
Menu bar with 3 Items (Pages) = About - GreenTech – Testimonials (Those pages are visible before logging in or registering).
Main Page:
 








About page:
For the creation of the About Page I chose a green image as a background that was used on all the following pages.
I created 2 TextView where was included some information about the app.
1 Button = This button has a functionality to return to the main page.
Menu bar with options to view the GreenTech page and Testimonials page.
About Page:
 









Testimonial page:
To create the page I used a green image as a background that was used on all the following pages.
3 TextView – User testimonials about the application
 1 Button = This button has a functionality to return to the main page. 
Menu Bar with options to view the GreenTech and about pages.

Testimonials Page: This page is still not well formatted -  it will be fixed in the next steps
 









Green Tech page
To create the page, I used a green image as a background that was used on all the following pages.
I created 4 TextView – Those textview has a description of the project developers
 4 images – Our group is made of 4 developers
1 Button = This button has a functionality to return to the main page.
Bar menu with options to view the About page and Testimonials page.

Green Tech page:
 






Login page
To create the page I used a green image as a background that was used on all the following pages.
2 plain text fields to insert data such as: Email & Password
2 Buttons = 1- Login to enter the application - if you have already registered with the application, this button will take you to the home page. If you have not registered, the Login page will inform you that the user needs to register.
  


2-Register button if you don't have a previous registration. This button will take the user to the registration page.










Register page:
To create the page I used a green image as a background that was used on all the following pages.

3 plain text fields to insert data such as: username, Email & Password
Email & password will be used to login to the app and username to be identified inside the application.

2 buttons = 1-Register to enter the application - if the information is correct, such as: email in correct format and password with a minimum of 6 characters, this button will take you to the Login page so that the user can enter the registered data and be able to access the home page.

Register Page:
 



2-Login Here button if you already have a previous registration. This button will take the user to the Login page.



How was the registration data stored?
The data created in the Register Page were stored using the FIREBASE database.

Firebase is a mobile (and web) development platform, with a focus on being a complete and easy-to-use back-end, this tool provides several different services that assist in the development and management of applications.

It is integrated with android studio and provides a series of tools that help in the process of registering users and saving them.
To use these tools you need to implement them in Android Manifest (xml file that contains all the information about the project.)
The Firebase Platform provides Java code on how to use the database. I needed to include them in my existing code so that I could use the functions without changing my code.
When the user makes a new registration the information goes to a Firebase database called FireStore that I created using the java code. I’ve used the part of java code to create a table and the columns that I define in the code as: USERNAME AND EMAIL. Password is saved together with user id in the firebase authentication session.

















 
