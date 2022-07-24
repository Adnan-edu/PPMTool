package io.adnanedu.ppmtool.web;

import io.adnanedu.ppmtool.domain.Project;
import io.adnanedu.ppmtool.dto.ProjectDto;
import io.adnanedu.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody ProjectDto projectDto){
        Project project = projectService.saveOrUpdateProject(projectDto);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
