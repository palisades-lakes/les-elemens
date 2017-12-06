::call "C:\Program Files (x86)\IntelSWTools\compilers_and_libraries\windows\mkl\bin\mklvars.bat" intel64
@echo off
set XMX=-Xms28g -Xmx28g -Xmn11g 
set CP=-cp ./src/scripts/clojure;lib/*
set JAVA="%JAVA_HOME%\bin\java"
set CMD=%JAVA% -ea %XMX% %CP% clojure.main %*
%CMD%
