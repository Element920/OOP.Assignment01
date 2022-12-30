 #### Authors:

 Israel Mor - (https://github.com/Element920
 Netanel Baruch - (https://github.com/Netanelbruch)
 
 
 # OOP.Assignment1

    This project implements the Observer pattern in Java, using a modified version of   StringBuilder.
    We used the ConcreteMember and GroupAdmin classes in order to do so.

### the projects was written on openjdk-19.0.1

## class - GroupAdmin:

    This is a Java class that implements the Sender interface and represents a group administrator who is responsible for
    maintaining a list of members and a message that is shared with all the members of the group.
    The group administrator maintains an instance of the UndoableStringBuilder class, which represents a string that
    can be modified using various methods such as insert, append, and delete, and also allows undoing the most recent modification
    using the undo method.
  

### class ConcreteMember:

    The ConcreteMember class is an implementation of the Member interface. It has three constructors: one that takes
     an UndoableStringBuilder object as an argument, one that takes a Sender object as an argument, and one that takes both a
     Sender and an UndoableStringBuilder object as arguments.


## Interfaces: 

    Sender and Member.


#### Tests:

    the folder "test" contains the test we used to make sure our program runs as it should the tests for the .java files consists of testing the code's capabilities for registering, unregistering, group creating and member notification. 

