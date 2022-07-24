package io.adnanedu.ppmtool.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date start_date;
    private Date end_date;
}
