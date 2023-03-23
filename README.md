# src explained

## managers (control)
Implement different functions for the program;
Managers act as controllers, handling all the main logic of the application.

## models (entity)
All classes, with set and get functions;
Models are entities which store various information. Models are being created as objects and accessed
by managers. Models will retrieve the relevant data from Serializers, which handle the permanent data
storage from our CSV.

## programs (boundaries)
Contains all the User Interfaces, will call function in managers;
The program package contains all the applications that manage the information between user and
managers. FYPMSApp is the main user interface for our application.

## serializer
Handle database, I/O;
Serializers manage the databases. This allows for efficient and secure storage as information will be
permanent and not be erased upon termination of the application.

Contains one main InterfaceSerializer; all the child serializers will implement the above interface.   

## tests
Contain all the test files  