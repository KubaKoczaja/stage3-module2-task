package com.mjc.school.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandExecutor {
		public void executeCommand(Command command) {
				command.execute();
		}
}
