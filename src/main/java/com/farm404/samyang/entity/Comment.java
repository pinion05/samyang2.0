package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {
    private Integer CommentID;
    private Integer PostID;
    private Integer UserID;
    private String Content;
}