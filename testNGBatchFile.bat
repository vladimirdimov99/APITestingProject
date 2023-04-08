set projectLocation=C:\Users\Vladimir\.jenkins\workspace\TestNGProject
cd %projectLocation%
echo %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
echo %classpath%
java org.testng.TestNG %projectLocation%\testng.xml
pause