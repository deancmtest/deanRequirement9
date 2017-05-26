/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.service;

import java.io.IOException;
import java.util.List;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;

/**
 * @author Dean Tesoriero
 */
public interface GithubApiService {
  List<GHPullRequest> getAllPullRequests(GHIssueState state) throws IOException;
}
