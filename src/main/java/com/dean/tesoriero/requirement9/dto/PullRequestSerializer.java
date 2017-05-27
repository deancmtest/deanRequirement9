package com.dean.tesoriero.requirement9.dto;

import org.eclipse.egit.github.core.PullRequest;

/**
 * Created by deant on 27/05/2017.
 */
public class PullRequestSerializer {

    public static PullRequestDto toDto(PullRequest request) {
        PullRequestDto dto = new PullRequestDto();
        dto.number = request.getNumber();
        dto.author = request.getUser().getLogin();
        dto.title = request.getTitle();
        dto.openDate = request.getCreatedAt();
        dto.closedDate = request.getClosedAt();
        return dto;
    }

}
