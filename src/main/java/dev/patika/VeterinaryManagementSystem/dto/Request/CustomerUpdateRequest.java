package dev.patika.VeterinaryManagementSystem.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID Değeri pozitif olmak zorunda")
    private long id;
    @NotNull(message = "Müşteri ismi boş veya null olamaz")
    private String name;
    private String phone;
    @Email
    private String mail;
    private String address;
    private String city;

}
