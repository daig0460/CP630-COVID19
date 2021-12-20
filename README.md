# CP630-COVID19
CP 630 Final Project - Covid-19 Vaccine &amp; Case Predictions

Project Setup Instructions: 

1) Ensure a local mysql instance is running (launched from Xampp Control Panel) and the following tables exist (examples from testing included below):
   a) appusers(userid, username, password, isadmin)
   ![image](https://user-images.githubusercontent.com/84038261/146696840-9cacd1e3-a88a-42f4-92e7-b40fdff61810.png)
   b) log(logid, userid, date, loginfo)
   ![image](https://user-images.githubusercontent.com/84038261/146696856-a663ea34-f081-40cd-bbe0-0d08cb661e98.png)
   c) predictionmodel(modelid, modelname, isviewable)
   ![image](https://user-images.githubusercontent.com/84038261/146696863-f42279c8-cc59-4e26-b2be-66885969cd5c.png)
   d) weka(id, modelname, model)
   ![image](https://user-images.githubusercontent.com/84038261/146696873-dc809f1d-17ac-430e-9853-69d8fb9d5a2d.png)
  Predictionmodel and weka must be prepopulated, in additon to prepopulating at least 1 admin user in appusers; the code used to populate these tables are included in "ec-project-service/ejbModule/ec/project/db/client":
   a) ProjectDBClientCreate.java
   b) ProjectDBClientInsert.java
  These files must be copied to an appropriate maven project which is able to run the code as a java application.
  The insert code does not contain all weka table insertion calls, 1 call was coded and the files needing insertion were swapped
  
2) Deploy ec-project-ear to a running Wildfly instance configured with standalone-full.xml: http://localhost:8080/ec-project-web (Login Page)

Using the project instructions (user specific):

1)
