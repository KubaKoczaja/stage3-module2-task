package com.mjc.school.view.command;

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
