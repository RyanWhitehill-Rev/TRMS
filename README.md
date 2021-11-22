# TRMS
The 'Tuition Reimbursement Management System' project

# Project Description

This is a web application built to create and track reimbursement requests within a company for employees who wish to be reimbursed for completing relevant coursework.

# Technologies Used

Front end:
  Written in Javascript, HTML, CSS 
  +Bootstrap
  
  Tested with Cucumber, via Selenium

Backend:
  Written in Java
  Javalin
  Hibernate
  
  Tested via JUnit and Postman
  
Data stored and accessed via PostgreSQL, in an AWS database

# Features

Existing users may sign in, then make use of the following functionality:
  View their existing reimbursement requests
  Create new requests for various course criteria
  Track their requests through the approval process
  Attach additional information to their requests (as desired by other users in the approval process)
  Attach some form of proof of a grade or presentation for the request to be granted

Action of other users is necissary for a reimbursement request to move through the approval process
  Approval of a supervisor, a department head, and a benefits coordiantor are all required
  Each of these users can ask for additional information from the request submitter, as well as those who have previously approved the request

# Getting Started

Run a 'git clone' command in a folder of your choosing
-- git clone https://github.com/RyanWhitehill-Rev/TRMS.git

Set up a database and configure the hibernate connection parameters in the config file (TRMS/RevProj_2/src/main/resources/hibernate.cfg.xml)
  By default, this project used PostgreQL and was hosted on Amazon Web Services RDS

Open the backend of the project in the IDE of your choice, and navigate to the app.java file (TRMS/RevProj_2/src/main/java/com/revature/app/App.java)
  Run the main function inside this class to host the application.
  
Access the webpage by opening the index.html file (TRMS/RP2_fe/myhtml.html)





