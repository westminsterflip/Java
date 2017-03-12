@echo off
mkdir test
copy "ha.bat" "test/ha.bat"
cd test
ha.bat
cd ..
del t.bat
