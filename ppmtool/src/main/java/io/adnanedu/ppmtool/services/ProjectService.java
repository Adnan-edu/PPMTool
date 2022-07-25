package io.adnanedu.ppmtool.services;

import io.adnanedu.ppmtool.domain.Project;
import io.adnanedu.ppmtool.dto.ProjectDto;
import io.adnanedu.ppmtool.exceptions.ProjectIdException;
import io.adnanedu.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    public Project saveOrUpdateProject(ProjectDto projectDto){
        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }
}
