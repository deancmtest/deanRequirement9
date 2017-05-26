/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.controller;

import com.dean.tesoriero.requirement9.service.GithubApiService;
import java.io.IOException;
import java.util.List;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dean Tesoriero
 */
@RestController
@RequestMapping("/api")
public class ApiController {

  @Autowired
  GithubApiService mApiService;

  @RequestMapping(path = "/pulls", method = RequestMethod.GET)
  public List<GHPullRequest> getAllPullRequests(@RequestParam("state") String state) throws IOException {
    return mApiService.getAllPullRequests(GHIssueState.ALL);
  }

}
