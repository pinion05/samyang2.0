package com.farm404.samyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FarmingDiary extends BaseEntity {
    private Integer DiaryID;
    private Integer UserID;
    private Integer CropID;
    private String Title;
    private String Content;
    private String WeatherCondition;
    private BigDecimal Temperature;
    private BigDecimal Humidity;
    private LocalDate WorkDate;
    
    // 화면 표시용 필드 (DB에는 저장되지 않음)
    private transient String userName;
    private transient String cropName;
    
    // MVC 컨트롤러에서 사용하는 필드명과 매핑하기 위한 getter/setter
    public Integer getDiaryID() {
        return DiaryID;
    }
    
    public void setDiaryID(Integer diaryID) {
        DiaryID = diaryID;
    }
    
    public String getWeather() {
        return WeatherCondition;
    }
    
    public void setWeather(String weather) {
        WeatherCondition = weather;
    }
    
    public String getWorkDescription() {
        return Content;
    }
    
    public void setWorkDescription(String workDescription) {
        Content = workDescription;
    }
    
    public String getNotes() {
        return Title;
    }
    
    public void setNotes(String notes) {
        Title = notes;
    }
    
    public String getImageURL() {
        // 임시로 빈 문자열 반환
        return "";
    }
    
    public void setImageURL(String imageURL) {
        // 이미지 URL은 현재 DB에 저장하지 않음
    }
}