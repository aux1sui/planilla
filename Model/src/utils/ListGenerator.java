package utils;

import entities.Curso;
import entities.Estudiante;

import java.util.Arrays;
import java.util.List;

public class ListGenerator {
    public static List<Estudiante.TipoIdentificacion> getTipoIdentificacionEstudiante(){
        return Arrays.asList(Estudiante.TipoIdentificacion.CC,Estudiante.TipoIdentificacion.CE,Estudiante.TipoIdentificacion.TI);
    }
    
    public static List<Estudiante.Genero> getGeneroEstudiante(){
        return Arrays.asList(Estudiante.Genero.M,Estudiante.Genero.F);
    }
    
    public static List<Estudiante.GrupoSanguineo> getGrupoSanguineoEstudiante(){
        return Arrays.asList(Estudiante.GrupoSanguineo.A_NEGATIVO,Estudiante.GrupoSanguineo.A_POSITIVO,Estudiante.GrupoSanguineo.B_NEGATIVO,Estudiante.GrupoSanguineo.B_POSITIVO,Estudiante.GrupoSanguineo.O_NEGATIVO,Estudiante.GrupoSanguineo.O_POSITIVO);
    }
    public static List<Curso.Modalidad> getModalidadCurso(){
        return Arrays.asList(Curso.Modalidad.PRESENCIAL,Curso.Modalidad.VIRTUAL);
    }

}
