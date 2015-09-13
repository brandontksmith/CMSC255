//////////////////////////////////////////////////////////////////////////////////////////////
// CMSC-255 Fall 2014
//
// Name: Brandon Smith
// Lab Section number: 2
// (Project/Example): Project-6
//
// Description: This class resembles a Message that contains a sender, recipient, and body.
//
/////////////////////////////////////////////////////////////////////////////////////////////

package messagecreator;

public class Message {
    
    private String sender;
    private String recipient;
    private String body;
    
    /**
     * Constructs a Message with a sender, recipient, and empty body.
     * 
     * @param sender name of the person sending the message
     * @param recipient name of the person receiving the message
     */
    public Message(String sender, String recipient) {
        
        this.sender = sender;
        this.recipient= recipient;
        this.body = "";
    }
    
    /**
     * Returns the sender of a Message.
     * 
     * @return the sender
     */
    public String getSender() {
        
        return sender;
    }
    
    /**
     * Returns the recipient of a Message.
     * 
     * @return the recipient
     */
    public String getRecipient() {
        
        return recipient;
    }
    
    /**
     * Returns the body of a Message.
     * 
     * @return the body
     */
    public String getBody() {
        
        return body;
    }
    
    /**
     * Appends a line of text to the message body.
     * 
     * @param line a line of text
     */
    public void append(String line) {
        
        body += line + "\n";
    }
    
    /**
     * Returns the message, including the sender, recipient, and the
     * body, as one long String.
     * 
     * @return the message
     */
    @Override
    public String toString() {
        
        String message;
        
        message = "From: " + sender + "\n";
        message += "To: " + recipient + "\n";
        message += body;
        
        return message;
    }
}