# CP630-COVID19
# CP 630 Final Project - Covid-19 Vaccine &amp; Case Predictions #

## Project Setup Instructions: ##

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

## Using the Project, User-specific Instructions: ##

The service allows users to make Covid-19 predicitons using 5 different models (example below):
* KNN Percentage of Single Dose vaccinations given: Date, PHU name, and age group 
* KNN Percentage of Two Dose Vaccinations given: Date, PHU name, and age group
* LN Number of Deaths Prediction given: PHU name, number of active cases, and number of resolved cases
* LN Number of Resolved Covid-19 Cases given: PHU name, number of active cases, and number of deaths
* RF Public Health Unit (PHU) Prediction given: Date, number of active cases, and number of resolved cases, and number of deaths
![image](https://user-images.githubusercontent.com/84038261/146697644-f10015a9-0e7e-4ee3-8320-526a3736d136.png)


1) General Users:
   * Users can choose any models (of 5) available to them during their session make different predictions
   * Prediction results are displayed immediately below input form and are persisted in a SQL log
   * Users can choose a colour preference for the site for their session
   * Users can log out returning them to the log in page
   * General user page example:
   ![image](https://user-images.githubusercontent.com/84038261/146697701-d20f0db9-167c-4e3e-a23a-f40abd9158bb.png)


2) Admin Users:
   * Admins can select which of the 5 models are available for prediction to the general users
   * Admins can always use all 5 to make predicitons
   * Prediction results are displayed immediately below input form and are persisted in a SQL log
   * Admins can manage users by adding or deleting them from the system (SQL persistence)
   * Admins can view a total log of all predictions made
   * Admins can choose a colour preference for the site for their session
   * Admins can log out returning them to the log in page
   * Admin user page example:
   ![image](https://user-images.githubusercontent.com/84038261/146697724-825a5267-47ed-45cf-a5d8-e5bdd3a9f55c.png)

