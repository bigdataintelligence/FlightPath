+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
This is a software package for FlightPath challenge. It was written in Java language 
and wrapped up with linux shell. Please find below instructions for running it.
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

============================
1. Prerequisite for testing
============================

a) Assume you have Java 1.8 runtime installed on your linux computer.
b) You have received FlightPath.zip file that consists of four files:
	- FlightPath.class
	- FlightPath.java
	- list-flight-paths.sh
	- README
c) You have extracted the files and copied over to ./bin folder.

============================
2. Test Cases
============================

-------------
Test case 2.1
-------------

./bin/list-flight-paths "Castle Black" "Riverrun"
Castle Black -> winterfell -> Riverrun: 55
Castle Black -> Riverrun: 80

-------------
Test case 2.2
-------------

./bin/list-flight-paths "Castle Black" 
Thank you for testing this application; here are your input: Castle Black
Correct Usage: Java FlightPath "source location" "target location"

-------------
Test case 2.3
-------------

./bin/list-flight-paths "Castle Black" "Riverrun" "winterfell"
Thank you for testing this application; here are your input parameter(s): Castle Black Riverrun winterfell
Correct Usage: Java FlightPath "source location" "target location"

-------------
Test case 2.4
-------------

./bin/list-flight-paths "Castle Red" "Riverrun"
Thank you for testing this application; here are your input parameter(s): Castle Red Riverrun
Either source location or target location is unrecognisable

-------------
Test case 2.5
-------------

./bin/list-flight-paths "winterfell" "King's Landing"
winterfell -> Riverrun -> King's Landing: 110
winterfell -> King's Landing: 50

-------------
Test case 2.6
-------------

./bin/list-flight-paths "Castle Black" "King's Landing"
Castle Black -> winterfell -> Riverrun -> King's Landing: 125
Castle Black -> winterfell -> King's Landing: 65
Castle Black -> Riverrun -> King's Landing: 150
Castle Black -> King's Landing: 90

-------------
Test case 2.6
-------------

./bin/list-flight-paths "Castle Black" "winterfell"
Castle Black -> winterfell: 15

-------------
Test case 2.7
-------------

./bin/list-flight-paths "winterfell" "Riverrun"
winterfell -> Riverrun: 40

-------------
Test case 2.8
-------------

./bin/list-flight-paths "Riverrun" "King's Landing"
Riverrun -> King's Landing: 70









