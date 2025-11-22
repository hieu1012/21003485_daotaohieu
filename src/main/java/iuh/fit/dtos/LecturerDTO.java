package iuh.fit.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LecturerDTO {

    private Integer id;

    @NotBlank(message = "Mã giảng viên không được để trống")
    private String lecturerCode;

    @NotBlank(message = "Tên giảng viên không được để trống")
    private String lecturerName;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String department;
}