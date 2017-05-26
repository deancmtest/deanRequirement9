/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.service;

import java.io.IOException;
import java.util.List;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

/**
 * @author Dean Tesoriero
 */
@Service
public class GithubApiServiceImpl implements GithubApiService{

  @Override
  public List<GHPullRequest> getAllPullRequests(GHIssueState state) throws IOException {
    GitHub github = GitHub.connectAnonymously();
    return github.getRepository("deancmtest/deanRequirement9").getPullRequests(GHIssueState.CLOSED);

  }
}
