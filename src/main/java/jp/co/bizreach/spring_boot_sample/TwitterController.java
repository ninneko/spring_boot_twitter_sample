package jp.co.bizreach.spring_boot_sample;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * @author bizreach.Inc
 */
@Controller
@RequestMapping("")
public class TwitterController {

	@Autowired
	private HttpSession session;

	@RequestMapping("mypage")
	public String mypage(Model model) throws TwitterException {
		Twitter twitter = createTwitter();
		ResponseList<Status> timeLineList = twitter.getHomeTimeline();
		User user = twitter.verifyCredentials();
		model.addAttribute("user", user);
		model.addAttribute("timelineList", timeLineList);
		model.addAttribute("text", timeLineList.get(0).getText());
		return "mypage";
	}

	@RequestMapping("doTweet")
	public String doTweet(@RequestParam(value = "tweet", required = true) String name) throws TwitterException {
		Twitter twitter = createTwitter();
		twitter.updateStatus(name);
		return "redirect:/mypage";
	}

	private Twitter createTwitter() {
		AccessToken accessToken = (AccessToken) session.getAttribute("accessToken");
		TwitterFactory factory = new TwitterFactory();
		return factory.getInstance(accessToken);
	}
}
