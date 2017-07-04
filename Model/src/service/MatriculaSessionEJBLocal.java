package service;

import entities.Matricula;

import javax.ejb.Local;

@Local
public interface MatriculaSessionEJBLocal {
    Matricula persistMatricula(Matricula matricula);

    Matricula mergeMatricula(Matricula matricula);

    void removeMatricula(Matricula matricula);
}
