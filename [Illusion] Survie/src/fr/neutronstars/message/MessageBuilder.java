package fr.neutronstars.message;

/**
* @author NeutronStars
*/
public final class MessageBuilder extends AbstractMessageBuilder<MessageBuilder>{

	public MessageBuilder(AbstractMessageBuilder<?> messageBuilder){
		super(messageBuilder);
	}
	
	public MessageBuilder(String text){
		super(text);
	}

	public MessageBuilder clone() {
		return new MessageBuilder(this);
	}
	
}
