package org.example.ai_crypto_advisor.votes;

import jakarta.transaction.Transactional;
import org.example.ai_crypto_advisor.dashboard.DashboardContent;
import org.example.ai_crypto_advisor.dashboard.DashboardContentRepository;
import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserRepository;
import org.example.ai_crypto_advisor.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ContentVoteServiceImpl implements ContentVoteService {
    @Autowired
    private ContentVoteRepository contentVoteRepository;
    @Autowired
    private DashboardContentRepository dashboardContentRepository;
    @Autowired
    private UserServiceImpl userService;


    @Override
    public boolean voteOnContent(int userId, int contentId, VoteType voteType) {
        try{
            DashboardContent content = dashboardContentRepository.findById(contentId).orElseThrow(
                    ()-> new RuntimeException("content not found"));

            ContentVote existingVote = contentVoteRepository.findByUserIdAndDashboardContentId(userId, contentId);
            if (existingVote != null) {
                existingVote.setVoteType(voteType);
                contentVoteRepository.save(existingVote);
            }else {
                User user = userService.getUserById(userId);
                DashboardContent dashboardContent = dashboardContentRepository.findById(contentId).orElseThrow();
                ContentVote newVote = new ContentVote(user, dashboardContent, voteType);
                contentVoteRepository.save(newVote);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
