# Prefix & Infix Calculator App

Small app that exposes an HTTP endpoint to run calculations with prefix & infix notations.
 
## How to build & run?
### Dependencies
Docker, JRE11. 

Project comes with a Dockerfile & a makefile that lets you run: 

`make build` to build the app

`make run` to run the app which will run a Docker container listening to port 8080

### Sample requests
You can use curl to send requests:

```curl -i -X POST -H "Content-Type: application/json" --data '{"expression":"- / 10 + 1 1 * 1 2"}' http://localhost:8080/calculation/prefix```

## Few notes
I've build the app as a web service from scratch to cover all the requirements including the bonuses.
Also decided to expose the endpoints with the method POST instead of a GET to let the testing become
 easier on your part (not relying on encoding the URL special characters like '/') and imagining perhaps this service _creating/storing_ 
calculations on a real world scenario.
