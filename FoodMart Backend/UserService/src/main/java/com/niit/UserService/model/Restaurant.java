package com.niit.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    private String restaurantName;

    private String location;
    private String resImage;
    private Set<Dishes> dishesSet;

}
