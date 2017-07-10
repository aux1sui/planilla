package backing;

import entities.Curso;
import entities.Estudiante;

import entities.Matricula;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.apache.log4j.Logger;

import utils.ListGenerator;

@ManagedBean(name = "admRegistroBean")
@SessionScoped
public class AdmRegistroBean implements Serializable{
    
    @SuppressWarnings("compatibility")
        //Fields
    private static final long serialVersionUID = 8336114230726923893L;
    static final Logger logger = Logger.getLogger(AdmRegistroBean.class);
    
    
    
    private String datePatternInput = "dd/MM/yyyy";
    
    @ManagedProperty(value = "#{planillaDelegate}")
    private DelegatePlanilla planillaDelegate;
    
    
    //
    private List<Estudiante.TipoIdentificacion> tipoIdentificacionEstudianteList;
    private List<Estudiante.Genero> generoEstudianteList;
    private List<Estudiante.GrupoSanguineo> grupoSanguineoEstudianteList;
    private List<Estudiante> estudianteList;
    private List<Estudiante> estudianteListMatricula;
    private List<Estudiante> estudianteListTabla;
    //
    private List<Curso.Modalidad> modalidadList;
    private List<Curso> cursoList;
    private List<Estudiante> cursoMatriculaList;
    private List<Matricula> matriculaList;

    //
    private Curso curso;
    //
    private Estudiante estudiante;
    //
    private Matricula matricula;
    
    
    //
    private Estudiante.TipoIdentificacion paramTipoIdentificacion;
    private String paramNumeroIdentificacion;
    private String paramNombres;
    private String paramApellidos;
    //
    private String paramNombreCurso;
    private String paramCodigo;

    private Curso.Modalidad paramModalidad;
    
    private String page = "index.xhtml";
    //Fin Fields
    
    private void preparateGoEstudiante(){
        
        this.tipoIdentificacionEstudianteList =  ListGenerator.getTipoIdentificacionEstudiante();
        this.generoEstudianteList = ListGenerator.getGeneroEstudiante();
        this.grupoSanguineoEstudianteList = ListGenerator.getGrupoSanguineoEstudiante();
        
        this.estudianteList = new ArrayList<>();
        
        this.findEstudianteAction();
    }
    
    public void goEstudianteAction(){
     
        
        logger.info("Accediendo a la pagina "+page);
        this.preparateGoEstudiante();
        this.page="estudiante.xhtml";
        logger.info("Acceso Exitoso");
     }

