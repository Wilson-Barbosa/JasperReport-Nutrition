package com.jasper.nutriotiontable.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Nutrition {
    
    // These are the fields that will be rendered in my JasperReport's Table
    // Each instante of NutritionClass will be a row in my table
    private String nutriotionName;
    private Integer total;
    private Integer goal;
    private String metric;

}
