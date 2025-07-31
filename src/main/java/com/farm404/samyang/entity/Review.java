package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {
    private Integer ReviewID;
    private Integer UserID;
    private Integer CropID;
    private Integer Rating;
    private String Title;
    private String Content;
    
    // 화면 표시용 필드 (DB에는 저장되지 않음)
    private transient String userName;
    private transient String cropName;
    
    // MVC 컨트롤러에서 사용하는 필드명과 매핑하기 위한 getter/setter
    public Integer getReviewID() {
        return ReviewID;
    }
    
    public void setReviewID(Integer reviewID) {
        ReviewID = reviewID;
    }
}