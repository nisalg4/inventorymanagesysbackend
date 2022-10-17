package com.example.springsecuritymongo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssetAssignmentResponse {
    String employee;
    String asset;

    public AssetAssignmentResponse(String employee, String asset) {
        this.employee = employee;
        this.asset = asset;
    }
}
