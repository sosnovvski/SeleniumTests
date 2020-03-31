package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import support.Browser;

public class TodoMvc {

	private Browser browser;

	public TodoMvc(Browser browser) {
		this.browser = browser;
	}

	public TodoMvc navigateTo() {
		browser.open("http://todomvc.com/examples/vanillajs");
		return this;
	}

	public TodoMvc createTodo(String todoName) {
		browser.type(By.className("new-todo"), todoName + Keys.ENTER);
		return this;
	}

	public void createTodos(String... todoNames) {
		for (String todoName : todoNames) {
			createTodo(todoName);
		}
	}

	public int getTodosLeft() {
		return Integer.parseInt(browser.find(By.cssSelector(".todo-count > strong")).getText());
	}

	public boolean todoExists(String todoName) {
		return getTodos().stream().anyMatch(todoName::equals);
	}

	public int getTodoCount() {
		return getTodoElements().size();
	}

	public List<String> getTodos() {
		return getTodoElements().stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public TodoMvc renameTodo(String todoName, String newTodoName) {
		WebElement todoToEdit = getTodoElementByName(todoName);
		browser.doubleClick(todoToEdit);

		WebElement todoEditInput = browser.find(By.cssSelector("input.edit"), todoToEdit);
		browser.executeScript("arguments[0].value = ''", todoEditInput);

		browser.type(todoEditInput, newTodoName + Keys.ENTER);
		return this;
	}

	public TodoMvc removeTodo(String todoName) {
		WebElement todoToRemove = getTodoElementByName(todoName);
		browser.moveToElement(todoToRemove);
		browser.click(By.cssSelector("button.destroy"), todoToRemove);
		return this;
	}

	public void completeTodo(String todoName) {
		WebElement todoToComplete = getTodoElementByName(todoName);
		browser.click(By.cssSelector("input.toggle"), todoToComplete);
	}

	public void completeAllTodos() {
		browser.click(By.className("toggle-all"));
	}

	public void showActive() {
		browser.click(By.cssSelector("a[href='#/active']"));
	}

	public void showCompleted() {
		browser.click(By.cssSelector("a[href='#/completed']"));
	}

	public void clearCompleted() {
		browser.click(By.className("clear-completed"));
	}

	private List<WebElement> getTodoElements() {
		return browser.findAll(By.cssSelector(".todo-list li"));
	}

	private WebElement getTodoElementByName(String todoName) {
		return getTodoElements().stream().filter(el -> todoName.equals(el.getText())).findFirst()
				.orElseThrow(() -> new AssertionError("Test data missing!"));
	}
}
