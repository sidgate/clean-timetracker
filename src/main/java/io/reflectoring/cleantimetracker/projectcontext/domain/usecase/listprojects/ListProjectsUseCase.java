package io.reflectoring.cleantimetracker.projectcontext.domain.usecase.listprojects;

import io.reflectoring.cleantimetracker.projectcontext.domain.entity.Project;
import io.reflectoring.cleantimetracker.projectcontext.domain.port.out.persistence.QueryProjectsPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProjectsUseCase {

	private QueryProjectsPort queryPort;

	public ListProjectsUseCase(QueryProjectsPort queryPort) {
		this.queryPort = queryPort;
	}

	public List<Project> listProjects() {
		return queryPort.listProjects();
	}

}
