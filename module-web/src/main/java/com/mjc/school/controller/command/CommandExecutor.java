package com.mjc.school.controller.command;

import com.mjc.school.controller.command.annotation.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandExecutor {
		@CommandHandler
		public void executeCommand(Command command) {
				command.execute();
		}
}
