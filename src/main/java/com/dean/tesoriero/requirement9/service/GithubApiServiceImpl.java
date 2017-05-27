/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.service;

import com.dean.tesoriero.requirement9.dto.PullRequestDto;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.dean.tesoriero.requirement9.dto.PullRequestSerializer;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dean Tesoriero
 */
@Service
public class GithubApiServiceImpl implements GithubApiService{

  @Value("${github_token}")
  String githubToken;

  public List<PullRequestDto> getAllPullRequests(String state) throws IOException {
    PullRequestService service = getPullRequestService();
    RepositoryId repo = getRepositoryId();

    return service.getPullRequests(repo, state)
                  .stream()
                  .map(PullRequestSerializer::toDto)
                  .collect(Collectors.toList());
  }

  @Override
  public void mergeRequest(int pullId) throws IOException {
    PullRequestService service = getPullRequestService();
    RepositoryId repo = getRepositoryId();
    
    if (service.getPullRequest(repo, pullId).isMergeable())
      service.merge(repo, pullId, "merging a pull request #" + pullId);
  }

  private RepositoryId getRepositoryId() {
    return new RepositoryId("deancmtest", "deanRequirement9");
  }

  private PullRequestService getPullRequestService() {
    PullRequestService service = new PullRequestService();
    service.getClient().setOAuth2Token(githubToken);
    return service;
  }


}
