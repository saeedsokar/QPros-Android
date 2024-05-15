#QPros-Android
This Repo. contains a script to open Weather App https://play.google.com/store/apps/details?id=com.info.weather.forecast&pli=1 

* use the following command to checkout "git checkout main"
* To Run the solution: open the root path of the project then try this "mvn clean package"

#Description:
Script will open the main activity "user settings activity"
change the Temperature from F to C and Time format from 12 to 24 hours
navigate and assert on Home screen
then Open menu, unit settings screen
change the Temperature from C to F and Time format from 24 to 12 hours
navigate and assert on Home screen

#Features:
1. The solution is developed in JAVA and Appium.
2. Reports are generated per each test case.
3. A screenshot is captured per each test case.
4. The solution is following -> Page object model design pattern with simple SOLID principles to be adoptable to changes and provide usability.
5. The solution allows parallel testing through testng.xml file

#Setup
1. make sure that Maven is downloaded and installed
2. make sure to project JAVA SDK from project settings = 20
3. install android studio, to create virutal device or connect to real device
4. make sure to download the app
5. from /src/main/resources , open config-android.properties
6. change the device name to the executable one
7. From the root path of the checkout main branch locally, type in terminal "mvn clean package"
8. after execution, open report in chrome from reports directory in the solution
9. after execution, open screenshots from /src/test/resources/screenshots
   
