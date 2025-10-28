package org.example.ai_crypto_advisor.votes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class ContentVoteController {

    @Autowired
    private ContentVoteService contentVoteService;

    @PostMapping
    public String vote(
            @RequestParam int userId,
            @RequestParam int contentId,
            @RequestParam VoteType voteType) {

        boolean success = contentVoteService.voteOnContent(userId, contentId, voteType);
        return success ? "Vote recorded successfully" : "Vote failed";
    }
}
