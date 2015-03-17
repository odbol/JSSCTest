This test suite shows the bug in JSSC where sometimes it misses bytes, or retreives duplicate bytes on reading.

Tested on 2013 MacBook Pro, OS X 10.9.5, with an Arduino Due.

Tested multiple times with a Serial Terminal (CoolTerm), so I'm pretty sure it's not the Arduino.

INSTALLATION:
----------------

0. Obtain an Arduino, upload the Arduino/JSSCTestSysex sketch to it.
1. Import as Eclipse project.
2. Run ArduinoJSSCTest.java as JUnit 3 Test, while the Arduino is plugged in.
4. If the test passes, keep running it. For me, it would usually fail on the 5th time.


Example output
------------------


WORKING RESPONSE:

````
Requesting update -1: BF7B01
-
>F0
Start Sysex
>BF>7C>01>BF>7D>17>BF>7E>16>B0>01>42>B0>02>00>B0>03>7F>B0>04>5F>B0>05>7F>B0>06>0C>B0>07>00>B0>08>00>B0>09>26>B0>0A>16>B0>0B>00>B0>0C>00>B0>0D>00>B0>0E>00>B1>01>39>B1-
>02>00>B1>03>7F>B1>04>00>B1>05>7F>B1>06>0C>B1>07>00>B1>08>00>B1>09>2F>B1>0A>17>B1>0B>0C>B1>0C>00>B1>0D>00>B1>0E>00>B2>01>39>B2>02>00>B2>03>7F>B2>04>00>B2>05>7F>B2>06>0C>B2>07>00>B2>08>00>B2>09>27>B2>0A>17>B2>0B>0C>B2>0C>00>B2>0D>00>B2>0E>00>B3>01>39>B3>02>00>B3>03>7F>B3>04>00>B3>05>7F>B3>06>0C>B3>07>00>B3>08>00>B3>09>30>B3>0A>17>B3>0B>0C>B3>0C>00>B3>0D>00>B3>0E>00>B4>01>39>B4>02>00>B4>03>7F>B4>04>00>B4>05>7F>B4>06>0C>B4>07>00>B4>08>00>B4>09>39>B4>0A>17>B4>0B>0C>B4>0C>00>B4>0D>00>B4>0E>00>B5>01>39>B5>02>00>B5>03>7F>B5>04>00>B5>05>7F>B5>06>0C>B5>07>00>B5>08>00>B5>09>2E>B5-
>0A>17>B5>0B>0C>B5>0C>00>B5>0D>00>B5>0E>00>B6>01>39>B6>02>00>B6>03>7F>B6>04>00>B6>05>7F>B6>06>0C>B6>07>00>B6>08>00>B6>09>37>B6>0A>17>B6>0B>0C>B6>0C>00>B6>0D>00>B6>0E>00>B7>01>39>B7>02>00>B7>03>7F>B7>04>00>B7>05>7F>B7>06>0C>B7>07>00>B7>08>00>B7>09>2B>B7>0A>17>B7>0B>0C>B7>0C>00>B7>0D>00>B7>0E>00>B8>01>5F>B8>02>00>B8>03>7F>B8>04>6B>B8>05>7F>B8>06>1D>B8>07>00>B8>08>00>B8>09>23>B8>0A>17>B8>0B>2A>B8>0C>08>B8>0D>00>B8>0E>66>B9>01>5F>B9>02>00>B9>03>7F>B9>04>6B>B9>05>7F>B9>06>1D>B9>07>00>B9>08>00>B9>09>2C>B9>0A>17>B9>0B>2A>B9>0C>08>B9>0D>00>B9>0E>66>BA>01>5F>BA>02>00>BA>03>7F>BA>04>6B>BA>05>7F>BA>06>1D>BA>07>00>BA>08>00>BA>09>38>BA>0A>17>BA>0B>2A>BA>0C>08>BA>0D>00>BA>0E>66>BB>01>5F>BB>02>00>BB>03>7F>BB>04>6B>BB>05>7F>BB>06>1D>BB>07>00>BB>08>00>BB>09>4D>BB>0A>17>BB>0B>2A>BB>0C>08>BB>0D>00>BB>0E>66>BC>02>6C>BC>03>02>F7
finishSysex of length 519
````



ISSUE 1

// DUPLICATE BYTES in begining - seem to replace the needed F0 byte with BF:

