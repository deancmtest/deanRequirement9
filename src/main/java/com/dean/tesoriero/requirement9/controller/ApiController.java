/*
 * Copyright (c) 2013-2014 Allianz Australia Ltd. (Allianz) All Rights Reserved.
 * This work is a trade secret of Allianz and unauthorized use or copying is prohibited.
 */
package com.dean.tesoriero.requirement9.controller;

import com.dean.tesoriero.requirement9.dto.PullRequestDto;
import com.dean.tesoriero.requirement9.service.GithubApiService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<List<PullRequestDto>> getAllPullRequests(@RequestParam("state") String state) throws IOException {
    if(state == null)
      return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return new ResponseEntity(mApiService.getAllPullRequests(state), HttpStatus.OK);
  }

  @RequestMapping(path = "/pulls/{pullId}", method = RequestMethod.POST)
  public ResponseEntity<List<PullRequestDto>> mergeRequests(@PathVariable int pullId) throws IOException {
    if(pullId == 0)
      return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    mApiService.mergeRequest(pullId);
    return new ResponseEntity(HttpStatus.OK);
  }

}
