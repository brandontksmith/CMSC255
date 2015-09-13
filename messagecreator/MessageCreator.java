//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-6
//
// Description: This program tests the Message class by making a message and printing it.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package messagecreator;

public class MessageCreator {

    public static void main(String[] args) {
        
        Message email = new Message("Brandon Smith", "Austen Smith");
        
        email.append("Hello,");
        email.append("This is our last programming project for CMSC 255!");
        email.append("Bye!");
        
        String message = email.toString();
        System.out.println(message);
    }
}