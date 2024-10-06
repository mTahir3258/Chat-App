package enginneer.tahir.messagingapplication

import android.os.Message

class MessageModel {
    var message : String?= null
    var senderId : String?= null
    constructor(){}
    constructor(message: String?,senderId : String?)
    {
        this.message = message
        this.senderId = senderId
    }
}