    public void findEstudianteAction(){
        try{
            this.estudianteList = this.planillaDelegate.getEstudianteByCriteria(this.paramTipoIdentificacion, this.paramNumeroIdentificacion, this.paramNombres, this.paramApellidos);
            logger.info("Se obtuvieron resultados del metodo getEstudianteByCriteria. cant= "+estudianteList.size());
        }catch(Exception e){
            logger.error("Error al encontrar Estudiante "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }
    public void findMatriculaAction(){
        try{
            this.matriculaList=this.planillaDelegate.getMatricula(this.estudiante);
            logger.info("Se obtuvieron resultados de getMatricula. cant= "+matriculaList.size());
        }catch(Exception e){
            logger.error("Error al encontrar Matricula "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
    }
    public void findEstudianteMatriculaAction(){
        try{
            this.cursoMatriculaList=this.planillaDelegate.getEstudiantesMatricula(this.curso);
            logger.info("Se obtuvieron resultados de getEstudiantesMatricula. cant= "+cursoMatriculaList.size());
        }catch(Exception e){
            logger.error("Error al encontrar Estudiantes matriculados al curso "+curso.getNombre()+"-"+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
    }
    private void preparateGoAddEstudiante(){
        this.estudiante = new Estudiante();
    }
    
    public void goAddEstudianteAction(){
        logger.info("Accediendo a la pagina "+page);
        this.preparateGoAddEstudiante();
        this.page = "estudianteAdd.xhtml";
        logger.info("Acceso Exitoso");
    }
    
    public void addEstudianteAction(){
        try{
            logger.info("A�adiendo a "+estudiante.getNombres());
            this.planillaDelegate.persistEstudiante(this.estudiante);
            this.estudianteList.add(this.estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo correctamente", "Se guardo correctamente"));
            this.page= "estudiante.xhtml";
            logger.info("Estudiante a�adido");
        }catch(Exception e){
            logger.error("Error al a�adir Estudiante "+estudiante.getNombres()+"-"+e.getMessage());
            e.printStackTrace(System.out);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getStackTrace().toString(), e.getStackTrace().toString()));
        }
    }
    public void goUpdEstudianteAction(){
        findMatriculaAction();
        this.page="estudianteUpd.xhtml";
    }
    
    public void updEstudianteAction(){
        try{
            this.estudiante = this.planillaDelegate.mergeEstudiante(this.estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Se actualizo correctamente", "Se actualizo correctamente"));
            this.findEstudianteAction();
            this.findCursoAction();
            this.page= "estudiante.xhtml";
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  e.getMessage(), e.getMessage()));
        }
    }
    
    public void delEstudianteAction(){
        try{
            this.planillaDelegate.removeEstudiante(this.estudiante);
            this.estudianteList.remove(this.estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino correctamente", "Se elimino correctamente"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }
    
    public void cancelEstudianteAction(){
        this.estudiante = null;
        this.page = "estudiante.xhtml";
    }
    public void cancelCursoAction(){
        this.curso = null;
        this.page = "cursos.xhtml";
    }
    private void preparateGoCursos(){
        this.modalidadList =  ListGenerator.getModalidadCurso();
        
        this.cursoList = new ArrayList<>();
        
        this.findCursoAction();
    }
    public void goCursosAction(){
        
        this.preparateGoCursos();
        this.page="cursos.xhtml";
    }

    public void findCursoAction(){
        try{
            this.cursoList = this.planillaDelegate.getCursoByCriteria(paramNombreCurso,paramCodigo,paramModalidad);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }
    private void preparateGoAddCurso(){
        this.curso= new Curso();
        this.modalidadList =  ListGenerator.getModalidadCurso();
    }
    
    public void goAddCursoAction(){
        this.preparateGoAddCurso();
        this.page = "cursosAdd.xhtml";
    }
    
    public void addCursoAction(){
        try{
            this.planillaDelegate.persistCurso(this.curso);
            this.cursoList.add(this.curso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo correctamente", "Se guardo correctamente"));
            this.page= "cursos.xhtml";
        }catch(Exception e){
            e.printStackTrace(System.out);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getStackTrace().toString(), e.getStackTrace().toString()));
        }
    }
    public void matricularAction(){
        try{
            
            this.matricula = new Matricula();
            this.matricula.setEstudiante(this.estudiante);
            this.matricula.setCurso(this.curso);
            this.matricula.setFechaRegistro(new Date());
            this.matricula = this.planillaDelegate.persistMatricula(this.matricula);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Se actualizo correctamente", "Se actualizo correctamente"));
            this.findEstudianteAction();
            this.page= "estudianteUpd.xhtml";
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  e.getMessage(), e.getMessage()));
            e.printStackTrace(System.err);
        }
    }
    private void preparateCursoShow(){
        this.cursoMatriculaList = new ArrayList<>();
        findEstudianteMatriculaAction();
    }

   

    public void goCursoShowAction(){
        preparateCursoShow();
        page="cursoShow.xhtml";
    }
    private void preparateGoAddMatricula() throws Exception {
        estudiante=new Estudiante();
        matricula= new Matricula();
        this.estudianteListTabla =  planillaDelegate.getEstudianteByCriteria(null, null, null, null);
        estudianteListMatricula= new ArrayList<Estudiante>();
        
    }
    
    public void goAddMatriculaAction() throws Exception {
        this.preparateGoAddMatricula();
        this.page = "matriculaAdd.xhtml";
    }
    public void addMatriculaAction(){
        try{
            this.planillaDelegate.persistMatriculas(estudianteListMatricula,curso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo correctamente", "Se guardo correctamente"));
            this.page= "cursosShow.xhtml";
        }catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }
    //Getters y Setters
    public void setDatePatternInput(String datePatternInput) {
        this.datePatternInput = datePatternInput;
    }

    public void setEstudianteListMatricula(List<Estudiante> estudianteListMatricula) {
        this.estudianteListMatricula = estudianteListMatricula;
    }

    public List<Estudiante> getEstudianteListMatricula() {
        return estudianteListMatricula;
    }

    public String getDatePatternInput() {
        return datePatternInput;
    }

    public void setPlanillaDelegate(DelegatePlanilla planillaDelegate) {
        this.planillaDelegate = planillaDelegate;
    }

    public DelegatePlanilla getPlanillaDelegate() {
        return planillaDelegate;
    }


    public List<Estudiante.TipoIdentificacion> getTipoIdentificacionEstudianteList() {
        return tipoIdentificacionEstudianteList;
    }

    public List<Estudiante.Genero> getGeneroEstudianteList() {
        return generoEstudianteList;
    }

    public List<?> getGrupoSanguineoEstudianteList() {
        return grupoSanguineoEstudianteList;
    }
    public List<?> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setParamTipoIdentificacion(Estudiante.TipoIdentificacion paramTipoIdentificacion) {
        this.paramTipoIdentificacion = paramTipoIdentificacion;
    }

    public Estudiante.TipoIdentificacion getParamTipoIdentificacion() {
        return paramTipoIdentificacion;
    }

    public void setParamNumeroIdentificacion(String paramNumeroIdentificacion) {
        this.paramNumeroIdentificacion = paramNumeroIdentificacion;
    }

    public String getParamNumeroIdentificacion() {
        return paramNumeroIdentificacion;
    }

    public void setParamNombres(String paramNombres) {
        this.paramNombres = paramNombres;
    }

    public String getParamNombres() {
        return paramNombres;
    }

    public void setParamApellidos(String paramApellidos) {
        this.paramApellidos = paramApellidos;
    }

    public String getParamApellidos() {
        return paramApellidos;
    }

    public String getPage() {
        return page;
    }


    public void setTipoIdentificacionEstudianteList(List<Estudiante.TipoIdentificacion> tipoIdentificacionEstudianteList) {
        this.tipoIdentificacionEstudianteList = tipoIdentificacionEstudianteList;
    }

    public void setGeneroEstudianteList(List<Estudiante.Genero> generoEstudianteList) {
        this.generoEstudianteList = generoEstudianteList;
    }

    public void setGrupoSanguineoEstudianteList(List<Estudiante.GrupoSanguineo> grupoSanguineoEstudianteList) {
        this.grupoSanguineoEstudianteList = grupoSanguineoEstudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public void setModalidadList(List<Curso.Modalidad> modalidadList) {
        this.modalidadList = modalidadList;
    }

    public List<Curso.Modalidad> getModalidadList() {
        return modalidadList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setParamNombreCurso(String paramNombreCurso) {
        this.paramNombreCurso = paramNombreCurso;
    }

    public String getParamNombreCurso() {
        return paramNombreCurso;
    }

    public void setParamCodigo(String paramCodigo) {
        this.paramCodigo = paramCodigo;
    }

    public String getParamCodigo() {
        return paramCodigo;
    }

    public void setParamModalidad(Curso.Modalidad paramModalidad) {
        this.paramModalidad = paramModalidad;
    }

    public Curso.Modalidad getParamModalidad() {
        return paramModalidad;
    }
    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }
    public void setCursoMatriculaList(List<Estudiante> cursoMatriculaList) {
        this.cursoMatriculaList = cursoMatriculaList;
    }

    public List<Estudiante> getCursoMatriculaList() {
        return cursoMatriculaList;
    }

    public void setEstudianteListTabla(List<Estudiante> estudianteListTabla) {
        this.estudianteListTabla = estudianteListTabla;
    }

    public List<Estudiante> getEstudianteListTabla() {
        return estudianteListTabla;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Matricula getMatricula() {
        return matricula;
    }
    //Fin Getters y Setters
}
