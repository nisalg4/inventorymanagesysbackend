
package com.example.springsecuritymongo.model;

        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssetAssignment {
    String employee;
    String asset;

    public AssetAssignment(String employee, String asset) {
        this.employee = employee;
        this.asset = asset;
    }
}