package com.brzyang.netty.im.command;

import com.brzyang.netty.im.handler.ImLoginRequestHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand{
    private Map<String, ConsoleCommand> consoleCommandMap;

    private static Logger logger = LoggerFactory.getLogger(ConsoleCommandManager.class);

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        //  获取第一个指令
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            logger.error("unknown command:{} try again!", command);
            for (Entry<String, ConsoleCommand> commandEntry : consoleCommandMap.entrySet()) {
                logger.info("command:{}", commandEntry.getKey());
            }
        }
    }
}
