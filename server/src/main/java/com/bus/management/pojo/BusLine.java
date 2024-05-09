package com.bus.management.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusLine {
    @NotNull
    private Integer id;
    private String city;
    private String startstation;
    private String endstation;
    private String routesite;
    private String busid;
    private String runtime;
    private String price;

}
