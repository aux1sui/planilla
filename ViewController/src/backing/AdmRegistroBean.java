package backing;

import entities.Estudiante;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import utils.ListGenerator;

@ManagedBean(name = "admRegistroBean")
@SessionScoped
public class AdmRegistroBean implements Serializable{
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 8336114230726923893L;

    private String datePatternInput = "dd/MM/yyyy";
    
    @ManagedProperty(value = "#{planillaDelegate}")
    private DelegatePlanilla planillaDelegate;
    
    
    private List<Estudiante.TipoIdentificacion> tipoIdentificacionEstudianteList;
    private List<Estudiante.Genero> generoEstudianteList;
    private List<Estudiante.GrupoSanguineo> grupoSanguineoEstudianteList;
    private List<Estudiante> estudianteList;
    
    private Estudiante estudiante;
    
    private Estudiante.TipoIdentificacion paramTipoIdentificacion;
    private String paramNumeroIdentificacion;
    private String paramNombres;
    private String paramApellidos;
    private String page = "index.xhtml";
    
    
    private void preparateGoEstudiante(){
        this.tipoIdentificacionEstudianteList =  ListGenerator.getTipoIdentificacionEstudiante();
        this.generoEstudianteList = ListGenerator.getGeneroEstudiante();
        this.grupoSanguineoEstudianteList = ListGenerator.getGrupoSanguineoEstudiante();
        
        this.estudianteList = new ArrayList<>();
        
        this.findEstudianteAction();
    }
    
    public void goEstudianteAction(){
        
        this.preparateGoEstudiante();
        this.page="estudiante.xhtml";
    }

    public void findEstudianteAction(){
        try{
            this.estudianteList = this.planillaDelegate.getEstudianteByCriteria(this.paramTipoIdentificacion, this.paramNumeroIdentificacion, this.paramNombres, this.paramApellidos);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
        }
    }
    
    private void preparateGoAddEstudiante(){
        this.estudiante = new Estudiante();
    }
    
    public void goAddEstudianteAction(){
        this.preparateGoAddEstudiante();
        this.page = "estudianteAdd.xhtml";
    }
    
    public void addEstudianteAction(){
        try{
            this.planillaDelegate.persistEstudiante(this.estudiante);
            this.estudianteList.add(this.estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo correctamente", "Se guardo correctamente"));
            this.page= "estudiante.xhtml";
        }catch(Exception e){
            e.printStackTrace(System.out);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getStackTrace().toString(), e.getStackTrace().toString()));
        }
    }
    public void goUpdEstudianteAction(){
        this.page="estudianteUpd.xhtml";
    }
    
    public void updEstudianteAction(){
        try{
            this.estudiante = this.planillaDelegate.mergeEstudiante(this.estudiante);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Se actualizo correctamente", "Se actualizo correctamente"));
            this.findEstudianteAction();
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
    
    public void cancelAction(){
        this.estudiante = null;
        this.page = "estudiante.xhtml";
    }

    public void setDatePatternInput(String datePatternInput) {
        this.datePatternInput = datePatternInput;
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
}
