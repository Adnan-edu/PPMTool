package io.adnanedu.ppmtool.web;

import io.adnanedu.ppmtool.domain.Project;
import io.adnanedu.ppmtool.dto.ProjectDto;
import io.adnanedu.ppmtool.services.MapValidationErrorService;
import io.adnanedu.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody ProjectDto projectDto, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap!=null)
            return errorMap;
        Project project = projectService.saveOrUpdateProject(projectDto);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
