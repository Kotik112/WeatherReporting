# WeatherReporting

A simple spring boot project to where multiple clients can connect to a server to update the temperature for a given city.

Note: Make sure to run the server application before starting the client (Weather Reporter) or the application will not run as intended.


## The server:
The server creates a Datagram socket that multiple "weather reporting" clients can connect to and update the temperature.
The server is just a textAreaField that displays the temperature string that the clients send to the server.

## The client:
The client application first asks for your location. Upon being provided with the location the actual client application boots up.
Now you can enter the current temperature to update the server.

Example output from server with two active client connections:

Stockholm, 15
London, 22

Arman
IoT Nackademin
