package pages;

import java.net.http.HttpResponse;

import lombok.Getter;

public class Discussion {
	@Getter
	private String body;
	@Getter
	private String title;

	public static DiscussionBuilder with() {
		return null;
	}

	public static class DiscussionBuilder {

		public DiscussionBuilder body(String body) {
			return null;
		}
	}
}
