package fr.neutronstars.message;

import java.util.List;

import com.google.common.collect.Lists;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

/**
 * @author Neutron_Stars
 * @param <T>
 */

@SuppressWarnings("unchecked")
abstract class AbstractMessageBuilder<T extends AbstractMessageBuilder<T>>{

	private final List<BaseComponent> baseComponents = Lists.newArrayList();
	private TextComponent textComponent;
	
	protected AbstractMessageBuilder(AbstractMessageBuilder<?> messageBuilder){
		this.baseComponents.addAll(messageBuilder.baseComponents);
		this.textComponent = (TextComponent) messageBuilder.textComponent.duplicate();
	}
	
	protected AbstractMessageBuilder(String text){
		textComponent = new TextComponent(text);
	}
	
	/**
	 * Add text after the last text.
	 * @param text
	 * @return the class
	 */
	public T next(String text){
		baseComponents.add(textComponent);
		textComponent = new TextComponent(text);
		return (T)this;
	}
	
	/**
	 * Add text in new line after the last text.
	 * @param text
	 * @return the class.
	 */
	public T nextln(String text){
		return this.next("\n"+text);
	}
	
	/**
	 * Add a event click to the text. (URL, command, suggest command, file and change page in the book.)
	 * @param action
	 * @param value
	 * @return the class.
	 */
	public T click(Action action, String value){
		textComponent.setClickEvent(new ClickEvent(action, value));
		return (T) this;
	}
	
	/**
	 * Add a event click to the text. (URL, command, suggest command, file and change page in the book.)
	 * @param index
	 * @return the class.
	 */
	public T click(int index){
		if(baseComponents.size() > index && index > -1)
		textComponent.setClickEvent(baseComponents.get(index).getClickEvent());
		return (T) this;
	}
	
	/**
	 * Add a text above the text.
	 * @param text
	 * @return the class.
	 */
	public T setHover(String text){
		return this.setHover(new TextComponent(text));
	}
	
	/**
	 * Add a text above the text.
	 * @param text
	 * @return the class.
	 */
	public T setHover(TextComponent text){
		textComponent.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{text}));
		return (T) this;
	}
	
	/**
	 * Add a text above the text.
	 * @param index
	 * @return the class.
	 */
	public T setHover(int index){
		if(baseComponents.size() > index && index > -1)
			textComponent.setHoverEvent(baseComponents.get(index).getHoverEvent());
		return (T)this;
	}
	
	/**
	 * Built to prepare to send it to the player.
	 * @return BaseComponent[]
	 */
	public BaseComponent[] build(){
		BaseComponent[] result = this.baseComponents.toArray(new BaseComponent[this.baseComponents.size() + 1]);
		result[this.baseComponents.size()] = this.textComponent;
		return result;
	}
	
	/**
	 * Transform to BukkitMessageBuilder
	 * return {@link BukkitMessageBuilder}
	 */
	public BukkitMessageBuilder toBukkitMessageBuilder(){
		return new BukkitMessageBuilder(this);
	}
	
	/**
	 * Create a new instance of the class.
	 * @return this
	 */
	public abstract T clone();
}
