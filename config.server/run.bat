@echo off
title superstring-rdmp-provider
set myself=%~dp0


call java -Xms256m -Xmx512m -Dfile.encoding=UTF-8 -cp %myself%\*;%myself%\target\* org.springframework.boot.loader.JarLauncher --app.root=file:/%myself%

call run.bat