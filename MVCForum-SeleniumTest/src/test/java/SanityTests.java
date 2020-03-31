import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import net.bytebuddy.implementation.bytecode.Throw;
import pages.Discussion;
import pages.DiscussionHeader;
import pages.MVCForumClient;
import support.Browser;
import support.BrowserParameterResolver;
import support.LoggedInUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Sanity tests")
@ExtendWith(BrowserParameterResolver.class)
public class SanityTests {

	@Test
	public void WhenARegisterUserStartsDiscussionOtherAnonymousUserCanSeeIt(Browser browser) {
		String body = "dummy body";
		MVCForumClient mvcForum = new MVCForumClient(browser).
				navigateTo();
		LoggedInUser userA = mvcForum.RegisterNewUserAndLogIn();
		Discussion createdDiscussion = userA.createDiscussion(Discussion.with().body(body));

		MVCForumClient anonymousUser = new MVCForumClient(browser);
		DiscussionHeader latestHeader = anonymousUser.latestDiscussions().top();
		assertEquals("lolek", createdDiscussion.getTitle(), latestHeader.getTitle());
		Discussion viewedDiscussion = latestHeader.openDiscussion();
		assertEquals("dsds", body, viewedDiscussion.getBody());
	}
}
