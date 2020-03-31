import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import pages.TodoMvc;
import support.Browser;
import support.BrowserParameterResolver;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Managing Todos")
@ExtendWith(BrowserParameterResolver.class)
class TodoMvcTests {

	private TodoMvc todoMvc;

	@BeforeEach
	void beforeEach(Browser browser) {
		todoMvc = new TodoMvc(browser).
				navigateTo();
	}

	@Test
	@DisplayName("Creates Todo with given name")
	void createsTodo() {
		String todoName = randomTodoName();
		todoMvc.createTodo(todoName);
		assertEquals(1, todoMvc.getTodosLeft());
		assertTrue(todoMvc.todoExists(todoName));
	}

	@Test
	@DisplayName("Edits inline double-clicked Todo")
	void editsTodo() {
		String todoName = randomTodoName();
		String newTodoName = randomTodoName();
		todoMvc.createTodo(todoName).
				renameTodo(todoName, newTodoName);
		assertTrue(todoMvc.todoExists(newTodoName));
	}

	@Test
	@DisplayName("Removes selected Todo")
	void removesTodo() {
		String todoName = randomTodoName();
		todoMvc.createTodo(todoName).
				removeTodo(todoName);
		assertFalse(todoMvc.todoExists(todoName));
	}

	@Test
	@DisplayName("Toggles selected Todo as completed")
	void togglesTodoCompleted() {
		String todoName = randomTodoName();
		todoMvc.createTodos(todoName, randomTodoName());

		todoMvc.completeTodo(todoName);
		assertEquals(1, todoMvc.getTodosLeft());

		todoMvc.showCompleted();
		assertEquals(1, todoMvc.getTodoCount());

		todoMvc.showActive();
		assertEquals(1, todoMvc.getTodoCount());
	}

	@Test
	@DisplayName("Toggles all Todos as completed")
	void togglesAllTodosCompleted() {
		todoMvc.createTodos(randomTodoName(), randomTodoName());

		todoMvc.completeAllTodos();
		assertEquals(0, todoMvc.getTodosLeft());

		todoMvc.showCompleted();
		assertEquals(2, todoMvc.getTodoCount());

		todoMvc.showActive();
		assertEquals(0, todoMvc.getTodoCount());
	}

	@Test
	@DisplayName("Clears all completed Todos")
	void clearsCompletedTodos() {
		todoMvc.createTodos(randomTodoName(), randomTodoName());
		todoMvc.completeAllTodos();
		todoMvc.createTodo(randomTodoName());

		todoMvc.clearCompleted();
		assertEquals(1, todoMvc.getTodosLeft());

		todoMvc.showCompleted();
		assertEquals(0, todoMvc.getTodoCount());

		todoMvc.showActive();
		assertEquals(1, todoMvc.getTodoCount());
	}

	@Test
	@DisplayName("Creates Todos all with the same name")
	void createsTodosWithSameName() {
		String todoName = randomTodoName();
		todoMvc.createTodos(todoName, todoName, todoName);
		assertEquals(3, todoMvc.getTodosLeft());

		todoMvc.showActive();
		assertEquals(3, todoMvc.getTodoCount());
	}

	private String randomTodoName() {
		return "My Todo " + UUID.randomUUID().toString();
	}
}
