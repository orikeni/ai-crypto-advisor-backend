package org.example.ai_crypto_advisor.votes;

import java.util.Map;

public interface ContentVoteService {
    boolean voteOnContent(int userId, int contentId, VoteType voteType);
}
