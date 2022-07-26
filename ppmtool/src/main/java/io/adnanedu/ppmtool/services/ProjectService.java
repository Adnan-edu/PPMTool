package io.adnanedu.ppmtool.services;

import io.adnanedu.ppmtool.domain.Backlog;
import io.adnanedu.ppmtool.domain.Project;
import io.adnanedu.ppmtool.dto.ProjectDto;
import io.adnanedu.ppmtool.exceptions.ProjectIdException;
import io.adnanedu.ppmtool.repositories.BacklogRepository;
import io.adnanedu.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    BacklogRepository backlogRepository;
    public Project saveOrUpdateProject(ProjectDto projectDto){
        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findBacklogByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }
    public ProjectDto findProjectByIdentifier(String projectId){
        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");
        }
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project, projectDto);
        return projectDto;
    }
    public Iterable<ProjectDto> findAllProjects(){
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project: projects){
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(project, projectDto);
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }
    public void deleteProjectByIdentifier(String projectid){
        Project project = projectRepository.findProjectByProjectIdentifier(projectid.toUpperCase());
        if(project == null){
            throw  new  ProjectIdException("Cannot Project with ID '"+projectid+"'. This project does not exist");
        }
        projectRepository.delete(project);
    }
}
