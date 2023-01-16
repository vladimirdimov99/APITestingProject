set projectLocation=C:\Users\Vladimir\IdeaProjects\APITestingProject
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause