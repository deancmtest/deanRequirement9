/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.service;

import com.dean.tesoriero.requirement9.dto.PullRequestDto;
import java.io.IOException;
import java.util.List;

/**
 * @author Dean Tesoriero
 */
public interface GithubApiService {
  List<PullRequestDto> getAllPullRequests(String state) throws IOException;


  void mergeRequest(int pullId) throws IOException;
}
