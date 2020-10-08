# Pre-requisites for running the tests : 
- Run the flask app 
- Navigate to http://127.0.0.1:5000/ and verify that flask app is up and running
- Navigate to  https://www.instacart.com/store/{Store Identifier}/storefront
- Try to get the _instacart_session cookie value for the above mentioned url





# How to run the tests :  
- Clone the repo 
- Under src/test/resources you will see the profile1.properties
- Change the value of test.cookie to the _instacart_session cookie which was already obtained for the instacart web page 
- From command prompt run below command 
```gradlew.bat clean test -Dprofile=profile1``` or ```gradle clean test -Dprofile=profile1```


