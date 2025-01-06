package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.dto.response.Sender;
import com.example.PandaCoffee.dto.response.To;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private Sender sender;
    private List<To> to;
    private String htmlContent;
    private String subject;
}
