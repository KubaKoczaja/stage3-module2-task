package com.mjc.school.view.command;

import com.mjc.school.view.command.annotation.CommandHandler;
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
