package techsays.in;

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private String messageTime;
   // private  String photo;
    private  String photo1;
    private  String photo;
    private  String id;
    private  String idd;
    private  String stamp;
    private  String s;
    private  String email;

    public ChatMessage(String messageText, String messageUser, String photo, String photo1, String s, String id, String idd, String stamp, String email) {
        this.messageText = messageText;
        this.messageUser = messageUser;

this.photo1=photo1;
     this.photo=photo;

        // Initialize to current time
       this. messageTime = messageTime;
        this.s=s;
        this.id=id;
        this.idd=idd;
        this.email=email;

        this.stamp=stamp;

    }



    public ChatMessage() {
    }

    public String s()
    {
        return  s;
    }
    public String getEmail()
    {
        return  email;
    }
    public String getStamp()
    {
        return  stamp;
    }

    public String getId()
    {
        return  id;
    }
    public String getIdd()
    {
        return  idd;
    }

    public String getPhoto()
{
    return  photo;
}
    public String getPhoto1()
    {
        return  photo1;
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }


//    public String getPhoto1() {
//        return photo1;
//    }
//
//    public void setPhoto1(String photo1) {
//        this.photo1 = photo1;
//    }
}