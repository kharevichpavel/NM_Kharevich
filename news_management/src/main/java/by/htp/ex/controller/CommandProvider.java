package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.AddNews;
import by.htp.ex.controller.impl.DeleteNews;
import by.htp.ex.controller.impl.DoEditNews;
import by.htp.ex.controller.impl.DoLocal;
import by.htp.ex.controller.impl.DoRegistration;
import by.htp.ex.controller.impl.DoSignIn;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.GoToAddNews;
import by.htp.ex.controller.impl.GoToBasePage;
import by.htp.ex.controller.impl.GoToEditNews;
import by.htp.ex.controller.impl.GoToErrorPage;
import by.htp.ex.controller.impl.GoToNewsList;
import by.htp.ex.controller.impl.GoToRegistrationPageCommand;
import by.htp.ex.controller.impl.GoToViewNews;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {

		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.DO_LOCAL, new DoLocal());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
	}

	public Command getCommand(String name) {

		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}

}
