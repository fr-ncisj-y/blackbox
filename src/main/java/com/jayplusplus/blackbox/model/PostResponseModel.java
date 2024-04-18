package com.jayplusplus.blackbox.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseModel {
    private Integer postId;
    private String content;
    private Date timestamp;
    private String title;
}