````
Requesting update -1: BF7B01
-
>BF.>BF.>7C.>01.>BF.>7D.>17.>BF.>7E.>B0.>B0.>01.>42.>B0.-
>02.>00.>B0.>03.>B0.>B0.>04.>5F.>B0.>05.>7F.>B0.>06.>B0.>B0.>07.>00.>B0.>08.>00.>B0.>09.>B0.>B0.>0A.>16.>B0.>0B.>00.>B0.>0C.>B0.>B0.>0D.>00.>B0.>0E.>00.>B1.>01.>B1.>B1.>02.>00.>B1.>03.>7F.>B1.>04.>B1.>B1.>05.>7F.>B1.>06.>0C.>B1.>07.>B1.>B1.>08.>00.>B1.>09.>2F.>B1.>0A.>B1.>B1.>0B.>0C.>B1.>0C.>00.>B1.>0D.>B1.>B1.>0E.>00.>B2.>01.>39.>B2.>02.>B2.>B2.>03.>7F.>B2.>04.>00.>B2.>05.>B2.>B2.>06.>0C.>B2.>07.>00.>B2.>08.>B2.>B2.>09.>27.>B2.>0A.>17.>B2.>0B.>B2.>B2.>0C.>00.>B2.>0D.>00.>B2.>0E.>B3.>B3.>01.>39.>B3.>02.>00.>B3.>03.>B3.>B3.>04.>00.>B3.-
>05.>7F.>B3.>06.>B3.>B3.>07.>00.>B3.>08.>00.>B3.>09.>B3.>B3.>0A.>17.>B3.>0B.>0C.>B3.>0C.>B3.>B3.>0D.>00.>B3.>0E.>00.>B4.>01.>B4.>B4.>02.>00.>B4.>03.>7F.>B4.>04.>B4.>B4.>05.>7F.>B4.>06.>0C.>B4.>07.>B4.>B4.>08.>00.>B4.>09.>39.>B4.>0A.>B4.>B4.>0B.>0C.>B4.>0C.>00.>B4.>0D.>B4.>B4.>0E.>00.>B5.>01.>39.>B5.>02.>B5.>B5.>03.>7F.>B5.>04.>00.>B5.>05.>B5.>B5.>06.>0C.>B5.>07.>00.>B5.>08.>B5.>B5.>09.>2E.>B5.>0A.>17.>B5.>0B.>B5.>B5.>0C.>00.>B5.>0D.>00.>B5.>0E.>B6.>B6.>01.>39.>B6.>02.>00.>B6.>03.>B6.>B6.>04.>00.>B6.>05.>7F.>B6.>06.>B6.>B6.>07.>00.>B6.>08.>00.>B6.>09.>B6.>B6.>0A.>17.>B6.>0B.>0C.>B6.>0C.>B6.>B6.>0D.>00.>B6.>0E.>00.>B7.>01.>B7.>B7.>02.>00.>B7.>03.>7F.>B7.>04.>B7.>B7.>05.>7F.>B7.>06.>0C.>B7.>07.>B7.>B7.>08.>00.>B7.>09.>2B.>B7.>0A.>B7.>B7.>0B.>0C.>B7.>0C.>00.>B7.>0D.>B7.>B7.>0E.>00.>B8.>01.>5F.>B8.>02.>B8.>B8.>03.>7F.>B8.>04.>6B.>B8.>05.>B8.>B8.>06.>1D.>B8.>07.>00.>B8.>08.>B8.>B8.>09.>23.>B8.>0A.>17.>B8.>0B.>B8.>B8.>0C.>08.>B8.>0D.>00.>B8.>0E.>B9.>B9.>01.>5F.>B9.>02.>00.>B9.>03.>B9.>B9.>04.>6B.>B9.>05.>7F.>B9.>06.>B9.>B9.>07.>00.>B9.>08.>00.>B9.>09.>B9.>B9.>0A.>17.>B9.>0B.>2A.>B9.>0C.>B9.>B9.>0D.>00.>B9.>0E.>66.>BA.>01.>BA.>BA.>02.>00.>BA.>03.>7F.>BA.>04.>BA.>BA.>05.>7F.>BA.>06.>1D.>BA.>07.>BA.>BA.>08.>00.>BA.>09.>38.>BA.>0A.>BA.>BA.>0B.>2A.>BA.>0C.>08.>BA.>0D.>BA.>BA.>0E.>66.>BB.>01.>5F.>BB.>02.>BB.>BB.>03.>7F.>BB.>04.>6B.>BB.>05.>BB.>BB.>06.>1D.>BB.>07.>00.>BB.>08.>BB.>BB.>09.>4D.>BB.>0A.>17.>BB.>0B.>BB.>BB.>0C.>08.>BB.>0D.>00.>BB.>0E.>BC.>BC.>02.>6C.>BC.>03.>02.>F7.
````




ISSUE 2
// bytes are out of order?? see 2nd byte and 4th byte, and missing 9th byte

````
Requesting update -1: BF7B01
-

