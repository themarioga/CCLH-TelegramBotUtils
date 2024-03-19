package org.themarioga.bot.util;

import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.themarioga.bot.model.CallbackQuery;
import org.themarioga.bot.model.Command;

import java.util.Arrays;

public class BotMessageUtils {

    private BotMessageUtils() {
        throw new UnsupportedOperationException();
    }

    public static Command getCommandFromMessage(String message) {
        String[] receivedMessage = message.split(" ");
        Command command = new Command();
        command.setCommand(receivedMessage[0]);
        command.setCommandData(receivedMessage.length > 1 ? String.join(" ", Arrays.copyOfRange(receivedMessage, 1, receivedMessage.length)) : null);

        return command;
    }

    public static CallbackQuery getCallbackQueryFromMessageQuery(String messageQuery) {
        String[] receivedQuery = messageQuery.split("__");

        CallbackQuery callbackQuery = new CallbackQuery();
        callbackQuery.setQuery(receivedQuery[0]);
        callbackQuery.setQueryData(receivedQuery.length > 1 ? receivedQuery[1] : null);

        return callbackQuery;
    }

    public static String getUserInfo(User user) {
        String output = String.valueOf(user.getId());
        if (StringUtils.hasText(user.getFirstName()) || StringUtils.hasText(user.getLastName()) || StringUtils.hasText(user.getUserName())) {
            output += " [" + getUsername(user) + "]";
        }

        return output;
    }

    public static String getUsername(User user) {
        String output = "";
        if (StringUtils.hasText(user.getFirstName())) output += user.getFirstName();
        if (StringUtils.hasText(user.getLastName())) output += " " + user.getLastName();
        if (StringUtils.hasText(user.getUserName())) output += " (@" + user.getUserName() + ")";
        return output;
    }

    public static String getUsername(Chat chat) {
        String output = "";
        if (StringUtils.hasText(chat.getFirstName())) output += chat.getFirstName();
        if (StringUtils.hasText(chat.getLastName())) output += " " + chat.getLastName();
        if (StringUtils.hasText(chat.getUserName())) output += " (@" + chat.getUserName() + ")";
        return output;
    }

}