>BF.>16.>7C.>01.>BF.>7D.>17.>BF.>7E.>B0.>7F.>01.>42.>B0.>02.>00.>B0.>03.>B0.>B0.>04.>5F.>B0.>05.>7F.>B0.>06.>B0.>26.>07.>00.>B0.>08.>00.>B0.>09.>B0.>B0.>0A.>16.>B0.>0B.>00.>B0.>0C.>B0.>39.>0D.>00.>B0.>0E.>00.>B1.>01.-
>B1.>00.>02.>00.>B1.>03.>7F.>B1.>04.>B1.>00.>05.>7F.>B1.>06.>0C.>B1.>07.>B1.>B1.>08.>00.>B1.>09.>2F.>B1.>0A.>B1.>00.>0B.>0C.>B1.>0C.>00.>B1.>0D.>B1.>B1.>0E.>00.>B2.>01.>39.>B2.>02.>B2.>B2.>03.>7F.>B2.>04.>00.>B2.>05.>B2.>00.>06.>0C.>B2.>07.>00.>B2.>08.>B2.>0C.>09.>27.>B2.>0A.>17.>B2.>0B.>B2.>B2.>0C.>00.>B2.>0D.>00.>B2.>0E.>B3.>7F.>01.>39.>B3.>02.>00.>B3.>03.>B3.>0C.>04.>00.>B3.>05.>7F.>B3.>06.>B3.>B3.>07.>00.>B3.>08.>00.>B3.>09.>B3.>B3.>0A.>17.>B3.>0B.>0C.>B3.>0C.>B3.>39.>0D.>00.>B3.>0E.>00.>B4.>01.>B4.>00.>02.>00.>B4.>03.>7F.>B4.>04.>B4.>00.>05.>7F.>B4.>06.>0C.>B4.>07.>B4.>17.>08.>00.>B4.>09.>39.>B4.>0A.>B4.>B4.>0B.>0C.>B4.>0C.>00.>B4.>0D.>B4.>00.>0E.>00.>B5.>01.>39.>B5.>02.>B5.>7F.>03.>7F.>B5.>04.>00.>B5.>05.>B5.>00.>06.>0C.>B5.>07.>00.>B5.>08.>B5.>0C.>09.>2E.>B5.>0A.>17.>B5.>0B.>B5.>00.>0C.>00.>B5.>0D.>00.>B5.>0E.>B6.>7F.>01.>39.>B6.>02.>00.>B6.>03.>B6.>0C.>04.>00.>B6.>05.>7F.>B6.>06.>B6.>B6.>07.>00.>B6.>08.>00.>B6.>09.>B6.>B6.>0A.>17.>B6.>0B.>0C.>B6.>0C.-
>B6.>39.>0D.>00.>B6.>0E.>00.>B7.>01.>B7.>B7.>02.>00.>B7.>03.>7F.>B7.>04.>B7.>00.>05.>7F.>B7.>06.>0C.>B7.>07.>B7.>17.>08.>00.>B7.>09.>2B.>B7.>0A.>B7.>00.>0B.>0C.>B7.>0C.>00.>B7.>0D.>B7.>00.>0E.>00.>B8.>01.>5F.>B8.>02.>B8.>7F.>03.>7F.>B8.>04.>6B.>B8.>05.>B8.>00.>06.>1D.>B8.>07.>00.>B8.>08.>B8.>2A.>09.>23.>B8.>0A.>17.>B8.>0B.>B8.>66.>0C.>08.>B8.>0D.>00.>B8.>0E.>B9.>7F.>01.>5F.>B9.>02.>00.>B9.>03.>B9.>1D.>04.>6B.>B9.>05.>7F.>B9.>06.>B9.>2C.>07.>00.>B9.>08.>00.>B9.>09.>B9.>08.>0A.>17.>B9.>0B.>2A.>B9.>0C.>B9.>5F.>0D.>00.>B9.>0E.>66.>BA.>01.>BA.>6B.>02.>00.>BA.>03.>7F.>BA.>04.>BA.>00.>05.>7F.>BA.>06.>1D.>BA.>07.>BA.>17.>08.>00.>BA.>09.>38.>BA.>0A.>BA.>00.>0B.>2A.>BA.>0C.>08.>BA.>0D.>BA.>00.>0E.>66.>BB.>01.>5F.>BB.>02.>BB.>7F.>03.>7F.>BB.>04.>6B.>BB.>05.>BB.>00.>06.>1D.>BB.>07.>00.>BB.>08.>BB.>BB.>09.>4D.>BB.>0A.>17.>BB.>0B.>BB.>66.>0C.>08.>BB.>0D.>00.>BB.>0E.>BC.>BC.>02.>6C.>BC.>03.>02.>F7.-
````

